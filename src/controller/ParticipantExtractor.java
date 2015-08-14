package controller;

import model.*;

public class ParticipantExtractor {
	/**
	 * Checks the event name (input string) for the preposition 'with' and assume anything that follows refers to participants in the event.
	 * Any occurrence of the word 'and' (preceded by a space) is replaced by a comma. Using ', ' as a delimiter, the substring following 'with '
	 * is split into an array. Each value of the array is then considered to be a participant name and added to the event's list.
	 */
	private static Event event;
	
	private ParticipantExtractor(){}
	
	public static Event extract(Event e){
		event = e;
		String eName = event.getName();
		System.out.println("Extracting participants from: "+eName);
		if(Regex.matches(eName, Participants.WITH.regex())){
			extractNames(Participants.WITH);
			remove(Participants.WITH.regex()+".*");
		}
		return event;
	}
	
	private static void extractNames(Participants prtsValue){
		String match = Regex.getMatch(event.getName(), prtsValue.regex()+".*");
		String list = match.replaceAll(" and ", ", ").replaceFirst(prtsValue.regex(), "");
		String[] names = list.split(", ");
		for(String name : names){
			System.out.println("Adding ptcpnt: "+name);
			event.addParticipant(name);
		}
	}
	
	private static void remove(String toRemove){
		String eventName = event.getName();
		eventName = eventName.replaceAll(toRemove+" ?", "");
		event.setName(eventName);
	}
}
