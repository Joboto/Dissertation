package controller;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.*;

public class DateTimeExtractor {
	/**
	 * Going to start with no attributes. Just have methods for returning stuff...
	 * Might have ArrayLists later on for holding regex's
	 * ...and also method for returning list of event names
	 */
	public DateTimeExtractor(){}
	
	public DateTime extractDateTime(String input){
		DateTime output = new DateTime();
		output = getTime(input, output);
		return output;
	}
	
	public DateTime getTime(String input, DateTime dt){
		//currently working on assumption that there will be a time in the input either in hh:mm, hh:mm or hh format.
		if(matches(input, "[1-2]?[0-9]:[0-6][0-9]")){
			String time[] = getMatch(input, "[1-2]?[0-9]:[0-6][0-9]").split(":");
			int hours = Integer.parseInt(time[0]);
			int minutes = Integer.parseInt(time[1]);
			dt = dt.withTime(hours, minutes, 0, 0);
		} 
		if(matches(input, "[1-2]?[0-9]")){
			String time = getMatch(input, "[1-2]?[0-9]");
			int hours = Integer.parseInt(time);
			System.out.println("You got this: "+hours+" from "+time);
			dt = dt.withTime(hours, 0, 0, 0);
		}
		if(matches(input, "[1-2]?[0-9][aApP][mM]")){
			String fullTime = getMatch(input, "[1-2]?[0-9][aApP][mM]");
			int hours = Integer.parseInt(getMatch(fullTime, "[1-2]?[0-9]"));
			if(matches(fullTime, "[pP]")){
				System.out.println("Found "+hours+"PM");
				if(hours == 12){
					dt = dt.withTime(hours, 0, 0, 0);
				} else {
					dt = dt.withTime(hours+12, 0, 0, 0);
				}
			} else {
				System.out.println("Found "+hours+"AM");
				if(hours == 12){
					dt = dt.withTime(0, 0, 0, 0);
				} else {
					dt = dt.withTime(hours, 0, 0, 0);
				}
			}
		}
		return dt;
	}
	
	public boolean matches(String input, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		return m.find();
	}
	
	public String getMatch(String input, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		if(m.find()){
			System.out.println("Found "+m.group());
			return m.group();
		} else {
			//shouldn't need this if earlier parts work... 
			return "0:0";
		}
	}

		
}
