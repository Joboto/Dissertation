package view;

import java.awt.GridLayout;

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
	private GridLayout grid;
	
	public DayPanel(DateTime thisDate, EventController ctrlr) {
		super();
		this.thisDate = thisDate;
		this.ctrlr = ctrlr;
		setBorder(BorderFactory.createTitledBorder(thisDate.dayOfWeek().getAsShortText()+" "+thisDate.getDayOfMonth()));
		//setAutoscrolls(true);
		this.grid = new GridLayout();
		this.grid.setColumns(1);
		this.grid.setRows(1);
		setLayout(this.grid);
		update();
	}
	
	public void update(){
		for(Event event : this.ctrlr.getDaysEvents(this.thisDate)){
			String title = event.getDateTime().toString(DateTimeFormat.shortTime())+" "+event.getName();
			add(new JLabel(title));
			this.grid.setRows(this.grid.getRows() + 1);
		}
	}
	
	public String toString(){
		String output = "" + (this.grid.getRows() - 1);
		output = output + " events for " + this.thisDate.toString(DateTimeFormat.shortDate());
		return output;
	}
}
