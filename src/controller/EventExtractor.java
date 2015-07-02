package controller;

import java.text.DateFormat;
import java.util.regex.*;

import org.joda.time.*;
import org.joda.time.convert.IntervalConverter;
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
	
	public static Event extract(String input){
		setEvent(new Event(input));
		//checkPrepositions();
		extractDatePhrase();
		extractDate();
		extractTime();
		extractPeriod();
		return getEvent();
	}
	
	private static void extractTime(){
		for(Time time : Time.values()){
			System.out.println("Checking for "+time.toString());
			if(matches(event.getName(), time.regex())){
				System.out.println("Found "+time.toString());
				String match = getMatch(event.getName(), time.regex());
				DateTimeFormatter fmt = DateTimeFormat.forPattern(time.format());
				if(event.getDay() == null){
					event.setDay(LocalDate.now());
				}
				event.setTime(LocalTime.parse(match, fmt));
				event.setName(remove(event.getName(), match));
				break;
			}
		}
	}
	
	private static void extractDate(){
		String eName = event.getName();
		if(matches(eName, "[0-9]+(st|nd|rd|th)")){
			String match = getMatch(eName, "[0-9]+(st|nd|rd|th)");
			eName = eName.replaceAll(match, match.substring(0, match.length() - 2));
		}
		for(Date date : Date.values()){
			System.out.println("Checking for "+date.toString());
			if(matches(eName, date.regex())){
				System.out.println("Found "+date.toString());
				String match = getMatch(eName, date.regex());
				DateTimeFormatter fmt = DateTimeFormat.forPattern(date.format());
				fmt = fmt.withDefaultYear(DateTime.now().getYear());
				System.out.println("Local Date test "+LocalDate.parse(match, fmt).getFieldTypes());
				event.setDay(LocalDate.parse(match, fmt));
				event.setName(remove(eName, match));
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
					event.setName(remove(event.getName(), DatePhrase.TOMORROW.regex()));
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
		day = day.dayOfWeek().setCopy(dayFound);
		if(day.isBefore(LocalDate.now().plusDays(1))){
			day = day.plusWeeks(1);
		}
		event.setDay(day);
		event.setName(remove(input, regex));
	}
	
	private static void extractPeriod(){
		for(PrdEnum prd : PrdEnum.values()){
			if(matches(event.getName(), prd.regex())){
				System.out.println("Found a Period expression");
				String match = getMatch(event.getName(), prd.regex());
				event.setPeriod(Period.parse(match, prd.format()));
				event.setName(remove(event.getName(), match));
				System.out.println("Number of hours "+event.getPeriod().getHours());
			}
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
	
	private static String remove(String input, String regex){
		return input.replaceAll(regex+" ?", "");
	}

	private static Event getEvent() {
		return EventExtractor.event;
	}

	private static void setEvent(Event event) {
		EventExtractor.event = event;
	}

}
