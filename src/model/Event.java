package model;

import org.joda.time.DateTime;

public class Event implements Comparable<Event>{
	/**
	 * Might also need a duration (JodaTime), from which you could work out endTime, or vice versa...
	 */
	private DateTime dateTime;
	private String name;
	
	public Event(DateTime dt, String name){
		setDateTime(dt);
		setName(name);
	}
	
	/*public Event(String name){
		setName(name);
	}*/
	
	public String toString(){
		String output = getName();
		output = output + " " + getDateTime().dayOfWeek().getAsShortText();
		output = output + " " + getDateTime().getDayOfMonth();
		output = output + " " + getDateTime().monthOfYear().getAsShortText();
		output = output + " " + getDateTime().getYear();
		return output;
	}
	
	public DateTime getDateTime() {
		return this.dateTime;
	}
	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Event o) {
		DateTime mine = getDateTime();
		DateTime theirs = o.getDateTime();
		int output = 0;
		if(mine.isBefore(theirs)){output = -1;}
		if(mine.isEqual(theirs)){output = 0;}
		if(mine.isAfter(theirs)){output = 1;}
		return output;
	}
	
	

}
