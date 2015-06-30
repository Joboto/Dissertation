
import model.*;
import controller.*;

import org.joda.time.*;

public class Demo {

	public static void main(String[] args) {
		DateTime dt = new DateTime();
		Interval interval = new Interval(dt.plusDays(1), Period.hours(1));
		
		EventQueue meQue = new EventQueue();
		Event first = new Event("No time");
		Event second = new Event(new LocalDate(new DateTime()), "Just a day");
		Event third = new Event(interval, "for tomorrow");
		
		meQue.addEvent(third);
		meQue.addEvent(second);
		meQue.addEvent(first);
		
		System.out.println(meQue.toString());
	}

}
