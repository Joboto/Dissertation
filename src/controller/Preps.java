package controller;

public enum Preps {
	/**
	 * Prepositions. Starting with just 'on' and 'at'. Eventually may include after, before, with etc.
	 * Might want to do something about 'next'...
	 */
	ON("[^a-zA-Z][Oo]n[^a-zA-Z]"), AT("[^a-zA-Z][Aa]t[^a-zA-Z]"), NEXT("[^a-zA-Z][Nn]ext[^a-zA-Z]");
	
	private String regex;
	
	private Preps(String string){
		regex = string;
	}
	
	public String asString(){
		return regex;
	}
}
