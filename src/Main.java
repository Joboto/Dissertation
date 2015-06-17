import java.awt.Dimension;
import javax.swing.JFrame;
import model.MyCalendar;
import view.ViewFrame;

public class Main {
	public static void main(String[] args) {
        MyCalendar cal = new MyCalendar();
        JFrame calendarFrame = new JFrame();
        calendarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calendarFrame.setTitle("Calendar");
        calendarFrame.setResizable(false);
        calendarFrame.setPreferredSize(new Dimension(800, 500));
        calendarFrame.add(new ViewFrame(cal));
        calendarFrame.pack();
        calendarFrame.setVisible(true);
    }

	/*public static void main(String[] args) {
		RunMVC mainRunMVC = new RunMVC();
	}*/

}
