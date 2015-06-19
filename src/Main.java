import java.awt.Dimension;

import javax.swing.JFrame;

import org.joda.time.DateTime;

import model.MyCalendar;
import model.MyJodaCal;
import view.JodaViewFrame;
import view.ViewFrame;

public class Main {
	/**
	 * Copied calendar main
	 */
	/*public static void main(String[] args) {
        MyCalendar cal = new MyCalendar();
		JFrame calendarFrame = new JFrame();
        calendarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calendarFrame.setTitle("Calendar");
        calendarFrame.setResizable(true);
        calendarFrame.setPreferredSize(new Dimension(800, 500));
        calendarFrame.add(new ViewFrame(cal));
        calendarFrame.pack();
        calendarFrame.setVisible(true);
    }*/
	/**
	 * JodaCal main
	 */
	public static void main(String[] args) {
        MyJodaCal cal = new MyJodaCal();
        //add test events
		cal.addEvent(new DateTime(), "Surprise");
		cal.addEvent(new DateTime().plusHours(1), "butt");
		cal.addEvent(new DateTime().plusHours(2), "sex");
		cal.addEvent(new DateTime().plusDays(1), "buttsex");
        JFrame calendarFrame = new JFrame();
        calendarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calendarFrame.setTitle("Calendar");
        calendarFrame.setResizable(true);
        calendarFrame.setPreferredSize(new Dimension(800, 500));
        calendarFrame.add(new JodaViewFrame(cal));
        calendarFrame.pack();
        calendarFrame.setVisible(true);
    }
	/**
	 * orig MVC main, just for regex thingy
	 */
	/*public static void main(String[] args) {
		RunMVC mainRunMVC = new RunMVC();
	}*/

}
