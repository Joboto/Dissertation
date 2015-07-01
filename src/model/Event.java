package model;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;

public class Event implements Comparable<Event>{
	/**
	 * Might also need a duration (JodaTime), from which you could work out endTime, or vice versa...
	 */
	//private DateTime dateTime;
	private Interval interval;
	private LocalTime time;
	private LocalDate day;
	private Period period;
	private String name;
	
	/*public Event(DateTime dt, String name){
		setDateTime(dt);
		setInterval(new Interval(dt, Period.hours(1)));
		setName(name);
	}*/
	
	public Event(Interval interval, String name){
		setInterval(interval);
		setDay(interval.getStart().toLocalDate());
		setName(name);
	}
	
	public Event(LocalDate day, String name){
		setDay(day);
		setName(name);
	}
	
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
	
	/*public DateTime getDateTime() {
		return this.dateTime;
	}
	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
		setDay(dateTime.toLocalDate());
	}*/
	public String getTitle(){
		String title = getName();
		if(getInterval() != null){
			title = getInterval().getStart().toString(DateTimeFormat.shortTime())+" "+title;
		} /*else {
			if(getDay() != null){
				title = getDay().toString(DateTimeFormat.shortDate())+" "+title;
			}
		}*/
		return title;
	}
	
	public DateTime getStart(){
		if(getInterval() != null){
			return getInterval().getStart();
		} else {
			if(getDay() != null){
				return getDay().toDateTimeAtStartOfDay();
			} else {
				return DateTime.now();
			}
		}
	}
	public Interval getInterval() {
		return this.interval;
	}
	public void setInterval(Interval interval) {
		this.interval = interval;
	}
	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
		setPeriod(Period.hours(1));
		setInterval(new Interval(getDay().toDateTime(getTime()), getPeriod()));
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
		this.name = name;
	}

	@Override
	public int compareTo(Event o) {
		if(getDay() == null){return -1;}
		if(o.getDay() == null){return 1;}
		if(getInterval() != null && o.getInterval() != null){
			return orderByStartTime(o);
		} else {
			return orderByDay(o);
		}
	}
	
	private int orderByDay(Event o){
		int output = 0;
		if(getDay().isBefore(o.getDay())){output = -1;}
		if(getDay().isEqual(o.getDay())){output = 0;}
		if(getDay().isAfter(o.getDay())){output = 1;}
		return output;
	}
	
	private int orderByStartTime(Event o){
		int output = 0;
		DateTime mine = getStart();
		DateTime theirs = o.getStart();
		if(mine.isBefore(theirs)){output = -1;}
		if(mine.isEqual(theirs)){output = 0;}
		if(mine.isAfter(theirs)){output = 1;}
		return output;
	}
}
