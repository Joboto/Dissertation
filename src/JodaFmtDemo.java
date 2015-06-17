import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.ISODateTimeFormat;

public class JodaFmtDemo {

	public static void main(String[] args) {
		String inputDateTime = "20th May 2002";
		//DateTimeFormatter fmt = DateTimeFormat.forPattern("dd'th' MMM yyyy");
		DateTimeFormatter fmt = new DateTimeFormatterBuilder()
			.appendDayOfMonth(2)
			.appendLiteral("th ")
			.appendMonthOfYearText()
			.appendLiteral(' ')
			.appendYear(4, 4)
			.toFormatter();
		
		DateTime dt = fmt.parseDateTime(inputDateTime);
		
		System.out.println(dt.toString());
		String theThing = dt.dayOfWeek().getAsText();
		theThing = theThing + " " + dt.dayOfMonth().getAsText();
		theThing = theThing + " " + dt.monthOfYear().getAsText();
		theThing = theThing + " " + dt.year().getAsText();
		System.out.println(theThing);
		
	}

}
