package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.joda.time.DateTime;

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
			if(e.getActionCommand().equals("previousMonth")){
				selectedDay = selectedDay.minusMonths(1);
			}
			else if(e.getActionCommand().equals("nextMonth")) selectedDay = selectedDay.plusMonths(1);
			else selectedDay = new DateTime();
		} catch(Exception ex){
			ex.printStackTrace();
		}

	}

}
