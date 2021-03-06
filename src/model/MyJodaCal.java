package model;

import java.util.ArrayList;
import java.util.Observable;

import org.joda.time.DateTime;

public class MyJodaCal extends Observable {
	/**
	 * Central class for Model. Contains an ArrayList of all events.
	 * Is observed by the View and has 'selectedMonth' which keeps track of the month the user wishes to view.
	 * Can return lists of events for a particular day given a DateTime or integer representing a day.
	 */
	private DateTime selectedDate;
	private EventQueue events;
	private Month selectedMonth;
	
	public MyJodaCal(){
		setSelectedDate(new DateTime());
		setEvents(new EventQueue());
		
	}
	
	/*public void addEvent(DateTime dt, String name){
		getEvents().addEvent(new Event(dt, name));
	}*/
	
	public void addEvent(Event event){
		getEvents().addEvent(event);
	}
	
	public ArrayList<Event> getDaysEvents(int dayOfMonth){
		return getSelectedMonth().getDay(dayOfMonth).getEvents(getEvents());
	}
	
	public ArrayList<Event> getDaysEvents(DateTime givenDay){
		return getSelectedMonth().getDay(givenDay).getEvents(getEvents());
	}
	
	public ArrayList<Event> getUnscheduledEvents(){
		ArrayList<Event> list = new ArrayList<Event>();
		for(Event event : getEvents().getEventList()){
			if(event.getDay() == null){
				list.add(event);
			}
		}
		return list;
	}

	public DateTime getSelectedDate() {
		return this.selectedDate;
	}

	public void setSelectedDate(DateTime selectedDate) {
		this.selectedDate = selectedDate;
		setSelectedMonth(new Month(getSelectedDate()));
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

	public Month getSelectedMonth() {
		return this.selectedMonth;
	}

	public void setSelectedMonth(Month selectedMonth) {
		this.selectedMonth = selectedMonth;
	}

}
