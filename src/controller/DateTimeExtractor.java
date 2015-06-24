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
		output = getDate(input, output);
		return output;
	}
	
	public DateTime getTime(String input, DateTime dt){
		//24 hour - simply checking for hh:mm format (or h:mm)
		if(matches(input, "[1-2]?[0-9]:[0-6][0-9]")){
			String time[] = getMatch(input, "[1-2]?[0-9]:[0-6][0-9]").split(":");
			int hours = Integer.parseInt(time[0]);
			int minutes = Integer.parseInt(time[1]);
			dt = dt.withTime(hours, minutes, 0, 0);
		}
		//checking for h or hh format
		if(matches(input, "[1-2]?[0-9]")){
			String time = getMatch(input, "[1-2]?[0-9]");
			int hours = Integer.parseInt(time);
			System.out.println("You got this: "+hours+" from "+time);
			dt = dt.withTime(hours, 0, 0, 0);
		}
		//checking for am/pm notation - with sub-methods could check for am/pm separately, just [0-9][aApP][mM]
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
	
	public DateTime getDate(String input, DateTime dt){
		if(matches(input, "[Tt]omorrow")){
			dt = dt.plusDays(1);
		}
		//Look for days
		if(matches(input, "[^ o]day")){
			System.out.println("Found a day: "+getMatch(input, "[^ o]day"));
			dt = getDayOfWeek(input, dt);
		}
		
		/*if(matches(input, "[Mm]onday")){
			if(now().getDayOfWeek() > 0){
				dt = dt.plusDays(8 - (now().getDayOfWeek()));
			} else {
				dt = dt.withDayOfWeek(1);
			}
		}
		if(matches(input, "[Tt]uesday")){
			if(now().getDayOfWeek() > 1){
				dt = dt.plusDays(9 - (now().getDayOfWeek()));
			} else {
				dt = dt.withDayOfWeek(2);
			}
		}
		if(matches(input, "[We]dnesday")){
			if(now().getDayOfWeek() > 2){
				dt = dt.plusDays(10 - (now().getDayOfWeek()));
			} else {
				dt = dt.withDayOfWeek(3);
			}
		}
		if(matches(input, "[Tt]hursday")){
			if(now().getDayOfWeek() > 3){
				dt = dt.plusDays(11 - (now().getDayOfWeek()));
			} else {
				dt = dt.withDayOfWeek(4);
			}
		}
		if(matches(input, "[Ff]riday")){
			if(now().getDayOfWeek() > 4){
				dt = dt.plusDays(12 - (now().getDayOfWeek()));
			} else {
				dt = dt.withDayOfWeek(5);
			}
		}
		if(matches(input, "[Sa]turday")){
			if(now().getDayOfWeek() > 5){
				dt = dt.plusDays(13 - (now().getDayOfWeek()));
			} else {
				dt = dt.withDayOfWeek(6);
			}
		}
		if(matches(input, "[Ss]unday")){
			if(now().getDayOfWeek() > 6){
				dt = dt.plusDays(14 - (now().getDayOfWeek()));
			} else {
				dt = dt.withDayOfWeek(7);
			}
		}*/
		return dt;
	}
	
	private DateTime getDayOfWeek(String input, DateTime dt){
		String regex = "([Mm]on|[Tt]ues|[Ww]ednes|[Tt]hurs|[Ff]ri|[Ss]atur|[Ss]un)day";
		String day = getMatch(input, regex).toUpperCase();
		System.out.println("this would be your day output: "+day);
		dt = dt.dayOfWeek().setCopy(day);
		if(dt.isBefore(now())){
			dt = dt.plusWeeks(1);
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
		m.find();
		return m.group();
		/*if(m.find()){
			System.out.println("Found "+m.group());
			return m.group();
		} else {
			//shouldn't need this if earlier parts work... 
			return "0:0";
		}*/
	}
	
	public DateTime now(){
		return new DateTime();
	}

		
}
