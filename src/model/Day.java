package model;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class Day {
	
	private DateTime date;
	
	public Day(DateTime date) {
		setDate(date);
		System.out.println("New day "+getDate().toString(DateTimeFormat.shortDateTime()));
	}
	
	public ArrayList<Event> getEvents(EventQueue allEvents){
		ArrayList<Event> daysEvents = new ArrayList<Event>();
		for(Event event : allEvents.getEventList()){
			if(event.getDateTime().withTimeAtStartOfDay().equals(getDate())){
				daysEvents.add(event);
			}
		}
		return daysEvents;
	}

	public DateTime getDate() {
		return this.date;
	}

	public void setDate(DateTime date) {
		this.date = date.withTimeAtStartOfDay();
	}

}
