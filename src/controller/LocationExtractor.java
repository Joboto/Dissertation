package controller;

import model.*;

public class LocationExtractor {
	/**
	 * Simply takes the substring following 'in ' or 'at ' and sets it as the event's location.
	 * The whole substring starting with either preposition is removed from the event name.  
	 */
	private static Event event;
	
	private LocationExtractor(){}
	
	public static Event extract(Event e){
		event = e;
		System.out.println("extracting location from: "+event.getName());
		String match = Regex.getMatch(event.getName(), Location.all()+".*+");
		System.out.println("Match = "+match);
		event.setLocation(match.replaceFirst(Location.all(), ""));
		remove(match);
		return event;
	}
	
	private static void remove(String toRemove){
		String eventName = event.getName();
		eventName = eventName.replaceAll(toRemove+" ?", "");
		event.setName(eventName);
	}
}
