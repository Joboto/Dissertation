package controller;

public enum Location {
	/**
	 * Simple enumerator for storing regular expressions of prepositions indicating location.
	 * Currently only two preposition. 
	 */
	AT(" at "),
	IN(" in "),
	;
	
	private String regex;
	
	private Location(String rx){
		regex = rx;
	}
	
	public String regex(){
		return regex;
	}
	
	public static String all(){
		String output = "(";
		for(Location loc : Location.values()){
			output = output + loc.regex + "|";
		}
		output = output.substring(0, output.length() - 1)+")";
		return output;
	}
}
