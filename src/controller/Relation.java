package controller;

public enum Relation {

	BEFORE("before "),
	AFTER("after "),
	;
	
	private String regex;
	
	private Relation(String rx){
		regex = rx;
	}
	
	public String regex(){
		return regex;
	}
	
	public static String all(){
		String output = "(";
		for(Relation loc : Relation.values()){
			output = output + loc.regex + "|";
		}
		output = output.substring(0, output.length() - 1) + ")";
		return output;
	}
}
