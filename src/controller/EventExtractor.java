package controller;

import java.util.ArrayList;
import java.util.regex.*;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormat;
import org.joda.time.format.PeriodFormatter;

import model.Event;

public class EventExtractor {
	/**
	 * Starting with attributes this time.
	 * Seeing if they can be used statically...
	 */
	private static Event event;
	
	private EventExtractor(){}
	
	public static Event extract(String input){
		setEvent(new Event(input));
		extractDatePhrase();
		extractDate();
		extractTimePhrase();
		extractTime();
		//extractEnd();
		extractPeriod();
		return getEvent();
	}
	
	private static void extractTime(){
		for(Time time : Time.values()){
			if(Regex.matches(event.getName(), time.regex())){
				System.out.println("Time. Matched for "+time);
				String match = Regex.getMatch(event.getName(), time.regex());
				System.out.println("Found: '"+match+"'");
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
	
	private static void extractEnd(){
		Period period = Period.ZERO;
		if(Regex.matches(event.getName(), "(un)?till? .*")){
			if(event.getTime() != null){
				LocalTime start = event.getTime();
				extractTime();
				extractTimePhrase();
				//end time currently stored in event.time
				period = period.plus(Period.fieldDifference(start, event.getTime()));
				event.setTime(start);
			}
			if(event.getDay() != null){
				LocalDate start = event.getDay();
				extractDate();
				extractDatePhrase();
				//end day currently stored in event.time
				period = period.plus(Period.fieldDifference(start, event.getTime()));
				event.setDay(start);
			}
		}
		remove("(un)?till? ?");
		if(!period.equals(Period.ZERO)){
			event.setPeriod(period.normalizedStandard());
		}
	}
	
	private static void extractDate(){
		String eName = event.getName();
		for(Date date : Date.values()){
			if(Regex.matches(eName, "(on |from |(un)till? )?"+date.regex())){
				System.out.println("Is it doing part of this?");
				String match = Regex.getMatch(eName, "(on |from |(un)till? )?"+date.regex());
				event.setDay(LocalDate.parse(match, date.format()));
				remove(match);
				break;
			}
		}
	}
	
	private static void extractDatePhrase(){
		for(DatePhrase phrase : DatePhrase.values()){
			if(Regex.matches(event.getName(), "(on |from |(un)till? )?"+phrase.regex())){
				System.out.println("Or perhaps this");
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
			remove("(on |from |(un)till? )?");
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
	
	//NB 'for | and ' appended to regex at this end so that prdEnum can also be used for relating to events
	private static void extractPeriod(){
		Period p = Period.ZERO;
		for(PrdEnum prd : PrdEnum.values()){
			if(Regex.matches(event.getName(), "(for | and )"+prd.regex())){
				String match = Regex.getMatch(event.getName(), "(for | and )"+prd.regex());
				System.out.println("Found '"+match+"'");
				System.out.println("Period to add: "+p.toString());
				p = p.plus(Period.parse(match, prd.format()));
				remove(match);
			}
		}
		if(!p.equals(Period.ZERO)){
			event.setPeriod(p.normalizedStandard());
		}
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
		return EventExtractor.event;
	}

	
	private static void setEvent(Event event) {
		EventExtractor.event = event;
	}

}
