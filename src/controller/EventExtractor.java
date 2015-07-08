package controller;

import java.util.regex.*;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import model.Event;

public class EventExtractor {
	/**
	 * Starting with attributes this time.
	 * Seeing if they can be used statically...
	 */
	private static Event event;
	
	private EventExtractor(){}
	
	//checks for dates before times and phrases before formats
	//if date not set when time extracted (phrase or format), then date set to today
	public static Event extract(String input){
		setEvent(new Event(input));
		//checkPrepositions();
		extractDatePhrase();
		extractDate();
		extractTimePhrase();
		extractTime();
		extractPeriod();
		extractParticipants();
		extractLocation();
		return getEvent();
	}
	
	private static void extractTime(){
		for(Time time : Time.values()){
			if(matches(event.getName(), time.regex())){
				String match = getMatch(event.getName(), time.regex());
				if(event.getDay() == null){
					event.setDay(LocalDate.now());
				}
				event.setTime(LocalTime.parse(match, time.format()));
				remove(match);
				break;
			}
		}
	}
	
	private static void extractTimePhrase(){
		for(TimePhrase phrase : TimePhrase.values()){
			if(matches(event.getName(), phrase.regex())){
				if(event.getDay() == null){
					if(phrase.equals(TimePhrase.MORNING)){
						event.setDay(LocalDate.now().plusDays(1));
					} else {
						event.setDay(LocalDate.now());
					}
				}
				event.setTime(phrase.time());
				remove(phrase.regex());
				break;
			}
		}
	}
	
	private static void extractDate(){
		String eName = event.getName();
		/*if(matches(eName, "[0-9]+(st|nd|rd|th)")){
			String match = getMatch(eName, "[0-9]+(st|nd|rd|th)");
			eName = eName.replaceAll(match, match.substring(0, match.length() - 2));
		}*/
		for(Date date : Date.values()){
			if(matches(eName, date.regex())){
				String match = getMatch(eName, date.regex());
				event.setDay(LocalDate.parse(match, date.format()));
				remove(match);
				break;
			}
		}
	}
	
	private static void extractDatePhrase(){
		for(DatePhrase phrase : DatePhrase.values()){
			if(matches(event.getName(), phrase.regex())){
				switch(phrase){
				case DAYofWEEK: extractDayOfWeek(); break;
				case TOMORROW: 
					event.setDay(LocalDate.now().plusDays(1)); 
					remove(DatePhrase.TOMORROW.regex());
					break;
				}
			}
		}
	}
	
	private static void extractDayOfWeek(){
		String regex = DatePhrase.DAYofWEEK.regex();
		String input = event.getName();
		String dayFound = getMatch(input, regex).toUpperCase();
		LocalDate day = LocalDate.now();
		day = day.dayOfWeek().setCopy(dayFound.replaceAll("(ON )?", ""));
		if(day.isBefore(LocalDate.now().plusDays(1))){
			day = day.plusWeeks(1);
		}
		event.setDay(day);
		remove(regex);
	}
	
	private static void extractPeriod(){
		for(PrdEnum prd : PrdEnum.values()){
			if(matches(event.getName(), prd.regex())){
				String match = getMatch(event.getName(), prd.regex());
				event.setPeriod(Period.parse(match, prd.format()));
				remove(match);
			}
		}
	}
	
	private static void extractParticipants(){
		for(Participants value : Participants.values()){
			if(matches(event.getName(), value.regex())){
				String match = getMatch(event.getName(), value.regex());
				String participant = match.replaceAll("with ", "");
				switch(value){
				case ONE:
					event.addParticipant(participant);
					break;
				case MANY:
					addMultipleParticipants(participant);
					break;
				case MEETING:
					participant = participant.replaceAll("[Mm]eet(ing)? ", "");
					addMultipleParticipants(participant);
					event.setName(match);
					return;
				}//end switch
				remove(match);
				break;
			}//end if
		}//end for
		
	}
	
	private static void addMultipleParticipants(String csList){
		csList = csList.replaceAll(" and ", ", ");
		String[] list = csList.split(", ");
		for(String name : list){
			event.addParticipant(name);
		}
	}
	
	private static void extractLocation(){
		if(matches(event.getName(), "at .+")){
			String match = getMatch(event.getName(), "at .+");
			event.setLocation(match.replaceAll("at ", ""));
			//This will mean that the location has been picked up in 'participants...
			if(event.getParticipants() != null){
				for(String participant : event.getParticipants()){
					event.getParticipants().remove(participant);
					participant = participant.replaceAll(match, "");
					event.addParticipant(participant);
				}
			}
			
			remove(match);
		}
	}
	//For time being, this will simply remove prepositions
	//In future it may be checked first, and used to select correct extractors
	private static void checkPrepositions(){
		String eName = getEvent().getName();
		for(Preps p : Preps.values()){
			if(matches(eName, p.asString())){
				eName = removeMatch(eName, p.asString());
			}
		}
		getEvent().setName(eName);
	}
	
	private static boolean matches(String input, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		return m.find();
	}
	
	private static String getMatch(String input, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		m.find();
		return m.group();
	}
	
	private static String removeMatch(String input, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		m.find();
		//just changed to only remove regex plus space after, not before
		//...might want to change to be the other way around at some point
		return input.replaceAll(m.group()+" ?", "");
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
