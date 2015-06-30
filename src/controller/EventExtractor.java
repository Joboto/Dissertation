package controller;

import java.util.regex.*;

import org.joda.time.*;
import org.joda.time.convert.IntervalConverter;

import model.Event;

public class EventExtractor {
	/**
	 * Starting with attributes this time.
	 * Seeing if they can be used statically...
	 */
	
	public EventExtractor(){}
	
	public static Event extract(String input){
		Event event = new Event(input);
		event = checkPrepositions(event);
		event.setInterval(new Interval(DateTime.now(), Period.hours(1)));
		return event;
	}
	
	private static Event extractTime(Event event){
		
		return event;
	}
	
	private static Event extractDate(Event event){
		String eName = event.getName();
		Date datefmt;
		for(Date value : Date.values()){
			if(matches(eName, value.asString())){
				datefmt = value;
			}
		}
		switch(datefmt){
		case DAYandMONTH: 
		}
		return event;
	}
	
	private static Event extactPeriod(Event event){
		
		return event;
	}
	
	//For time being, this will simply remove prepositions
	//In future it may be checked first, and used to select correct extractors
	private static Event checkPrepositions(Event event){
		String eName = event.getName();
		for(Preps p : Preps.values()){
			if(matches(eName, p.asString())){
				eName = removeMatch(eName, p.asString());
			}
		}
		event.setName(eName);
		return event;
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
		return input.replaceAll(" ?"+regex+" ?", "");
	}

}
