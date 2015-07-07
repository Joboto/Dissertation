package controller;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

public enum Date {
	DAYandMONTH("(on )?[1-3]?[0-9](st|nd|rd|th)? "+month(), dayAndMonth()),
	MONTHandDAY("(on )?"+month()+",? [1-3]?[0-9](st|nd|rd|th)?", monthAndDay()),
	DDMMYY("(on )?[1-3]?[0-9]/[0-1]?[0-9]/[0-9]{2,4}", ddmmyy()),
	;
	
	private String regex;
	private DateTimeFormatter format;
	
	private Date(String rgx, DateTimeFormatter fmt){
		regex = rgx;
		format = fmt;
	}
	
	public String regex(){
		return regex;
	}
	
	public DateTimeFormatter format(){
		return format;
	}
	
	private static String month(){
		return "([Jj]anuary|[Ff]ebruary|[Mm]arch|[Aa]pril|[Mm]ay|[Jj]une|[Jj]uly|[Aa]ugust|[Ss]eptember|[Oo]ctober|[Nn]ovember|[Dd]ecember)";
	}
	
	private static DateTimeFormatter dayAndMonth(){
		return new DateTimeFormatterBuilder()
			.appendDayOfMonth(1)
			.appendOptional(suffix("st"))
			.appendOptional(suffix("nd"))
			.appendOptional(suffix("rd"))
			.appendOptional(suffix("th"))
			.appendLiteral(' ')
			.appendMonthOfYearText()
			.toFormatter()
			.withDefaultYear(DateTime.now().getYear());
	}
	
	private static DateTimeFormatter monthAndDay(){
		return new DateTimeFormatterBuilder()
			.appendMonthOfYearText()
			.appendOptional(suffix(","))
			.appendLiteral(' ')
			.appendDayOfMonth(1)
			.appendOptional(suffix("st"))
			.appendOptional(suffix("nd"))
			.appendOptional(suffix("rd"))
			.appendOptional(suffix("th"))
			.toFormatter()
			.withDefaultYear(DateTime.now().getYear());
	}
	
	private static DateTimeFormatter ddmmyy(){
		return new DateTimeFormatterBuilder()
			.appendDayOfMonth(1)
			.appendLiteral('/')
			.appendMonthOfYear(1)
			.appendLiteral('/')
			.appendTwoDigitYear(2050, true) //true means lenient; can pick out yy or yyyy
			.toFormatter();
	}
	
	private static DateTimeParser suffix(String suffix){
		return new DateTimeFormatterBuilder()
			.appendLiteral(suffix)
			.toParser();
	}

}
