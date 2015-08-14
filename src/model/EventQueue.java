package model;

import java.util.ArrayList;
import java.util.Collections;
import org.joda.time.*;

public class EventQueue {
	/**
	 * ArrayList for storing events. Has methods for adding and removing events.
	 */
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
	
	public String getListByDate(DateTime dt){
		String output = "";
		for(Event event : this.eventList){
			if(event.getDay().isEqual(dt.toLocalDate())){
				output = output + event.getTitle() + "\n";
			}
		}
		return output;
	}
	
	public String toString(){
		String output = "";
		for(Event event : this.eventList){
			output = output +event.getTitle() + "\n";
		}
		return output;
	}

	public ArrayList<Event> getEventList() {
		return this.eventList;
	}

}
