package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.*;
import java.util.HashMap;

import javax.swing.*;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.PeriodFormat;

import controller.EventController;
import model.Event;

public class EventPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel nameLabel, dateLabel, startLabel, endLabel, periodLabel, participantLabel, locationLabel;
	private TextField nameField, dayField, monthField, yearField, startHoursField, startMinsField, endField, periodHoursField, periodMinsField, participantField, locationField;
	private JPanel datePanel, startPanel, /*endPanel,*/ periodPanel;
	private JButton updateBtn, deleteBtn;
	private Event thisEvent;
	private EventController controller;
	
	public EventPanel(Event event, EventController ctrl) {
		thisEvent = event;
		controller = ctrl;
		
		nameLabel = new JLabel("Event title");
		dateLabel = new JLabel("Date");
		startLabel = new JLabel("Start time");
		endLabel = new JLabel("End time");
		periodLabel = new JLabel("Duration");//might want multiple for minutes etc.
		participantLabel = new JLabel("Participants");
		locationLabel = new JLabel("Location");
		
		setLayout(new GridLayout(8, 2));
		
		System.out.println("Adding labels and text fields");
		add(nameLabel);
		add(getNameField());
		add(dateLabel);
		add(getDatePanel());
		add(startLabel);
		add(getStartPanel());
		add(endLabel);
		add(getEndField());
		add(periodLabel);
		add(getPeriodPanel());
		add(participantLabel);
		add(getParticipantField());
		add(locationLabel);
		add(getLocationField());
		add(getUpdateBtn());
		add(getDeleteBtn());
	}

	public TextField getNameField() {
		nameField = new TextField();
		if(thisEvent.getName() != null){
			System.out.println("Event name: "+thisEvent.getName());
			nameField.setText(thisEvent.getName());
		}
		return nameField;
	}

	public JPanel getDatePanel() {
		datePanel = new JPanel(new GridLayout(2,3));
		datePanel.add(new JLabel("Day"));
		datePanel.add(new JLabel("Month"));
		datePanel.add(new JLabel("Year"));
		datePanel.add(getDayField());
		datePanel.add(getMonthField());
		datePanel.add(getYearField());
		return datePanel;
	}
	
	public TextField getDayField(){
		dayField = new TextField();
		if(thisEvent.getDay() != null){
			dayField.setText(thisEvent.getDay().getDayOfMonth()+"");
		}
		return dayField;
	}
	
	public TextField getMonthField(){
		monthField = new TextField();
		if(thisEvent.getDay() != null){
			monthField.setText(thisEvent.getDay().getMonthOfYear()+"");
		}
		return monthField;
	}

	public TextField getYearField(){
		yearField = new TextField();
		if(thisEvent.getDay() != null){
			yearField.setText(thisEvent.getDay().getYear()+"");
		}
		return yearField;
	}

	public JPanel getStartPanel() {
		startPanel = new JPanel(new GridLayout(2, 2));
		startPanel.add(new JLabel("Hour"));
		startPanel.add(new JLabel("Minutes"));
		startPanel.add(getStartHoursField());
		startPanel.add(getStartMinsField());
		return startPanel;
	}

	public TextField getStartHoursField() {
		startHoursField = new TextField();
		if(thisEvent.getTime() != null){
			startHoursField.setText(thisEvent.getTime().hourOfDay().getAsText());
		}
		return startHoursField;
	}

	public TextField getStartMinsField() {
		startMinsField = new TextField();
		if(thisEvent.getTime() != null){
			startMinsField.setText(thisEvent.getTime().minuteOfHour().getAsText());
		}
		return startMinsField;
	}

	public TextField getEndField() {
		endField = new TextField();
		if(thisEvent.getPeriod() != null && thisEvent.getTime() != null){
			endField.setText(thisEvent.getEnd().toString(DateTimeFormat.shortTime()));
		}
		endField.setEditable(false);
		return endField;
	}

	public JPanel getPeriodPanel() {
		periodPanel = new JPanel(new GridLayout(2, 2));
		periodPanel.add(new JLabel("Hours"));
		periodPanel.add(new JLabel("Minutes"));
		periodPanel.add(getPeriodHoursField());
		periodPanel.add(getPeriodMinsField());
		return periodPanel;
	}

	public TextField getPeriodHoursField() {
		periodHoursField = new TextField();
		if(thisEvent.getPeriod() != null){
			periodHoursField.setText(thisEvent.getPeriod().getHours()+"");
		}
		return periodHoursField;
	}

	public TextField getPeriodMinsField() {
		periodMinsField = new TextField();
		if(thisEvent.getPeriod() != null){
			periodMinsField.setText(thisEvent.getPeriod().getMinutes()+"");
		}
		return periodMinsField;
	}

	public TextField getParticipantField() {
		participantField = new TextField();
		if(thisEvent.getParticipants() != null){
			String participantList = "";
			for(String participant : thisEvent.getParticipants()){
				participantList = participantList + participant + ", ";
			}
			participantField.setText(participantList.substring(0, participantList.length() - 2));
		}
		return participantField;
	}

	public TextField getLocationField() {
		locationField = new TextField();
		if(thisEvent.getLocation() != null){
			locationField.setText(thisEvent.getLocation());
		}
		return locationField;
	}

	public JButton getDeleteBtn() {
		deleteBtn = new JButton("Delete Event");
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.deleteEvent(thisEvent);
				Window dialog = SwingUtilities.windowForComponent(deleteBtn);
				dialog.dispose();
			}
		});
		return deleteBtn;
	}

	public JButton getUpdateBtn() {
		updateBtn = new JButton("Update Event");
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String> fields = new HashMap<>();
				fields.put("name", nameField.getText());
				fields.put("startDay", dayField.getText());
				fields.put("startMonth", monthField.getText());
				fields.put("startYear", yearField.getText());
				fields.put("startHours", startHoursField.getText());
				fields.put("startMinutes", startMinsField.getText());
				fields.put("periodHours", periodHoursField.getText());
				fields.put("periodMinutes", periodMinsField.getText());
				fields.put("participants", participantField.getText());
				fields.put("location", locationField.getText());
				System.out.println(fields.get("name"));
				controller.updateEvent(thisEvent, fields);
				Window dialog = SwingUtilities.windowForComponent(updateBtn);
				dialog.dispose();
			}
		});
		return updateBtn;
	}
	
}
