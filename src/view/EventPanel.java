package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import org.joda.time.format.DateTimeFormat;

import controller.EventController;
import model.Event;

public class EventPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel nameLabel, dateLabel, startLabel, endLabel, periodLabel, participantLabel, locationLabel;
	private JTextArea nameField, participantField, locationField;
	private TextField dayField, monthField, yearField, startHoursField, startMinsField, endField, periodHoursField, periodMinsField;
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
		periodLabel = new JLabel("Duration");
		participantLabel = new JLabel("Participants");
		locationLabel = new JLabel("Location");
		
		setLayout(new GridLayout(8, 2));
		
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
		setFocusable(true);
		addKeyListener(getUpdateKeyListener());
		for(Component c : getComponents()){
			if(c.getClass() == TextField.class){
				c.setFocusable(true);
				c.addKeyListener(getUpdateKeyListener());
			}
		}
	}

	public JTextArea getNameField() {
		nameField = new JTextArea();
		nameField.setLineWrap(true);
		nameField.setWrapStyleWord(true);
		nameField.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		nameField.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		nameField.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
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

	public JTextArea getParticipantField() {
		participantField = new JTextArea();
		participantField.setLineWrap(true);
		participantField.setWrapStyleWord(true);
		participantField.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		participantField.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		participantField.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
		if(thisEvent.getParticipants() != null){
			String participantList = "";
			for(String participant : thisEvent.getParticipants()){
				participantList = participantList + participant + ", ";
			}
			participantField.setText(participantList.substring(0, participantList.length() - 2));
		}
		return participantField;
	}

	public JTextArea getLocationField() {
		locationField = new JTextArea();
		locationField.setLineWrap(true);
		locationField.setWrapStyleWord(true);
		locationField.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		locationField.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		locationField.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
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
	
	public KeyListener getUpdateKeyListener(){
		return new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					e.consume();
					updateBtn.doClick();
				}
				if(e.getKeyCode() == KeyEvent.VK_DELETE){
					e.consume();
					deleteBtn.doClick();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					e.consume();
					updateBtn.doClick();
				}
				if(e.getKeyCode() == KeyEvent.VK_DELETE){
					e.consume();
					deleteBtn.doClick();
				}
			}
		};
	}
	
}
