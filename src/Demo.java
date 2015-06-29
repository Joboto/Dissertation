
import model.*;
import controller.*;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.Period;

public class Demo {

	public static void main(String[] args) {
		DateTime now = new DateTime();
		Period hr = Period.hours(2);
		DateTime then = now.plus(hr);
		
		Duration d = new Duration(now, then);
		
		Interval i = new Interval(then, d);
		
		System.out.println(i.toPeriod().getHours());
		System.out.println(i.getStart().hourOfDay().getAsText());
		System.out.println(i.getEnd().hourOfDay().getAsText());
	}

}
