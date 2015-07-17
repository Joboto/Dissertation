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
		System.out.println("Extracting relations...");
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
		if(!Regex.matches(event.getName(), Relation.all())){
			System.out.println("Didn't find 'after' or 'before'...");
			return;
		}
		if(Regex.matches(allEventNames(), getReference(Relation.AFTER))){
			System.out.println("After... "+getReference(Relation.AFTER));
			Event related = getRelatedEvent(Relation.AFTER);
			System.out.println("Related event: "+related.getName());
			if(related.getEnd() == null){
				event.setTime(related.getTime().plusHours(1));
			} else {
				event.setTime(related.getEnd());
			}	
		}
		if(Regex.matches(allEventNames(), getReference(Relation.BEFORE))){
			System.out.println("Before... "+getReference(Relation.BEFORE));
			Event related = getRelatedEvent(Relation.BEFORE);
			System.out.println("Related event: "+related.getName());
			event.setTime(related.getTime().minusHours(1));	
		}
		String name = event.getName().replaceAll(Relation.all(), "");
		event.setName(name);
		if(event.getDay() == null){
			event.setDay(LocalDate.now());
		}
	}
	
	private static String getReference(Relation val){
		String match = Regex.getMatch(event.getName().toLowerCase(), val.regex());
		match = match.replaceFirst(val.regex(), "");
		match = match.replaceAll("[Tt]he ", "");
		return match.toLowerCase();
	}
	
	private static Event getRelatedEvent(Relation val){
		Event toReturn = new Event();
		for(Event ev : list){
			if(Regex.matches(ev.getName().toLowerCase(), getReference(val))){
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

}
