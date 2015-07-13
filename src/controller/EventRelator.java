package controller;

import java.util.ArrayList;
import java.util.Collections;
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
			return;
		}
		if(!matches(event.getName(), "[Aa]fter .*")){
			return;
		}
		if(event.getDay() == null){
			event.setDay(LocalDate.now());
		}
		if(matches(allEventNames(), getReference())){
			System.out.println("Relation found...");
			Event related = getRelatedEvent();
			if(related.getEnd() == null && related.getTime() != null){
				event.setTime(related.getTime().plusHours(1));
			} else {
				event.setTime(related.getEnd());
			}
			String name = event.getName().replaceAll("[Aa]fter .*", "");
			event.setName(name);
		}
	}
	
	private static String allEventNames(){
		String output = "";
		for(Event ev : list){
			output = output + ev.getName();
		}
		return output.toLowerCase();
	}
	
	private static String getReference(){
		String match = getMatch(event.getName(), "[Aa]fter .*");
		match = match.replaceFirst("[Aa]fter ", "");
		System.out.println("Relator found referred event name (string): "+match);
		return match.toLowerCase();
	}
	
	
	private static Event getRelatedEvent(){
		Event toReturn = referredEvent();
		ArrayList<Event> nameMatches = new ArrayList<Event>();
		for(Event ev : list){
			if(matches(ev.getName().toLowerCase(), toReturn.getName().toLowerCase())){
				nameMatches.add(ev);
			}
		}
		if(nameMatches.size() > 1){
			//compare participants
			for(Event ev : nameMatches){
				if(participantMatch(toReturn, ev)){
					toReturn = ev;
				}
			}
		}
		System.out.println("Relator now returning event: "+toReturn.getName());
		return toReturn;
	}
	
	
	
	private static Event referredEvent(){
		System.out.println("Creating new event from "+getReference());
		return EventExtractor.extract(getReference());
	}
	
	private static boolean participantMatch(Event e1, Event e2){
		boolean match = false;
		ArrayList<String> p1 = e1.getParticipants();
		ArrayList<String> p2 = e2.getParticipants();
		//Collections.sort(p1);
		//Collections.sort(p2);
		if(p1.size() <= p2.size()){
			for(String name1 : p1){
				for(String name2 : p2){
					if(name1.toLowerCase().equals(name2.toLowerCase())){
						match = true;
						break;
					} else {
						match = false;
					}
				}
			}
		}
		
		return match;
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
