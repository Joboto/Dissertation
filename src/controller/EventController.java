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
import org.joda.time.LocalTime;
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
		Event newEvent = new Event(fields.get("name"));
		if(!fields.get("startDay").isEmpty()){
			int day = Integer.parseInt(fields.get("startDay"));
			int month = Integer.parseInt(fields.get("startMonth"));
			int year = Integer.parseInt(fields.get("startYear"));
			newEvent.setDay(new LocalDate(year, month, day));
		}
		if(!fields.get("startHours").isEmpty()){
			int hours = Integer.parseInt(fields.get("startHours"));
			int minutes = Integer.parseInt(fields.get("startMinutes"));
			newEvent.setTime(new LocalTime(hours, minutes));
		}
		if(!fields.get("periodHours").isEmpty()){
			int hours = Integer.parseInt(fields.get("periodHours"));
			int minutes = Integer.parseInt(fields.get("startMinutes"));
			newEvent.setPeriod(Period.hours(hours).withMinutes(minutes));
		}
		if(!fields.get("participants").isEmpty()){
			newEvent.setParticipants(fields.get("participants"));
		}
		if(!fields.get("location").isEmpty()){
			newEvent.setLocation(fields.get("location"));
		}
		System.out.println("adding "+newEvent.getTitle());
		cal.addEvent(newEvent);
		if(newEvent.getDay() != null){
			selectedDay = newEvent.getStart();
		}
		cal.setSelectedDate(selectedDay);
	}
	

}
