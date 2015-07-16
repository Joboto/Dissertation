package controller;

public enum PrepCombo {
	/**
	 * For relations, participants and location plus combinations...
	 */
	//LOClast(rel()+"|"+prt()+".*"+loc()),
	//PRTSlast(rel()+"|"+loc()+".*"+prt()),
	//RELlast(loc()+"|"+prt()+".*"+rel()),
	LOC(loc()),
	PRTS(prt()),
	REL(rel()),
	//ALL(rel()+"|"+prt()+"|"+loc()),
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
		return Participants.WITH.regex();
	}
	
	private static String rel(){
		return Relation.all();
	}
}
