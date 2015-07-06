import java.awt.Dimension;

import javax.swing.JFrame;

import org.joda.time.DateTime;

import controller.EventController;
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
        EventController ctrl = new EventController(cal);
       
    }
	
}
