package model;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class Day {
	/**
	 * Might want to return an event based on a time.
	 * Need also, therefore, to think about durations...
	 * Might want to associate with month or calendar so can refer to EventQueue directly...
	 */
	private DateTime date;
	
	public Day(DateTime date) {
		setDate(date);
		//System.out.println("New day "+getDate().toString(DateTimeFormat.shortDateTime()));
	}
	
	public ArrayList<Event> getEvents(EventQueue allEvents){
		ArrayList<Event> daysEvents = new ArrayList<Event>();
		for(Event event : allEvents.getEventList()){
			if(event.getStart().withTimeAtStartOfDay().equals(getDate())){
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
