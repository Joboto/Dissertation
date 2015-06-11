import java.util.Date;
import org.joda.time.DateTime;


public class JodaDemo {

	public static void main(String[] args) {
		
		Date juDate = new Date();
		DateTime dt = new DateTime(juDate);
		DateTime dt2 = new DateTime();
		
		DateTime year2000 = dt.withYear(2000);
		
		System.out.println(dt);
		System.out.println(year2000);
		System.out.println(dt2);

	}

}
