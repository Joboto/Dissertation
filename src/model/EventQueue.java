package model;

import java.util.ArrayList;
import java.util.Collections;
import org.joda.time.format.DateTimeFormatter;

public class EventQueue {
	private ArrayList<Event> eventList;
	
	public EventQueue(){
		this.eventList = new ArrayList<Event>();
	}
	
	public void addEvent(Event event){
		this.eventList.add(event);
		Collections.sort(this.eventList);
	}
	
	public void removeEvent(Event event){
		this.eventList.remove(event);
	}
	
	public String toString(){
		String output = "";
		for(Event event : this.eventList){
			output = output + event.toString() + "\n";
		}
		return output;
	}

}
