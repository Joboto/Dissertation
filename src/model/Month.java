package model;

import java.util.ArrayList;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;

public class Month {
	/**
	 * Currently builds a month of days, might be nice to do it by weeks.
	 * Might also be an idea to return a Day given particular input (date time or day of month).
	 */
	private DateTime startOfMonth;
	private YearMonth month;
	private ArrayList<Day> days;
	
	public Month(DateTime date) {
		setStartOfMonth(date);
		buildMonth();
	}
	
	private void buildMonth(){
		this.days = new ArrayList<Day>();
		DateTime tempDt = getStartOfMonth();
		/*while(tempDt.isBefore(startOfMonth.plusMonths(1))){
			getDays().add(new Day(tempDt));
			tempDt = tempDt.plusDays(1);
		}*/
		for(int day = 0; day < 35; day++){
			Day newDay = new Day(tempDt.plusDays(day));
			//System.out.println("Adding: "+newDay.getDate().toString());
			getDays().add(newDay);
		}
	}
	
	public Day getDay(DateTime date){
		Day toReturn = new Day();
		for(Day d : getDays()){
			if(d.getDate().equals(date.withTimeAtStartOfDay())){
				toReturn = d;
				break;
			}
		}
		return toReturn;
	}
	
	public Day getDay(int index){
		try {
			return getDays().get(index);
		} catch (NullPointerException e) {
			System.out.println("Date out of bounds...");
			return null;
		}
	}

	public DateTime getStartOfMonth() {
		return this.startOfMonth;
	}

	public void setStartOfMonth(DateTime someDate) {
		this.startOfMonth = someDate.withDayOfMonth(1).minusDays(someDate.withDayOfMonth(1).getDayOfWeek()-1);
	}

	public ArrayList<Day> getDays() {
		return this.days;
	}
	
}
