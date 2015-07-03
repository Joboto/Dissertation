import java.awt.Dimension;

import javax.swing.JFrame;

import org.joda.time.DateTime;

import model.Event;
import model.MyJodaCal;
import view.JodaViewFrame;

public class Main {
	public static void main(String[] args) {
        MyJodaCal cal = new MyJodaCal();
        //add test events
		//cal.addEvent(new DateTime(), "Something");
		//cal.addEvent(new DateTime().plusHours(1), "Something Else");
		//cal.addEvent(new DateTime().plusHours(2), "Something new");
		//cal.addEvent(new DateTime().plusDays(1), "And another thing");
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
