import java.awt.Dimension;
import javax.swing.JFrame;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import model.Event;
import model.MyJodaCal;
import view.JodaViewFrame;

public class Main {
	public static void main(String[] args) {
        MyJodaCal cal = new MyJodaCal();
        //add test events
        Event e1 = new Event("kick off");
        e1.setDay(LocalDate.now().withDayOfMonth(31).withMonthOfYear(7));
        e1.setTime(LocalTime.parse("10:00"));
        //e1.setPeriod(Period.hours(1));
        Event e2 = new Event("Flight to dublin");
        e2.setDay(LocalDate.now().withDayOfMonth(25).withMonthOfYear(7));
        e2.setTime(LocalTime.parse("10:00"));
        e2.setPeriod(Period.minutes(60));
        cal.addEvent(e2);
        cal.addEvent(e1);
        cal.addEvent(new Event("Test"));
        JFrame calendarFrame = new JFrame();
        calendarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calendarFrame.setTitle("Calendar");
        calendarFrame.setResizable(true);
        calendarFrame.setPreferredSize(new Dimension(800, 500));
        calendarFrame.add(new JodaViewFrame(cal));
        calendarFrame.pack();
        calendarFrame.setVisible(true);
    }
	
}
