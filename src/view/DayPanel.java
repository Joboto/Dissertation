package view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Event;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import controller.EventController;

public class DayPanel extends JPanel {
	
	private DateTime thisDate;
	private EventController ctrlr;
	
	public DayPanel(DateTime thisDate, EventController ctrlr) {
		super();
		this.thisDate = thisDate;
		this.ctrlr = ctrlr;
		setBorder(BorderFactory.createTitledBorder(thisDate.dayOfWeek().getAsShortText()+" "+thisDate.getDayOfMonth()));
		setAutoscrolls(true);
		update();
	}
	
	public void update(){
		for(Event event : this.ctrlr.getDaysEvents(this.thisDate)){
			String title = event.getDateTime().toString(DateTimeFormat.shortTime())+" "+event.getName();
			add(new JLabel(title));
		}
	}
}
