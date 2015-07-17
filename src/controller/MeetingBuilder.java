package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.*;

public class MeetingBuilder {
	
	private static Event event;
	private static String activity;
	
	private MeetingBuilder(){}
	
	public static Event check(Event e){
		event = e;
		if(Regex.matches(event.getName(), "[Mm]eet(ing)?")){
			System.out.println("Meet building for input: "+event.getName());
			extractActivity();
			extractParticipants();
			buildMeeting();
		}
		return event;
	}
	
	private static void buildMeeting(){
		if(event.getParticipants() != null){
			activity = activity + " with ";
			for(String name : event.getParticipants()){
				activity = activity + name + ", ";
			}
			activity = activity.substring(0, activity.length() - 2);
		}
		if(activity.length() > 0){
			event.setName(activity);
		}
	}
	
	//how about 'to...' e.g. 'meeting to discuss...' ?
	private static void extractActivity(){
		String eName = event.getName();
		if(Regex.matches(eName, "(for|to) .+")){
			activity = Regex.getMatch(eName, "(for|to) .+");
			event.setName(eName.replaceAll(activity, ""));
			activity = activity.replaceAll("(for|to) ", "");
			System.out.println("Setting 'activity' to: "+activity);
			System.out.println("...and event name to: "+event.getName());
		} else {
			activity = "Meeting";
		}
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
