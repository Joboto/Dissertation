package controller;

import org.joda.time.LocalTime;

public enum TimePhrase {
	/**
	 * This could be where some learning takes place with things like, after work
	 * ...or might want a different enum or class altogether 
	 */
	NOON("(at )?(([Nn]oon)|([Mm]idday))", LocalTime.parse("12:00")),
	MIDNIGHT("(at )?[Mm]idnight", LocalTime.parse("00:00")),
	MORNING("([Ff]irst thing)|(([Ii]n the )?morning)", LocalTime.parse("08:00")),
	//THISAFTERNOON
	//AFTERLUNCH
	//AFTERWORK
	//TONIGHT
	//THISEVENING
	;
	
	private String regex;
	private LocalTime time;
	
	private TimePhrase(String rgx, LocalTime lt){
		regex = rgx;
		time = lt;
	}
	
	public String regex(){
		return regex;
	}
	
	public LocalTime time(){
		return time;
	}

}
