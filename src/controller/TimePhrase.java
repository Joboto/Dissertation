package controller;

import javax.swing.JOptionPane;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;

public enum TimePhrase {
	/**
	 * This could be where some learning takes place with things like, after work
	 * ...or might want a different enum or class altogether 
	 */
	NOON("(at)?(( [Nn]oon)|( [Mm]idday))", LocalTime.parse("12:00"), 0, null),
	MIDNIGHT("(at )?[Mm]idnight", LocalTime.parse("00:00"), 0, null),
	MORNING("([Ff]irst thing( in the morning)?)|(([Ii]n the )?morning)", LocalTime.parse("08:00"), 0, null),
	AFTERNOON("(([Tt]his|in the) afternoon,?)|([Aa]fter lunch,?)", LocalTime.parse("14:00"), 0, null),
	EVENING("([Aa]fter work,?)|(([Tt]his )?evening,?)", LocalTime.parse("17:00"), 0, null),
	//TONIGHT...
	;
	
	private String regex;
	private LocalTime time;
	private LocalTime prevCorrection;
	private int correctionCount;
	private static final int correctionLimit = 2;
	
	private TimePhrase(String rgx, LocalTime lt, int count, LocalTime prevCorr){
		regex = rgx;
		time = lt;
		correctionCount = count;
		prevCorrection = prevCorr;
	}
	
	public String regex(){
		return regex;
	}
	
	public LocalTime time(){
		return time;
	}

	public void correction(LocalTime lt){
		if(lt.equals(prevCorrection)){
			correctionCount++;
			System.out.println("Correction count now "+correctionCount);
			if(correctionCount == correctionLimit){
				String message = "You have made this correction "+correctionLimit+" times in a row";
				message = message + "\nWould you like to change the default time\n of '"+this.toString().toLowerCase()+"' to "+lt.toString(DateTimeFormat.shortTime());
				int option = JOptionPane.showConfirmDialog(null, message, "Change to default time", JOptionPane.YES_NO_OPTION);
				if(option == 0){
					time = lt;
					correctionCount = 0;
				}
			}
		} else {
			prevCorrection = lt;
			correctionCount = 1;
			System.out.println("'else' setting PREV to "+prevCorrection.toString()+" and COUNT to "+correctionCount);
		}
	}
}
