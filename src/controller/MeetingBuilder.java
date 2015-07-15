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
		if(matches(event.getName(), "[Mm]eet(ing)? .+")){
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
		if(activity != null){
			event.setName(activity);
		}
	}
	
	//how about 'to...' e.g. 'meeting to discuss...' ?
	private static void extractActivity(){
		activity = null;
		String eName = event.getName();
		if(matches(eName, "(for|to) .+")){
			activity = getMatch(eName, "(for|to) .+");
			event.setName(eName.replaceAll(activity, ""));
			activity = activity.replaceAll("(for|to) ", "");
			System.out.println("Setting 'activity' to: "+activity);
			System.out.println("...and event name to: "+event.getName());
		}
	}
	
	private static void extractParticipants(){
		String match = getMatch(event.getName(), "[Mm]eet(ing)?.*");
		String list = match.replaceAll(" and ", ", ").replaceFirst("[Mm]eet(ing)? ?", "");
		if(list.length() > 0){
			String[] names = list.split(", ");
			for(String name : names){
				event.addParticipant(name);
			}
		}
		
	}

	private static boolean matches(String input, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		return m.find();
	}
	
	private static String getMatch(String input, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		m.find();
		return m.group();
	}
}
