
import model.*;
import controller.*;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Demo {

	public static void main(String[] args) {
		LocalTime lt = LocalTime.parse("17:00");
		
		System.out.println(TimePhrase.AFTERNOON.time().toString(DateTimeFormat.shortTime()));
		TimePhrase.AFTERNOON.correction(lt);
		System.out.println(TimePhrase.AFTERNOON.time().toString(DateTimeFormat.shortTime()));
		TimePhrase.AFTERNOON.correction(LocalTime.now());
		System.out.println(TimePhrase.AFTERNOON.time().toString(DateTimeFormat.shortTime()));
		TimePhrase.AFTERNOON.correction(lt);
		System.out.println(TimePhrase.AFTERNOON.time().toString(DateTimeFormat.shortTime()));
		TimePhrase.AFTERNOON.correction(lt);
		System.out.println(TimePhrase.AFTERNOON.time().toString(DateTimeFormat.shortTime()));
		TimePhrase.AFTERNOON.correction(lt);
		System.out.println(TimePhrase.AFTERNOON.time().toString(DateTimeFormat.shortTime()));
		TimePhrase.AFTERNOON.correction(lt);
		System.out.println(TimePhrase.AFTERNOON.time().toString(DateTimeFormat.shortTime()));
		TimePhrase.AFTERNOON.correction(lt);
		System.out.println(TimePhrase.AFTERNOON.time().toString(DateTimeFormat.shortTime()));
		TimePhrase.AFTERNOON.correction(lt);
	}

}
