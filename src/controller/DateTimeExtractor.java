package controller;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.*;

public class DateTimeExtractor {
	/**
	 * Going to start with no attributes. Just have methods for returning stuff...
	 * Might have ArrayLists later on for holding regex's
	 * ...and also method for returning list of existing event names
	 */
	private String eventName;
	
	public DateTimeExtractor(){}
	
	public DateTime extractDateTime(String input){
		DateTime output = new DateTime();
		setEventName(input);
		output = getDate(getEventName(), output);
		output = getTime(getEventName(), output);
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
		if(matches(input, "[1-2]?[0-9]^:")){
			String time = getMatch(input, "[1-2]?[0-9]");
			int hours = Integer.parseInt(time);
			dt = dt.withTime(hours, 0, 0, 0);
		}
		//checking for am/pm notation - with sub-methods could check for am/pm separately, just [0-9][aApP][mM]
		if(matches(input, "[1-2]?[0-9][aApP][mM]")){
			String fullTime = getMatch(input, "[1-2]?[0-9][aApP][mM]");
			int hours = Integer.parseInt(getMatch(fullTime, "[1-2]?[0-9]"));
			if(matches(fullTime, "[pP]")){
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
		if(matches(input, "([Mm]on|[Tt]ues|[Ww]ednes|[Tt]hurs|[Ff]ri|[Ss]atur|[Ss]un)day")){
			dt = getDayOfWeek(input, dt);
		}
		
		return dt;
	}
	
	private DateTime getDayOfWeek(String input, DateTime dt){
		//String regex = "([Mm]on|[Tt]ues|[Ww]ednes|[Tt]hurs|[Ff]ri|[Ss]atur|[Ss]un)day";
		String day = getMatch(input, Regexes.DAY.asString()).toUpperCase();
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
		setEventName(getEventName().replaceAll(" ?"+m.group()+" ?", ""));
		return m.group();
	}
	
	public DateTime now(){
		return new DateTime();
	}

	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
		System.out.println("Event name now: "+getEventName());
	}

		
}
