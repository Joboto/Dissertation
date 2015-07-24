package controller;

import model.*;

public class MeetingBuilder {
	
	private static Event event;
	
	private MeetingBuilder(){}
	
	public static Event check(Event e){
		event = e;
		if(Regex.matches(event.getName(), "[Mm]eet(ing)?")){
			System.out.println("Meet building for input: "+event.getName());
			extractParticipants();
			buildMeeting();
		}
		return event;
	}
	
	private static void buildMeeting(){
		String newName = "Meeting ";
		if(event.getParticipants() != null){
			for(String name : event.getParticipants()){
				newName = newName + name + ", ";
			}
			newName = newName.substring(0, newName.length() - 2);
			if(event.getParticipants().size() > 1){
				StringBuilder b = new StringBuilder(newName);
				b.replace(newName.lastIndexOf(','), newName.lastIndexOf(',') + 1, " and");
				newName = b.toString();
			}
		}
		event.setName(newName);
	}
	
	private static void extractParticipants(){
		String match = Regex.getMatch(event.getName(), "[Mm]eet(ing)?.*");
		String list = match.replaceAll(" and ", ", ").replaceFirst("[Mm]eet(ing)? ?", "");
		if(list.length() > 0){
			String[] names = list.split(", ");
			for(String name : names){
				event.addParticipant(name);
			}
		}
		
	}
}
