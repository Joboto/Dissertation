package controller;

import model.Event;

public class AgendaExtractor {
	/**
	 * Makes sure that sub-phrases such as 'to have drink' doesn't get extracted with another event attribute.
	 * This is then added back into the event name after extraction is complete.
	 */
	private static Event event;
	
	public static Event extract(Event e) {
		event = e;
		System.out.println("Extracting agenda...");
		String match = Regex.getMatch(event.getName(), PrepCombo.AGENDA.regex()+".+");
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
