
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.*;
import controller.*;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Demo {

	public static void main(String[] args) {
		String relLocPrt = "Swim after the gym at the Commy with Bob";
		String relPrtLoc = "Swim after the gym with Bob at the Commy";
		String prtRelLoc = "Swim with Bob after the gym at the Commy";
		String prtLocRel = "Swim with Bob at the Commy after the gym";
		String locPrtRel = "Swim at the Commy with Bob after the gym";
		String locRelPrt = "Swim at the Commy after the gym with Bob";
		
		//checkPreps(relLocPrt);
		
		System.out.println(Location.all());
		System.out.println(Location.AT.toString());
		System.out.println(Location.AT);
	}
	
	private static void checkPreps(String input){
		String[] words = input.split(" ");
		ArrayList<Object> found = new ArrayList<>();
		for(int loop = words.length - 1; loop >= 0; loop--){
			for(PrepCombo combo : PrepCombo.values()){
				if(matches(words[loop], combo.regex())){
					found.add(combo);
				}
			}
		}
		for(Object obj : found){
			System.out.println(obj.toString());
		}
	}
	
	private static boolean matches(String input, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		return m.find();
	}

}
