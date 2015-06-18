package model;

import java.util.Observable;

import org.joda.time.DateTime;

public class MyJodaCal extends Observable {
	private DateTime selectedDate;
	private EventQueue events;
	
	public MyJodaCal(){
		setSelectedDate(new DateTime());
		setEvents(new EventQueue());
	}
	
	public void addEvent(DateTime dt, String name){
		getEvents().addEvent(new Event(dt, name));
	}

	public DateTime getSelectedDate() {
		return this.selectedDate;
	}

	public void setSelectedDate(DateTime selectedDate) {
		this.selectedDate = selectedDate;
		setChanged();
		notifyObservers();
		clearChanged();
	}

	public EventQueue getEvents() {
		return this.events;
	}

	public void setEvents(EventQueue events) {
		this.events = events;
	}

}
