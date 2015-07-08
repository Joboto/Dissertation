package controller;

public enum Participants {
	/**
	 * By putting MEETING first, a meeting will always have the participants included in the event name.
	 * Could take out the word meet(ing), and just have particpant names...
	 */
	MEETING("[Mm]eet(ing)? "+standardName()),
	MANY("with "+standardName()+"(((, )|( and ))"+standardName()+")+"),
	ONE("with "+standardName()),
	;

	private String regex;
	
	private Participants(String rgx){
		regex = rgx;
	}
	
	public String regex(){
		return regex;
	}
	
	private static String standardName(){
		return "([A-Za-z][a-z-]+)( [A-Za-z][a-z-']+)*";
	}
}
