package view;

import java.awt.*;

import javax.swing.*;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.PeriodFormat;

import model.Event;

public class EventPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel nameLabel, startLabel, endLabel, periodLabel, participantLabel, locationLabel;
	private TextField nameField, startField, endField, periodField, participantField, locationField;
	private Event thisEvent;
	
	public EventPanel(Event event) {
		thisEvent = event;
		
		nameLabel = new JLabel("Event title");
		startLabel = new JLabel("Start time");
		endLabel = new JLabel("End time");
		periodLabel = new JLabel("Duration");//might want multiple for minutes etc.
		participantLabel = new JLabel("Participants");
		locationLabel = new JLabel("Location");
		
		setLayout(new GridLayout(6, 2));
		
		System.out.println("Adding labels and text fields");
		add(nameLabel);
		add(getNameField());
		add(startLabel);
		add(getStartField());
		add(endLabel);
		add(getEndField());
		add(periodLabel);
		add(getPeriodField());
		add(participantLabel);
		add(getParticipantField());
		add(locationLabel);
		add(getLocationField());
		
		
	}

	public TextField getNameField() {
		nameField = new TextField();
		if(thisEvent.getName() != null){
			System.out.println("Event name: "+thisEvent.getName());
			nameField.setText(thisEvent.getName());
		}
		return nameField;
	}

	public TextField getStartField() {
		startField = new TextField();
		if(thisEvent.getTime() != null){
			System.out.println("Start time: "+thisEvent.getTime().toString());
			startField.setText(thisEvent.getTime().toString(DateTimeFormat.shortTime()));
		}
		return startField;
	}

	public TextField getEndField() {
		endField = new TextField();
		if(thisEvent.getPeriod() != null){
			System.out.println("End time: "+thisEvent.getEnd().toString());
			endField.setText(thisEvent.getEnd().toString(DateTimeFormat.shortTime()));
		}
		return endField;
	}

	public TextField getPeriodField() {
		periodField = new TextField();
		if(thisEvent.getPeriod() != null){
			System.out.println("duration: "+thisEvent.getPeriod().toString());
			periodField.setText(thisEvent.getPeriod().toString(PeriodFormat.getDefault()));
		}
		return periodField;
	}

	public TextField getParticipantField() {
		participantField = new TextField();
		return participantField;
	}

	public TextField getLocationField() {
		locationField = new TextField();
		return locationField;
	}
	
}
