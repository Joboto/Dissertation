
import model.*;
import controller.*;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Demo {

	public static void main(String[] args) {
		String day = "monday";
		DateTimeFormatter fmt = DateTimeFormat.forPattern(Date.DAYofWEEK.format());
		
		LocalDate now = LocalDate.now();
		
		LocalDate parsedDate = LocalDate.parse(day, fmt);
		
		LocalDate newDay = now.withFields(parsedDate);
		
		System.out.println(now.toString());
		System.out.println(parsedDate.toString());
		System.out.println(newDay.toString());
	}

}
