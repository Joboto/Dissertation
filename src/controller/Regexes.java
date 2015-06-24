package controller;

public enum Regexes {
	DAY("([Mm]on|[Tt]ues|[Ww]ednes|[Tt]hurs|[Ff]ri|[Ss]atur|[Ss]un)day");
	
	private String regex;
	
	private Regexes(String string){
		regex = string;
	}
	
	public String asString(){
		return regex;
	}
}
