package controller;

public enum Date {
	DAYandMONTH("[1-3]?[0-9] ([Jj]anuary|[Ff]ebruary|[Mm]arch|[Aa]pril|[Mm]ay|[Jj]une|[Jj]uly|[Aa]ugust|[Ss]eptember|[Oo]ctober|[Nn]ovember|[Dd]ecember)", "d MMMM"),
	MONTHandDAY("([Jj]anuary|[Ff]ebruaru|[Mm]arch|[Aa]pril|[Mm]ay|[Jj]une|[Jj]uly|[Aa]ugust|[Ss]eptember|[Oo]ctober|[Nn]ovember|[Dd]ecember) [1-3]?[0-9]", "MMMM d"),
	DDMMYY("[1-3]?[0-9]/[0-1]?[0-9]/[0-9]{2}", "d/M/yy"),
	;
	
	private String regex;
	private String format;
	
	private Date(String rgx, String fmt){
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
