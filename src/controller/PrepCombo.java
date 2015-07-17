package controller;

public enum PrepCombo {
	/**
	 * Creates conjunctions (unions) of all the regexes from each of the enums Location, Participants and Relation. 
	 */
	LOC(loc()),
	PRTS(prt()),
	REL(rel()),
	AGENDA("( to | for )"),
	;
	
	private String regex;
	
	private PrepCombo(String string){
		regex = string;
	}
	
	public String regex(){
		return regex;
	}
	
	private static String loc(){
		return Location.all();
	}
	
	private static String prt(){
		return Participants.all();
	}
	
	private static String rel(){
		return Relation.all();
	}
}
