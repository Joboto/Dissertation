package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.Period;

import model.Event;
import model.MyJodaCal;

public class EventController implements ActionListener {
	private MyJodaCal cal;
	private DateTime selectedDay;
	
	public EventController(MyJodaCal c){
		cal = c;
		selectedDay = cal.getSelectedDate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try{
			if(e.getActionCommand().equals("previous")) {selectedDay = selectedDay.minusMonths(1);}
			if(e.getActionCommand().equals("today")) {selectedDay = new DateTime();}
			if(e.getActionCommand().equals("next")) {selectedDay = selectedDay.plusMonths(1);}
			if(e.getActionCommand().equals("deleteEvent")){System.out.println(e.getSource().getClass().getName());}
			cal.setSelectedDate(selectedDay);
			System.out.println(cal.getEvents().getEventList().size() + " events.");
			//cal.notifyObservers();
		} catch(Exception ex){
			ex.printStackTrace();
		}

	}
	
	public void addEvent(String input){
		Event toAdd = EventExtractor.extract(input);
		System.out.println("Adding: "+toAdd.getTitle());
		cal.addEvent(toAdd);
		if(toAdd.getDay() != null){
			selectedDay = toAdd.getStart();
		}
		
		cal.setSelectedDate(selectedDay);
	}
	
	public void deleteEvent(Event event){
		cal.getEvents().removeEvent(event);
		cal.setSelectedDate(selectedDay);
	}

	public void updateEvent(Event event, HashMap<String, String>fields) {
		cal.getEvents().getEventList().remove(event);
		Event newEvent = new Event(event.getName());
		if(fields.get("dayField") != null){
			int day = Integer.parseInt(fields.get("dayField"));
			int month = Integer.parseInt(fields.get("monthField"));
			int year = Integer.parseInt(fields.get("yearField"));
			newEvent.setDay(new LocalDate(year, month, day));
		}
		cal.addEvent(newEvent);
		cal.setSelectedDate(selectedDay);
	}
	

}
