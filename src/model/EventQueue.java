package model;

import java.util.ArrayList;
import java.util.Collections;
import org.joda.time.*;

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
	
	public String getListByDate(DateTime dt){
		String output = "";
		for(Event event : this.eventList){
			if(event.getDateTime().withTimeAtStartOfDay().isEqual(dt.withTimeAtStartOfDay())){
				output = output + event.toString() + "\n";
			}
		}
		return output;
	}

	public ArrayList<Event> getEventList() {
		return this.eventList;
	}

}
