package controller;

import java.util.ArrayList;
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
		partsAndLoc();
		//extractLocation();
		//extractParticipants();
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
				event.setTimePhrase(phrase);
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
				case TODAY:
					event.setDay(LocalDate.now());
					remove(DatePhrase.TODAY.regex());
				}
			}
		}
	}
	
	private static void extractDayOfWeek(){
		String regex = DatePhrase.DAYofWEEK.regex();
		String input = event.getName();
		String dayFound = getMatch(input, regex).toUpperCase().replaceAll(",", "");
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
	
	//working on assumption that location and participants would come at end of statement
	//Not bothering with 'Meet(ing)' just now
	private static void partsAndLoc(){
		ArrayList<Object> prepositions = collectPreps();
		System.out.println(prepositions.size()+" prepositions found.");
		for(Object prep : prepositions){
			System.out.println("Switching "+prep.toString());
			switch((PrepCombo) prep){
			case LOC:
				System.out.println("Case: "+PrepCombo.LOC.toString());
				extractLocation();
				break;
			case PRTS:
				System.out.println("Case: "+PrepCombo.PRTS.toString());
				extractParticipants();
				break;
			case REL:
				System.out.println("Case: "+PrepCombo.REL.toString());
				break;
			}
		}
		/*if(matches(event.getName(), "with .*") && matches(event.getName(), "at .*")){
			if(matches(event.getName(), "with .*at .*")){
				extractLocation();
				extractParticipants();
			} else {
				extractParticipants();
				extractLocation();
			}
		}
		if(matches(event.getName(), "with .*")){extractParticipants();}
		if(matches(event.getName(), "at .*")){extractLocation();}*/
		event = MeetingBuilder.check(event);
		
	}
	
	private static ArrayList<Object> collectPreps(){
		String[] words = event.getName().split(" ");
		ArrayList<Object> found = new ArrayList<>();
		for(int loop = words.length - 1; loop >= 0; loop--){
			for(PrepCombo combo : PrepCombo.values()){
				if(matches(words[loop]+" ", combo.regex())){
					System.out.println("Found '"+words[loop]+"', adding '"+combo.toString()+"'");
					found.add(combo);
				}
			}
		}
		return found;
	}
	
	private static void extractLocation(){
		System.out.println("extracting location from: "+event.getName());
		String match = getMatch(event.getName(), Location.AT.regex()+".+");
		System.out.println("Bloody, location. Match = "+match);
		event.setLocation(match.replaceFirst(Location.all(), ""));
		remove(match);
	}
	
	private static void extractParticipants(){
		System.out.println("Extracting participants from: "+event.getName());
		String match = getMatch(event.getName(), "with .*");
		String list = match.replaceAll(" and ", ", ").replaceFirst("with ", "");
		String[] names = list.split(", ");
		for(String name : names){
			event.addParticipant(name);
		}
		remove(match);
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
