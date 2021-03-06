package model;

import java.util.ArrayList;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;

import controller.TimePhrase;

public class Event implements Comparable<Event>{
	/**
	 * Implements comparable so that events can be ordered within the event queue.
	 * To avoid null pointer exceptions, and facilitate ordering, method getStart returns mid-night 
	 * if event has a day but no time, and 'start of time' if event has no day.
	 * Does not have an attribute for 'end time' but a method calculates based on start and period.
	 */
	private LocalTime time;
	private LocalDate day;
	private Period period;
	private String name;
	private String location;
	private String agenda;
	private ArrayList<String> participants;
	
	private TimePhrase phrase;
	
	public Event(){}
	
	public Event(String name){
		setName(name);
	}
	
	public String toString(){
		String output = getName();
		output = output + " " + getStart().dayOfWeek().getAsShortText();
		output = output + " " + getStart().getDayOfMonth();
		output = output + " " + getStart().monthOfYear().getAsShortText();
		output = output + " " + getStart().getYear();
		return output;
	}
	
	public String getTitle(){
		String title = getName();
		if(getTime() != null){
			/*if(getPeriod() != null){
				title = "-"+getEnd().toString(DateTimeFormat.shortTime())+" "+title;
			}*/
			title = getTime().toString(DateTimeFormat.shortTime())+" "+title;
		} /*else {
			if(getDay() != null){
				title = getDay().toString(DateTimeFormat.shortDate())+" "+title;
			}
		}*/
		return title;
	}
	
	public DateTime getStart(){
		if(getTime() != null){
			return getDay().toDateTime(getTime());
		} else {
			if(getDay() != null){
				return getDay().toDateTimeAtStartOfDay();
			} else {
				return DateTime.now().withMillis(0);
			}
		}
	}
	
	public DateTime getEnd(){
		if(getPeriod() != null){
			return getStart().plus(getPeriod());
		} else {
			return null;
		}
	}
	
	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public LocalDate getDay() {
		return this.day;
	}
	
	public void setDay(LocalDate day) {
		this.day = day;
	}
	
	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		name = name.trim();
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		this.name = name;
	}

	public String getLocation() {
		return this.location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public String getAgenda() {
		return this.agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	public ArrayList<String> getParticipants() {
		return participants;
	}

	public void addParticipant(String participant) {
		if(this.participants == null){
			this.participants = new ArrayList<String>();
		}
		participant = participant.substring(0, 1).toUpperCase()+participant.substring(1);
		this.participants.add(participant);
	}

	public TimePhrase getTimePhrase() {
		return phrase;
	}

	public void setTimePhrase(TimePhrase phrase) {
		this.phrase = phrase;
	}

	@Override
	public int compareTo(Event o) {
		int output = 0;
		if(getStart().isBefore(o.getStart())){output = -1;}
		if(getStart().isEqual(o.getStart())){output = 0;}
		if(getStart().isAfter(o.getStart())){output = 1;}
		return output;
	}
}
