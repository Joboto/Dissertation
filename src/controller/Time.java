package controller;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

public enum Time {
	/**
	 * To be expanded.
	 * For finding time formats in the input string.
	 * Each enumeration has a regular expression and a corresponding dateTimeFormatter (JodaTime).
	 * If the regex is matched, the dateTimeFormatter can then be used to parse it.
	 */
	TWELVEHOUR("(at )?1?[0-9]:[0-6][0-9][aApP][mM]", twelveHours()),
	TWENTYFOURHOUR("(at )?[1-2]?[0-9]:[0-6][0-9]", twentyFourHours()),
	HOURONLY("(at )?1?[0-9][aApP][mM]", hoursOnly()),
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
			.appendHourOfDay(1)
			.appendLiteral(':')
			.appendMinuteOfHour(1)
			.toFormatter();
	}
	
	private static DateTimeFormatter twelveHours(){
		return new DateTimeFormatterBuilder()
			.appendOptional(at())
			.appendClockhourOfHalfday(1)
			.appendLiteral(':')
			.appendMinuteOfHour(1)
			.appendHalfdayOfDayText()
			.toFormatter();
	}

	private static DateTimeFormatter hoursOnly(){
		return new DateTimeFormatterBuilder()
			.appendOptional(at())
			.appendClockhourOfHalfday(1)
			.appendHalfdayOfDayText()
			.toFormatter();
	}

	private static DateTimeFormatter justNumber() {
		return new DateTimeFormatterBuilder()
			.appendLiteral("at ")
			.appendClockhourOfDay(1)
			.toFormatter();
	}

	private static DateTimeParser at(){
		return new DateTimeFormatterBuilder()
			.appendLiteral("at ")
			.toParser();
	}
}
