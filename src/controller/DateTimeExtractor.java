package controller;

import org.joda.time.*;
import org.joda.time.format.PeriodFormat;

import model.Event;

public class DateTimeExtractor {
	/**
	 * Receives event from EventController and checks for substrings indicating date, time and period in the event name (input string).
	 * Uses relevant enumerators to look for date and time formats or expressions and period.
	 * If found, the relevant format or expression/phrase is interpreted, the value calculated and set as the relevant event attribute.
	 * The relevant substring is removed from the event name.
	 * In some cases a JodaTime formatter is passed back by the enumerator and this is used to parse the event name.
	 * Date is extracted first, period last. When extracting time, if a time format or phrase is found but date has not been set,
	 * the day is set to the current date.
	 */
	private static Event event;
	
	private DateTimeExtractor(){}
	
	public static Event extract(String input){
		setEvent(new Event(input));
		extractDatePhrase();
		extractDate();
		extractTimePhrase();
		extractTime();
		extractPeriod();
		return getEvent();
	}
	
	private static void extractTime(){
		for(Time time : Time.values()){
			if(Regex.matches(event.getName(), time.regex())){
				String match = Regex.getMatch(event.getName(), time.regex());
				event.setTime(LocalTime.parse(match, time.format()));
				if(time.equals(Time.JUSTNUMBER) && event.getTime().isBefore(LocalTime.parse("08:00"))){
					event.setTime(event.getTime().plusHours(12));
				}
				if(event.getDay() == null){
					if(event.getTime().isBefore(LocalTime.now())){
						event.setDay(LocalDate.now().plusDays(1));
					} else {
						event.setDay(LocalDate.now());
					}
				}
				remove(match);
				break;
			}
		}
	}
	
	private static void extractTimePhrase(){
		for(TimePhrase phrase : TimePhrase.values()){
			if(Regex.matches(event.getName(), phrase.regex())){
				if(event.getDay() == null){
					if(phrase.equals(TimePhrase.MORNING)){
						event.setDay(LocalDate.now().plusDays(1));
					} else {
						event.setDay(LocalDate.now());
					}
				}
				event.setTime(phrase.time());
				event.setTimePhrase(phrase);
				remove(phrase.regex());
				break;
			}
		}
	}
	
	private static void extractDate(){
		String eName = event.getName();
		for(Date date : Date.values()){
			if(Regex.matches(eName, date.regex())){
				String match = Regex.getMatch(eName, date.regex());
				event.setDay(LocalDate.parse(match, date.format()));
				remove(match);
				break;
			}
		}
	}
	
	private static void extractDatePhrase(){
		for(DatePhrase phrase : DatePhrase.values()){
			if(Regex.matches(event.getName(), phrase.regex())){
				switch(phrase){
				case DAYofWEEK: 
					extractDayOfWeek(); 
					break;
				case TOMORROW: 
					event.setDay(LocalDate.now().plusDays(1)); 
					remove(DatePhrase.TOMORROW.regex());
					break;
				case TODAY:
					event.setDay(LocalDate.now());
					remove(DatePhrase.TODAY.regex());
					break;
				case NEXTweekDAY:
					extractDayOfWeek();
					if((event.getDay().getDayOfWeek() - LocalDate.now().getDayOfWeek()) < 3){
						event.setDay(event.getDay().plusWeeks(1));
					}
					break;
				}
			}
		}
	}
	
	private static void extractDayOfWeek(){
		String regex = DatePhrase.DAYofWEEK.regex();
		String input = event.getName();
		String dayFound = Regex.getMatch(input, regex).toUpperCase().replaceAll(",", "");
		LocalDate day = LocalDate.now();
		day = day.dayOfWeek().setCopy(dayFound.replaceAll("(ON |THIS )?", ""));
		if(day.isBefore(LocalDate.now().plusDays(1))){
			day = day.plusWeeks(1);
		}
		event.setDay(day);
		remove(regex);
	}
	
	//NB 'for |and ' appended to regex at this end so that prdEnum can also be used for relating to events
	private static void extractPeriod(){
		Period p = Period.ZERO;
		String regex = "for "+PrdEnum.all();
		for(PrdEnum prd : PrdEnum.values()){
			regex = regex + "(, | and )?(" + prd.regex() +")?";
		}
		//regex = regex + ")?";
		if(Regex.matches(event.getName(), regex)){
			String period = Regex.getMatch(event.getName(), regex);
			System.out.println("Found period: '"+period+"'");
			for(PrdEnum value : PrdEnum.values()){
				System.out.println(value);
				if(Regex.matches(period, value.regex())){
					String match = Regex.getMatch(period, value.regex());
					System.out.println("Matched '"+match+"'");
					p = p.plus(Period.parse(match, value.format()));
				}
			}
			System.out.println("Period is: '"+p.normalizedStandard().toString(PeriodFormat.getDefault())+"'");
			if(!p.equals(Period.ZERO)){
				event.setPeriod(p.normalizedStandard());
				remove(period);
			}
		}
		/*for(Time time : Time.values()){
			if(Regex.matches(event.getName(), "(until|til) "+time.regex())){
				String match = Regex.getMatch(event.getName(), "(until|til) "+time.regex());
				LocalTime end = LocalTime.parse(match, time.format());
				p = Period.fieldDifference(event.getTime(), end);
			}
		}*/
		
		String halfHour = "for half an hour";
		if(Regex.matches(event.getName(), halfHour)){
			event.setPeriod(Period.minutes(30));
			remove(halfHour);
		}
	}
	
	private static void remove(String toRemove){
		String eventName = event.getName();
		eventName = eventName.replaceAll(toRemove+" ?", "");
		event.setName(eventName);
	}
	
	private static Event getEvent() {
		return DateTimeExtractor.event;
	}

	private static void setEvent(Event event) {
		DateTimeExtractor.event = event;
	}

}
