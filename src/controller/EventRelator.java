package controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PreDestroy;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;

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
		System.out.println("Have found 'after' or 'before'...");
		System.out.println(allEventNames());
		System.out.println(getReference());
		if(Regex.matches(allEventNames(), getReference())){
			System.out.println("Found reference... "+getReference());
			Event related = getRelatedEvent();
			System.out.println("Related event: "+related.getName());
			if(Regex.matches(event.getName(), Relation.AFTER.regex())){
				if(related.getEnd() == null){
					event.setTime(related.getTime().plus(timeGap()));
				} else {
					event.setTime(related.getEnd().toLocalTime().plus(timeGap()));
				}
			} else {
				if(event.getPeriod() == null){
					event.setTime(related.getTime().minus(timeGap()));
				} else {
					event.setTime(related.getTime().minus(timeGap()).minus(event.getPeriod()));
				}
			}
				
		}
		String name = event.getName().replaceAll(Relation.all()+".*", "");
		event.setName(name);
		if(event.getDay() == null){
			event.setDay(LocalDate.now());
		}
	}
	
	private static Period timeGap(){
		Period gap = Period.minutes(5);
		for(PrdEnum val : PrdEnum.values()){
			if(Regex.matches(event.getName(), val.regex())){
				String match = Regex.getMatch(event.getName(), val.regex());
				gap = Period.parse(match, val.format());
				event.setName(event.getName().replaceAll(match, ""));
			}
		}
		return gap;
	}
	
	private static String getReference(){
		String match = Regex.getMatch(event.getName().toLowerCase(), Relation.all() + ".+");
		match = match.replaceFirst(Relation.all(), "");
		match = match.replaceAll("[Tt]he ", "");
		match = match.replaceAll("with ", "");
		return match.toLowerCase();
	}
	
	private static Event getRelatedEvent(){
		Event toReturn = new Event();
		for(Event ev : list){
			if(Regex.matches(ev.getName().toLowerCase(), getReference())){
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
