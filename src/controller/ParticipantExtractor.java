package controller;

import model.*;

public class ParticipantExtractor {

	private static Event event;
	
	private ParticipantExtractor(){}
	
	public static Event extract(Event e){
		event = e;
		String eName = event.getName();
		System.out.println("Extracting participants from: "+eName);
		
		//If event name has 'meet(ing) with' or just '..with..'
		if(Regex.matches(eName, "("+Participants.MEET.regex()+")?" + Participants.WITH.regex())){
			extractNames(Participants.WITH);
			remove(Regex.getMatch(eName, Participants.WITH.regex()+".*"));
		} else {
			extractNames(Participants.MEET);
		}
		if(Regex.matches(eName, Participants.MEET.regex())){
			
			String newName;
			if(event.getAgenda() != null){
				newName = event.getAgenda();
			} else {
				newName = "Meeting";
			}
			if(event.getParticipants().size() > 0){
				newName = newName + " with";
				for(String name : event.getParticipants()){
					newName = newName + " " + name + ", ";
				}
				newName = newName.substring(0, newName.length() - 2);
			}
			event.setName(newName);
		}
		return event;
	}
	
	private static void extractNames(Participants prtsValue){
		if(Regex.matches(event.getName(), prtsValue.regex()+".+"+PrepCombo.AGENDA.regex())){
			event = AgendaExtractor.extract(event);
		}
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
