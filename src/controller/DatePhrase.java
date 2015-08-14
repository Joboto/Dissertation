package controller;

public enum DatePhrase {
	/**
	 * To be expanded.
	 * For finding specific date phrases. Each enumeration has a regular expression.
	 * The dateTimeExtractor can use JodaTime to parse a day of the week. 
	 */
	NEXTweekDAY("[Nn]ext "+days()+",?"),
	DAYofWEEK("([Oo]n |[Tt]his )?"+days()+",?"),
	TOMORROW("[Tt]omorrow"),
	TODAY("[Tt]oday"),
	//THISweekDAY
	//NEXTWEEK
	//WEEKTOMORROW
	//INxWEEKS
	;
	
	private String regex;
	
	private DatePhrase(String rgx){
		regex = rgx;
	}
	
	public String regex(){
		return regex;
	}
	
	private static String days(){
		return "([Mm]on|[Tt]ues|[Ww]ednes|[Tt]hurs|[Ff]ri|[Ss]atur|[Ss]un)day";
	}
	
}
