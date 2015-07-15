package controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

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
			System.out.println("No events on given day.");
			return;
		}
		if(!matches(event.getName(), "[Aa]fter .*")){
			System.out.println("Didn't find after 'after'...");
			return;
		}
		System.out.println("Have found 'after'...");
		if(matches(allEventNames(), getReference())){
			System.out.println("Relation found to reference... "+getReference());
			Event related = getRelatedEvent();
			System.out.println("Related event: "+related.getName());
			if(related.getEnd() == null){
				event.setTime(related.getTime().plusHours(1));
			} else {
				event.setTime(related.getEnd());
			}
			String name = event.getName().replaceAll("[Aa]fter .*", "");
			event.setName(name);
			if(event.getDay() == null){
				event.setDay(LocalDate.now());
			}
		}
	}
	
	private static String getReference(){
		String match = getMatch(event.getName(), "[Aa]fter .*");
		match = match.replaceFirst("[Aa]fter ", "");
		return match.toLowerCase();
	}
	
	private static Event getRelatedEvent(){
		Event toReturn = new Event();
		for(Event ev : list){
			if(matches(ev.getName().toLowerCase(), getReference())){
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
