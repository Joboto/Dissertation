package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	/**
	 * Used statically by the Extractor classes for finding given regular expressions in the input string.  
	 */
	//Returns true if the the given regex matches any part of the input string.
	public static boolean matches(String input, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		return m.find();
	}
	
	//Returns the substring from the input string which matches the regular expression.
	public static String getMatch(String input, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		m.find();
		return m.group();
	}
	
	//Returns the input string minus the substring which matches the regular expression.
	public static String removeMatch(String input, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		m.find();
		return input.replaceAll(m.group()+" ?", "");
	}
	
	
}
