package controller;

public enum Preps {
	/**
	 * Prepositions. Starting with just 'on' and 'at'. Eventually may include after, before, with etc.
	 */
	ON("[Oo]n"), AT("[Aa]t");
	
	private String regex;
	
	private Preps(String string){
		regex = string;
	}
	
	public String asString(){
		return regex;
	}
}
