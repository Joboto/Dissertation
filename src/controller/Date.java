package controller;

public enum Date {
	//adding in TODAY for completeness and to give a default value.
	//EMPTY(""),
	DAYofWEEK("([Mm]on|[Tt]ues|[Ww]ednes|[Tt]hurs|[Ff]ri|[Ss]atur|[Ss]un)day", "EEEE"),
	DAYandMONTH("[1-3]?[0-9] ([Jj]anuary|[Ff]ebruary|[Mm]arch|[Aa]pril|[Mm]ay|[Jj]une|[Jj]uly|[Aa]ugust|[Ss]eptember|[Oo]ctober|[Nn]ovember|[Dd]ecember)", "d MMMM"),
	MONTHandDAY("([Jj]anuary|[Ff]ebruaru|[Mm]arch|[Aa]pril|[Mm]ay|[Jj]une|[Jj]uly|[Aa]ugust|[Ss]eptember|[Oo]ctober|[Nn]ovember|[Dd]ecember) [1-3]?[0-9]", "MMMM d"),
	//TOMORROW("[Tt]omorrow"),
	//TODAY("[Tt]oday")
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
