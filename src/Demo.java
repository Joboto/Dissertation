
import model.*;
import controller.*;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Demo {

	public static void main(String[] args) {
		EventQueue que = new EventQueue();
		LocalDate day = new LocalDate();
		LocalTime time = new LocalTime();
		
		Event e1 = new Event("Tomorrow afternoon");
		e1.setDay(day.plusDays(1));
		e1.setTime(time.plusHours(2));
		Event e2 = new Event("Next fortnight, with time");
		e2.setDay(day.plusDays(14));
		e2.setTime(time);
		Event e3 = new Event("Next week, with time");
		e3.setDay(day.plusDays(7));
		e3.setTime(time);
		Event e4 = new Event("Next week, no time");
		e4.setDay(day.plusDays(7));
		//e4.setTime(time);
		Event e5 = new Event("Next week, later");
		e5.setDay(day.plusDays(7));
		e5.setTime(time.plusHours(1));
		Event e6 = new Event("No date or time");
		//e6.setDay(day);
		//e6.setTime(time);
		Event e7 = new Event("Random");
		//e7.setDay(day.plusDays(12));
		//e7.setTime(time);
		
		que.addEvent(e1);
		que.addEvent(e2);
		que.addEvent(e3);
		que.addEvent(e4);
		que.addEvent(e5);
		que.addEvent(e6);
		que.addEvent(e7);
		
		System.out.println(que.toString());
	}

}
