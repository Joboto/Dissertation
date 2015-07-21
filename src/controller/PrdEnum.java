package controller;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.PeriodFormat;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public enum PrdEnum {
	/**
	 * 
	 */
	HOURS("[0-9]+ hours?", hours()),
	MINUTES("[0-9]+ minutes?", minutes()),
	//DAYS
	//MONTHS
	//YEARS
	;
	
	private String regex;
	private PeriodFormatter format;
	
	private PrdEnum(String rgx, PeriodFormatter fmt){
		regex = rgx;
		format = fmt;
	}
	
	public String regex(){
		return regex;
	}
	
	public PeriodFormatter format(){
		return format;
	}
	
	public static String all(){
		String output = "(";
		for(PrdEnum prd : PrdEnum.values()){
			output = output + prd.regex + "|";
		}
		output = output.substring(0, output.length() - 1)+")";
		return output;
	}
	
	private static PeriodFormatter hours(){
		return new PeriodFormatterBuilder()
			.appendPrefix(new String[]{"[0-9]+", "[0-9]+"}, new String[]{"for ", ""})
			.appendHours()
			.appendSuffix(" hour", " hours")
			.toFormatter();
	}
	
	private static PeriodFormatter minutes(){
		return new PeriodFormatterBuilder()
			.appendPrefix(new String[]{"[0-9]+", "[0-9]+", "[0-9]+"}, new String[]{"for ", " and ", ""})
			.appendMinutes()
			.appendSuffix(" minute", " minutes")
			.toFormatter();
	}
}
