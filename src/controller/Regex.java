package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	
	public static boolean matches(String input, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		return m.find();
	}
	
	public static String getMatch(String input, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		m.find();
		return m.group();
	}
	
	public static String removeMatch(String input, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		m.find();
		return input.replaceAll(m.group()+" ?", "");
	}
	
	
}
