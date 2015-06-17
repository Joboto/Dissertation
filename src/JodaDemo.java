import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.joda.time.MutableDateTime;


public class JodaDemo {

	public static void main(String[] args) {
		
		Date juDate = new Date();
		DateTime dt = new DateTime(juDate);
		DateTime dt2 = dt.dayOfWeek().addToCopy(2);
		DateTime dt3 = dt.plusDays(2);
		MutableDateTime mdt = dt.toMutableDateTime();
		//DateTime year2000 = dt.withYear(2000);
		
		mdt.addDays(4);
		
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
	}

}
