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
	
	public EventExtractor(){}
	
	public static Event extract(String input){
		setEvent(new Event(input));
		checkPrepositions();
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
			System.out.println("no time found");
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
				event.setDay(LocalDate.parse(match, fmt));
				event.setName(remove(eName, match));
				break;
			}
			System.out.println("no time found");
		}
	}
	
	/*private static void extractDate(String dummy){
		String eName = getEvent().getName();
		Date datefmt = Date.EMPTY;
		//would rather have a different way of checking for matches, but 'for' loop will do for now...
		//could have method within enum...
		for(Date value : Date.values()){
			if(matches(eName, value.asString())){
				System.out.println("Found a 'Date' match "+value.asString());
				datefmt = value;
			}
		}
		switch(datefmt){
		case DAYandMONTH: 
			extractDayAndMonth();
			break;
		case MONTHandDAY: 
			extractMonthAndDay(); 
			break;
		case DAYofWEEK: 
			extractDayOfWeek();
			break;
		case TOMORROW: 
			getEvent().setName(remove(eName, Date.TOMORROW.asString()));
			getEvent().setDay(LocalDate.now().plusDays(1)); 
			break;
		case TODAY: 
			event.setName(remove(eName, Date.TODAY.asString()));
			getEvent().setDay(LocalDate.now()); 
			break;
		default:
			break;
		}
	}*/
	
	/*private static void extractDayOfWeek(){
		String regex = Date.DAYofWEEK.asString();
		String input = getEvent().getName();
		String dayFound = getMatch(input, regex).toUpperCase();
		LocalDate day = LocalDate.now();
		day = day.dayOfWeek().setCopy(dayFound);
		if(day.isBefore(LocalDate.now().plusDays(1))){
			day = day.plusWeeks(1);
		}
		getEvent().setDay(day);
		System.out.println("DoW: removing "+regex+" from "+input);
		getEvent().setName(remove(input, regex));
	}*/
	
	/*private static void extractDayAndMonth(){
		LocalDate date = LocalDate.now();
		String regex = Date.DAYandMONTH.asString();
		String output[] = getMatch(event.getName(), regex).split(" ");
		String month = output[1].toUpperCase();
		int day;
		switch(output[0].length()){
		case 1: case 3: day = Integer.parseInt(output[0].substring(0, 1));
		break;
		default: day = Integer.parseInt(output[0].substring(0, 2));
		}
		date = date.monthOfYear().setCopy(month);
		date = date.withDayOfMonth(day);
		event.setDay(date);
		event.setName(remove(event.getName(), regex));
	}*/
	
	//for American date format
	/*private static void extractMonthAndDay(){
		LocalDate date = LocalDate.now();
		String regex = Date.MONTHandDAY.asString();
		String output[] = getMatch(event.getName(), regex).split(" ");
		String month = output[0].toUpperCase();
		int day;
		switch(output[1].length()){
		case 1: case 3: day = Integer.parseInt(output[1].substring(0, 1));
		break;
		default: day = Integer.parseInt(output[1].substring(0, 2));
		}
		date = date.monthOfYear().setCopy(month);
		date = date.withDayOfMonth(day);
		event.setDay(date);
		event.setName(remove(event.getName(), regex));
	}*/
	
	private static void extractPeriod(){
		
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
		return input.replaceAll(" ?"+regex+" ?", "");
	}

	private static Event getEvent() {
		return EventExtractor.event;
	}

	private static void setEvent(Event event) {
		EventExtractor.event = event;
	}

}
