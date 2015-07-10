package controller;

public enum DatePhrase {
	
	DAYofWEEK("([Oo]n )?([Mm]on|[Tt]ues|[Ww]ednes|[Tt]hurs|[Ff]ri|[Ss]atur|[Ss]un)day"),
	TOMORROW("[Tt]omorrow"),
	TODAY("[Tt]oday");
	//NEXTweekDAY
	//NEXTWEEK
	//WEEKTOMORROW
	//INxWEEKS
	
	private String regex;
	
	private DatePhrase(String rgx){
		regex = rgx;
	}
	
	public String regex(){
		return regex;
	}
	
}
