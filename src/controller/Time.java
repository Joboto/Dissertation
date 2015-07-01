package controller;

public enum Time {
	
	TWENTYFOURHOUR("[1-2]?[0-9]:[0-6][0-9][^a-zA-Z]", "H:m"),
	TWELVEHOUR("1?[0-9]:[0-6][0-9][aApP][mM]", "h:ma");
	
	private String regex;
	private String format;
	
	private Time(String rgx, String fmt){
		regex = rgx;
		format = fmt;
	}
	
	public String regex(){
		return regex;
	}
	
	public String format(){
		return format;
	}
}
