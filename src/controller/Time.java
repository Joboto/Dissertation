package controller;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

public enum Time {
	
	TWENTYFOURHOUR("(at |from )?[1-2]?[0-9]:[0-6][0-9]", twentyFourHours()),
	TWELVEHOUR("(at |from )?1?[0-9]:[0-6][0-9][aApP][mM]", twelveHours()),
	HOURONLY("(at |from )?1?[0-9][aApP][mM]", hoursOnly()),
	JUSTNUMBER("at 1?[0-9]", justNumber());
	
	private String regex;
	private DateTimeFormatter format;
	
	private Time(String rgx, DateTimeFormatter fmt){
		regex = rgx;
		format = fmt;
	}
	
	public String regex(){
		return regex;
	}
	
	public DateTimeFormatter format(){
		return format;
	}
	
	private static DateTimeFormatter twentyFourHours(){
		return new DateTimeFormatterBuilder()
			.appendOptional(at())
			.appendOptional(from())
			.appendHourOfDay(1)
			.appendLiteral(':')
			.appendMinuteOfHour(1)
			.toFormatter();
	}
	
	private static DateTimeFormatter twelveHours(){
		return new DateTimeFormatterBuilder()
			.appendOptional(at())
			.appendOptional(from())
			.appendClockhourOfHalfday(1)
			.appendLiteral(':')
			.appendMinuteOfHour(1)
			.appendHalfdayOfDayText()
			.toFormatter();
	}

	private static DateTimeFormatter hoursOnly(){
		return new DateTimeFormatterBuilder()
			.appendOptional(at())
			.appendOptional(from())
			.appendClockhourOfHalfday(1)
			.appendHalfdayOfDayText()
			.toFormatter();
	}

	private static DateTimeFormatter justNumber() {
		return new DateTimeFormatterBuilder()
			.appendOptional(at())
			//.appendOptional(from())
			//.appendOptional(until())
			//.appendOptional(till())
			.appendClockhourOfDay(1)
			.toFormatter();
	}

	private static DateTimeParser at(){
		return new DateTimeFormatterBuilder()
			.appendLiteral("at ")
			.toParser();
	}
	
	private static DateTimeParser from(){
		return new DateTimeFormatterBuilder()
			.appendLiteral("from ")
			.toParser();
	}
	
	private static DateTimeParser until(){
		return new DateTimeFormatterBuilder()
			.appendLiteral("until ")
			.toParser();
	}
	
	private static DateTimeParser till(){
		return new DateTimeFormatterBuilder()
			.appendLiteral("till ")
			.toParser();
	}
	
}
