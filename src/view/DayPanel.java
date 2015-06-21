package view;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Event;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class DayPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridLayout grid;
	private ArrayList<Event> daysEvents;
	private DateTime thisDate;
	
	public DayPanel(ArrayList<Event> events, DateTime date) {
		daysEvents = events;
		thisDate = date;
		setBorder(BorderFactory.createTitledBorder(date.dayOfWeek().getAsShortText()+" "+date.getDayOfMonth()));
		setAutoscrolls(true);
		
		update();
	}
	
	public void update(){
		this.removeAll();
		this.grid = new GridLayout();
		this.grid.setColumns(1);
		this.grid.setRows(1);
		setLayout(this.grid);
		for(Event event : daysEvents){
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
