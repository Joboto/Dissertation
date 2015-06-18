package model;

import java.util.Observable;

import org.joda.time.DateTime;

public class MyJodaCal extends Observable {
	private DateTime selectedDate;
	private EventQueue events;

}
