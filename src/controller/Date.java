package controller;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

public enum Date {
	/**
	 * To be expanded.
	 * For finding date formats in the input string.
	 * Each enumeration has a regular expression and a corresponding dateTimeFormatter (JodaTime).
	 * If the regex is matched, the dateTimeFormatter can then be used to parse it.
	 */
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
		return "([Jj]an(uary)?|[Ff]eb(ruary)?|[Mm]ar(ch)?|[Aa]pr(il)?|[Mm]ay|[Jj]un(e)?|[Jj]ul(y)?|[Aa]ug(ust)?|[Ss]ep(tember)?|[Oo]ct(ober)?|[Nn]ov(ember)?|[Dd]ec(ember)?)";
	}
	
	private static DateTimeFormatter dayAndMonth(){
		return new DateTimeFormatterBuilder()
			.appendOptional(suffix("on "))
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
			.appendOptional(suffix("on "))
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
			.appendOptional(suffix("on "))
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
