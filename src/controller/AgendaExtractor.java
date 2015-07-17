package controller;

import model.Event;

public class AgendaExtractor {
	/**
	 * No need to remove agenda from event name. Will call this method from within other extractors
	 */
	private static Event event;
	
	public static Event extract(Event e) {
		event = e;
		System.out.println("Extracting agenda...");
		String match = Regex.getMatch(event.getName(), PrepCombo.AGENDA.regex()+".*+");
		System.out.println("Found '"+match+"'");
		event.setAgenda(match);
		remove(match);
		
		return event;
	}
	
	private static void remove(String toRemove){
		String eventName = event.getName();
		eventName = eventName.replaceAll(toRemove+" ?", "");
		event.setName(eventName);
	}

}
