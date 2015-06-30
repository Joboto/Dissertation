package controller;

public enum Date {
	DAYofWEEK("([Mm]on|[Tt]ues|[Ww]ednes|[Tt]hurs|[Ff]ri|[Ss]atur|[Ss]un)day"),
	DAYandMONTH("[1-3]?[0-9](st|nd|rd|th)? ([Jj]anuary|[Ff]ebruaru|[Mm]arch|[Aa]pril|[Mm]ay|[Jj]une|[Jj]uly|[Aa]ugust|[Ss]eptember|[Oo]ctober|[Nn]ovember|[Dd]ecember)"),
	MONTHandDAY("([Jj]anuary|[Ff]ebruaru|[Mm]arch|[Aa]pril|[Mm]ay|[Jj]une|[Jj]uly|[Aa]ugust|[Ss]eptember|[Oo]ctober|[Nn]ovember|[Dd]ecember) [1-3]?[0-9](st|nd|rd|th)?"),
	TOMORROW("[Tt]morrow");
	
	private String regex;
	
	private Date(String string){
		regex = string;
	}
	
	public String asString(){
		return regex;
	}
}
