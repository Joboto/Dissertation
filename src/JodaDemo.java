import java.util.Date;
import org.joda.time.DateTime;


public class JodaDemo {

	public static void main(String[] args) {
		
		Date juDate = new Date();
		DateTime dt = new DateTime(juDate);
		
		System.out.println(dt);

	}

}
