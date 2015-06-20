package view;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Day;
import model.Event;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import controller.EventController;

public class DayPanel extends JPanel {
	
	private GridLayout grid;
	private Day theDay;
	
	public DayPanel(Day day) {
		theDay = day;
		setBorder(BorderFactory.createTitledBorder(theDay.getDate().dayOfWeek().getAsShortText()+" "+theDay.getDate().getDayOfMonth()));
		setAutoscrolls(true);
		
		update();
	}
	
	public void update(){
		this.removeAll();
		this.grid = new GridLayout();
		this.grid.setColumns(1);
		this.grid.setRows(1);
		setLayout(this.grid);
		for(Event event : theDay.get){
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
