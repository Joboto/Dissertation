package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.joda.time.DateTime;
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
			String[] array = e.getActionCommand().split(":", 2);
			if(array.length == 2){
				System.out.println("Adding event: "+array[1]);
				cal.addEvent(selectedDay.plusDays(2), array[1]);
			}
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
	
	

}
