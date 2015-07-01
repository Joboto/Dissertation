package controller;

public enum DatePhrase {
	
	DAYofWEEK("([Mm]on|[Tt]ues|[Ww]ednes|[Tt]hurs|[Ff]ri|[Ss]atur|[Ss]un)day"),
	TOMORROW("[Tt]omorrow");
	
	private String regex;
	
	private DatePhrase(String rgx){
		regex = rgx;
	}
	
	public String regex(){
		return regex;
	}
	
}
