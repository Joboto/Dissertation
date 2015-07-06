package controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.joda.time.DateTime;
import view.*;
import model.*;

public class EventController implements ActionListener {
	private MyJodaCal cal;
	private DateTime selectedDay;
	
	public EventController(MyJodaCal c){
		cal = c;
		selectedDay = cal.getSelectedDate();
		createView();
	}
	
	public void createView(){
		buildFrame("Calendar", 800, 500, new JodaViewFrame(cal, this), true);
	}
	
	public void createDayView(DateTime date){
		DayPanel dp = new DayPanel(cal.getDaysEvents(date), date, this);
		buildFrame("Day's events", 300, 500, dp, false);
	}
	
	public void buildFrame(String title, int xDim, int yDim, Component comp, boolean exitOnClose){
		JFrame calendarFrame = new JFrame();
        if(exitOnClose){
        	calendarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        calendarFrame.setTitle(title);
        calendarFrame.setResizable(true);
        calendarFrame.setPreferredSize(new Dimension(xDim, yDim));
        calendarFrame.add(comp);
        calendarFrame.pack();
        calendarFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
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
		Event toAdd = EventExtractor.extract(input);
		System.out.println("Adding: "+toAdd.getTitle());
		cal.addEvent(toAdd);
		if(toAdd.getDay() != null){
			selectedDay = toAdd.getStart();
		}
		
		cal.setSelectedDate(selectedDay);
	}

	public DateTime getSelectedDay() {
		return selectedDay;
	}
	

}
