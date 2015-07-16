package controller;

public enum Participants {
	/**
	 * 
	 */
	WITH("with "),
	;

	private String regex;
	
	private Participants(String rgx){
		regex = rgx;
	}
	
	public String regex(){
		return regex;
	}
}
