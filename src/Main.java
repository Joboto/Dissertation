import java.awt.Dimension;

import javax.swing.JFrame;

import org.joda.time.DateTime;

import model.MyCalendar;
import model.MyJodaCal;
import view.JodaViewFrame;
import view.ViewFrame;

public class Main {
	public static void main(String[] args) {
        //MyCalendar cal = new MyCalendar();
		MyJodaCal cal = new MyJodaCal();
		cal.addEvent(new DateTime(), "Surprise butt sex");
        JFrame calendarFrame = new JFrame();
        calendarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calendarFrame.setTitle("Calendar");
        calendarFrame.setResizable(false);
        calendarFrame.setPreferredSize(new Dimension(800, 500));
        //calendarFrame.add(new ViewFrame(cal));
        calendarFrame.add(new JodaViewFrame(cal));
        calendarFrame.pack();
        calendarFrame.setVisible(true);
    }

	/*public static void main(String[] args) {
		RunMVC mainRunMVC = new RunMVC();
	}*/

}
