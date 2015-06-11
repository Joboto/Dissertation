package model;

import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Model extends Observable {
	
	private String result;

	public Model() {
		System.out.println("Model()");
		
	}
	
	public void compare(String text, String regex){
		Pattern p = Pattern.compile(".*"+regex+".*");
		Matcher m = p.matcher(text);
		boolean b = m.matches();
		
		if(b){
			System.out.println("model finding match");
			result = "Input matches.";
		} else {
			System.out.println("model finding no match");
			result = "No match.";
		}
		setChanged();
		notifyObservers(result);
	}

}
