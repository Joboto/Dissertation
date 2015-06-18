package model;

import java.util.ArrayList;
import org.joda.time.format.DateTimeFormatter;

public class EventQueue {
	private ArrayList<Event> eventList;
	
	public EventQueue(){
		this.eventList = new ArrayList<Event>();
	}
	
	public String toString(){
		String output = "";
		for(Event event : this.eventList){
			output = output + event.getName();
			output = output + event.getDateTime().toString();
		}
		return output;
	}

}
