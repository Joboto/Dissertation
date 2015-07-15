package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;

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
			//cal.notifyObservers();
		} catch(Exception ex){
			ex.printStackTrace();
		}

	}
	
	public void addEvent(String input){
		goodBye(input);
		Event toAdd = EventExtractor.extract(input);
		if(toAdd.getTime() == null){
			DateTime dt;
			if(toAdd.getDay() == null){
				dt = DateTime.now();
			} else {
				dt = toAdd.getDay().toDateTime(toAdd.getTime());
			}
			toAdd = EventRelator.compare(toAdd, cal.getDaysEvents(dt));
		}
		//toAdd = MeetingBuilder.check(toAdd);
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
			System.out.println("Updating start time.");
			int hours = Integer.parseInt(fields.get("startHours"));
			int minutes = Integer.parseInt(fields.get("startMinutes"));
			LocalTime update = new LocalTime(hours, minutes);
			TimePhrase phrase = event.getTimePhrase();
			newEvent.setTime(update);
			if(phrase != null){
				System.out.println("Correcting '"+phrase.toString()+"' to "+update.toString());
				phrase.correction(update);
			}
		}
		if(!fields.get("periodHours").isEmpty()){
			int hours = Integer.parseInt(fields.get("periodHours"));
			int minutes = Integer.parseInt(fields.get("startMinutes"));
			newEvent.setPeriod(Period.hours(hours).withMinutes(minutes));
		}
		if(!fields.get("participants").isEmpty()){
			String[] partspts = fields.get("participants").split(", ");
			for(String pt : partspts){
				newEvent.addParticipant(pt);
			}
			
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
	
	public void goodBye(String input){
		if(input.toLowerCase().matches("goodbye|quit|exit")){
			JOptionPane.showMessageDialog(null, "See ya!", "Goodbye", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
	}
	

}
