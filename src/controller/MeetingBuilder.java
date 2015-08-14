package controller;

import model.*;

public class MeetingBuilder {
	/**
	 * For standardising how meetings appear, primarily for making relating to meetings easier.
	 * By this point all other details should have been removed from the input string,
	 * including any phrases/sub-strings such as 'to discuss...'. So if a meeting is being described,
	 * what's left in the event name will be the word 'meet' or 'meeting' followed by a list of particiant
	 * names. So participants can just be extracted in the same way as in the case of the preposition 'with'.
	 * If 'meet' or 'meeting' is found, the event name is then built in the same way for each meeting;
	 * The word 'Meeting' followed by a list of all participants (if there are any stored).
	 * 
	 * As participants are extracted here as well as by the ParticipantExtractor,
	 * this means that the final event name for all meetings will still be formated the same.
	 * E.g. "Meeting with Gerry, John and Bob" will yield the same event name as
	 * "Meet Gerry with Bob and John": "Meeting Gerry, John and Bob" (not withstanding a possible reordering of names).
	 */
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
		//match = match.replaceAll("with ", "");
		String list = match.replaceAll(" and ", ", ").replaceFirst("[Mm]eet(ing)? ?", "");
		if(list.length() > 0){
			String[] names = list.split(", ");
			for(String name : names){
				event.addParticipant(name);
			}
		}
		
	}
}
