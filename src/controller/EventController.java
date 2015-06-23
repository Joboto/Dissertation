package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

import model.Event;
import model.MyJodaCal;

public class EventController implements ActionListener {
	private MyJodaCal cal;
	private DateTime selectedDay;
	private DateTimeExtractor extr;
	
	public EventController(MyJodaCal c){
		cal = c;
		selectedDay = cal.getSelectedDate();
		extr = new DateTimeExtractor();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			if(e.getActionCommand().equals("previous")) {selectedDay = selectedDay.minusMonths(1);}
			if(e.getActionCommand().equals("today")) {selectedDay = new DateTime();}
			if(e.getActionCommand().equals("next")) {selectedDay = selectedDay.plusMonths(1);}
			cal.setSelectedDate(selectedDay);
			System.out.println(cal.getEvents().getEventList().size() + " events.");
			//cal.notifyObservers();
		} catch(Exception ex){
			ex.printStackTrace();
		}

	}
	
	public void addEvent(String input){
		DateTime dt = extr.extractDateTime(input);
		cal.addEvent(dt, input);
		cal.setSelectedDate(selectedDay);//dont like this; think it should be cal.notifyObservers or something.
	}
	

}
