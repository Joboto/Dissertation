package controller;

import model.*;

public class ParticipantExtractor {

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
