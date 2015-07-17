package controller;

public enum DatePhrase {
	
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
