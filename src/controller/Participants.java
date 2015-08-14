package controller;

public enum Participants {
	/**
	 * Simple enumerator for storing regular expressions of prepositions indicating participants.
	 * Currently only one preposition. 
	 */
	WITH(" with "),
	//MEET("[Mm]eet(ing)? ")
	;

	private String regex;
	
	private Participants(String rgx){
		regex = rgx;
	}
	
	public String regex(){
		return regex;
	}
	
	public static String all(){
		String output = "(";
		for(Participants loc : Participants.values()){
			output = output + loc.regex + "|";
		}
		output = output.substring(0, output.length() - 1)+")";
		return output;
	}
}
