package controller;

import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public enum PrdEnum {
	/**
	 * For finding period statements in the input string.
	 * Each enumeration has a regular expression and a corresponding periodFormatter (JodaTime).
	 * If the regex is matched, the periodFormatter can then be used to parse it.
	 */
	DAYS("[0-9]+ days?", days()),
	HOURS("[0-9]+ hours?", hours()),
	MINUTES("[0-9]+ minutes?", minutes()),
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
	
	private static PeriodFormatter days(){
		return new PeriodFormatterBuilder()
			.appendPrefix(new String[]{"[0-9]+", "[0-9]+"}, new String[]{"for ", ""})
			.appendDays()
			.appendSuffix(" day", " days")
			.toFormatter();
	}
	
	private static PeriodFormatter hours(){
		return new PeriodFormatterBuilder()
		.appendPrefix(new String[]{"[0-9]+", "[0-9]+", "[0-9]+"}, new String[]{"for ", "and ", ""})
			.appendHours()
			.appendSuffix(" hour", " hours")
			.toFormatter();
	}
	
	private static PeriodFormatter minutes(){
		return new PeriodFormatterBuilder()
			.appendPrefix(new String[]{"[0-9]+", "[0-9]+", "[0-9]+"}, new String[]{"for ", "and ", ""})
			.appendMinutes()
			.appendSuffix(" minute", " minutes")
			.toFormatter();
	}
}
