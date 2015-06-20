import model.Month;
import model.Day;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;


public class Demo {

	public static void main(String[] args) {
		System.out.println("Starting...");
		DateTime now = new DateTime();
		Month thisMonth = new Month(now);
		
		String first = "Here it is: " + thisMonth.getDays().get(1).getDate().toString(DateTimeFormat.shortDate());
		System.out.println(first);

	}

}
