package model;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class Month {
	/**
	 * Currently builds a month of days, might be nice to do it by weeks.
	 * Might also be an idea to return a Day given particular input (date time or day of month).
	 */
	private DateTime startOfMonth;
	private ArrayList<Day> days;
	
	public Month(DateTime date) {
		setStartOfMonth(date);
		buildMonth();
	}
	
	private void buildMonth(){
		this.days = new ArrayList<Day>();
		DateTime tempDt = getStartOfMonth();
		while(tempDt.isBefore(startOfMonth.plusMonths(1))){
			getDays().add(new Day(tempDt));
			tempDt = tempDt.plusDays(1);
		}
	}
	
	public Day getDay(DateTime date){
		return getDay(date.getDayOfMonth());
	}
	
	public Day getDay(int dayOfMonth){
		try {
			return getDays().get(dayOfMonth - 1);
		} catch (NullPointerException e) {
			System.out.println("Date out of bounds...");
			return null;
		}
	}

	public DateTime getStartOfMonth() {
		return this.startOfMonth;
	}

	public void setStartOfMonth(DateTime someDate) {
		this.startOfMonth = someDate.withDayOfMonth(1);
		System.out.println("First day of month: "+getStartOfMonth().toString(DateTimeFormat.shortDateTime()));
	}

	public ArrayList<Day> getDays() {
		return this.days;
	}
	
}
