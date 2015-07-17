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
		String dayFound = Regex.getMatch(input, regex).toUpperCase().replaceAll(",", "");
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
			if(Regex.matches(event.getName(), prd.regex())){
				String match = Regex.getMatch(event.getName(), prd.regex());
				event.setPeriod(Period.parse(match, prd.format()));
				remove(match);
			}
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
