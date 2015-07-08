package controller;

import org.joda.time.LocalTime;

public enum TimePhrase {
	/**
	 * This could be where some learning takes place with things like, after work
	 * ...or might want a different enum or class altogether 
	 */
	NOON("(at )?(([Nn]oon)|([Mm]idday))", LocalTime.parse("12:00")),
	MIDNIGHT("(at )?[Mm]idnight", LocalTime.parse("00:00")),
	MORNING("([Ff]irst thing( in the morning)?)|(([Ii]n the )?morning)", LocalTime.parse("08:00")),
	AFTERNOON("([Tt]his afternoon,?)|([Aa]fter lunch,?)", LocalTime.parse("14:00")),
	EVENING("([Aa]fter work,?)|([Tt]his evening,?)", LocalTime.parse("17:00")),
	//TONIGHT...
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
