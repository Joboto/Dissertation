package controller;

import model.*;

public class ParticipantExtractor {

	private static Event event;
	
	private ParticipantExtractor(){}
	
	public static Event extract(Event e){
		event = e;
		System.out.println("Extracting participants from: "+event.getName());
		String match = Regex.getMatch(event.getName(), "with .*");
		String list = match.replaceAll(" and ", ", ").replaceFirst("with ", "");
		String[] names = list.split(", ");
		for(String name : names){
			event.addParticipant(name);
		}
		remove(match);
		return event;
	}
	
	private static void remove(String toRemove){
		String eventName = event.getName();
		eventName = eventName.replaceAll(toRemove+" ?", "");
		event.setName(eventName);
	}
}
