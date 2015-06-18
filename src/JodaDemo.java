import java.util.ArrayList;
import java.util.Date;

import model.Event;
import model.EventQueue;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.joda.time.MutableDateTime;


public class JodaDemo {

	public static void main(String[] args) {
		
		DateTime dt = new DateTime();
		DateTime dt2 = dt.plusMonths(2);
		DateTime dt3 = dt.plusDays(2);
		MutableDateTime mdt = dt.toMutableDateTime();
		//DateTime year2000 = dt.withYear(2000);
		
		/*mdt.addDays(4);
		
		System.out.println(dt);
		//System.out.println(year2000);
		System.out.println(dt2);
		System.out.println(dt3);
		
		System.out.println(dt.dayOfYear().toString());
		System.out.println(dt.dayOfYear().get());
		System.out.println(dt.dayOfYear().getAsShortText());
		System.out.println(dt.dayOfYear().getAsText());
		System.out.println(dt.dayOfYear().getAsString());
		
		System.out.println(dt.dayOfWeek().getAsShortText());
		System.out.println(dt2.dayOfWeek().getAsShortText());
		System.out.println(dt3.dayOfWeek().getAsShortText());
		System.out.println(mdt.dayOfMonth().getAsString());
		
		/*Instant i = new Instant();
		Duration d = new Duration(5000);
		Interval v = d.toIntervalTo(i);
		
		System.out.println(i.toString());
		System.out.println(v.getStart().toString());
		System.out.println(v.getEnd().toString());
		System.out.println(v.getEnd().dayOfWeek().getAsShortText());
		*/
		System.out.println(dt.toString());
		System.out.println(dt2.toString());
		System.out.println(dt3.toString());
		System.out.println("\n");
		
		Event e1 = new Event(dt, "The shaving of the monkey");
		Event e2 = new Event(dt2, "Pruning of the moose");
		Event e3 = new Event(dt3, "the pickling of the herring");
		
		System.out.println(e1.toString());
		System.out.println(e2.toString());
		System.out.println(e3.toString());
		System.out.println("\n");
		EventQueue eq = new EventQueue();
		eq.addEvent(e1);
		eq.addEvent(e2);
		eq.addEvent(e3);
		System.out.println(eq.toString());
		
	}
	
	

}
