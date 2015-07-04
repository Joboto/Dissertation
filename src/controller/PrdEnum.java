package controller;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public enum PrdEnum {
	/**
	 * 
	 */
	HOURS("(for )?[0-9]+ hours?", hours());
	
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
	
	private static PeriodFormatter hours(){
		return new PeriodFormatterBuilder()
			.appendPrefix(new String[]{"[0-9]+", "[0-9]+"}, new String[]{"for ", ""})
			.appendHours()
			.appendSuffix(" hour", " hours")
			.toFormatter();
	}
}
