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
			cal.setSelectedDate(selectedDay);
			System.out.println(cal.getEvents().getEventList().size() + " events.");
			//cal.notifyObservers();
		} catch(Exception ex){
			ex.printStackTrace();
		}

	}
	
	public void addEvent(String input){
		cal.addEvent(extractTimex(input), input);
		cal.setSelectedDate(selectedDay);
	}
	
	public DateTime extractTimex(String input){
		DateTime output = new DateTime();
		String time[] = getMatch(input, "[0-9]{1,2}:[0-9]{2}").split(":");
		int hours = Integer.parseInt(time[0]);
		int minutes = Integer.parseInt(time[1]);
		output = output.withTime(hours, minutes, 0, 0);
		//output = output.withTime(getHours(input), getMinutes(input), 0, 0);
		return output;
		
	}
	
	public int getHours(String input){
		String hours;
		if(getMatch(input, ":") == null){
			hours = getMatch(input, "[0-9]+");
			System.out.println("returning "+hours);
		} else {
			hours = getMatch(input, "[0-9]+:");
			System.out.println("Found: "+hours);
			hours = hours.substring(0, 0);
			System.out.println("returning "+hours);
		}
		return Integer.parseInt(hours);
		/*String[] hhmm = input.split(":");
		return Integer.parseInt(hhmm[0]);*/
	}
	
	public int getMinutes(String input){
		return 0;
	}
	
	public String getMatch(String input, String regex){
		String[] tokens = input.split(" ");
		for(int loop = 0; loop < tokens.length; loop++){
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(tokens[loop]);
			if(m.matches()){
				return tokens[loop];
			}
		}
		return null;
	}

}
