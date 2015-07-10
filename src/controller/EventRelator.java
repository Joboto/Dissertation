package controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.LocalDate;

import model.Event;

public class EventRelator {
	
	private static Event event;
	private static ArrayList<Event> list;
	
	public static Event compare(Event e, ArrayList<Event> l){
		event = e;
		list = l;
		doTheThing();
		return event;
	}
	
	private static void doTheThing(){
		if(list.size() == 0){
			System.out.println("Not bothering with rest of relating");
			return;
		}
		if(!matches(event.getName(), "[Aa]fter .*")){
			return;
		}
		if(event.getDay() == null){
			event.setDay(LocalDate.now());
		}
		if(matches(allEventNames(), getReference())){
			event.setTime(getRelatedEvent().getEnd());
			String name = event.getName().replaceAll("[Aa]fter .*", "");
			event.setName(name);
		}
	}
	
	private static String getReference(){
		String match = getMatch(event.getName(), "[Aa]fter .*");
		match = match.replaceFirst("[Aa]fter .*", "");
		return match.toLowerCase();
	}
	
	private static Event getRelatedEvent(){
		Event toReturn = new Event();
		for(Event ev : list){
			if(matches(event.getName().toLowerCase(), ev.getName().toLowerCase())){
				toReturn = ev;
				break;
			}
		}
		return toReturn;
	}
	
	private static String allEventNames(){
		String output = "";
		for(Event ev : list){
			output = output + ev.getName();
		}
		return output.toLowerCase();
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

}