package model;

import org.joda.time.DateTime;

public class Event {
	private DateTime dateTime;
	private String name;
	
	public Event(DateTime dt, String name){
		setDateTime(dt);
		setName(name);
	}
	
	public Event(String name){
		setName(name);
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
	
	

}
