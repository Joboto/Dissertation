package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Event;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import controller.EventController;

public class DayPanel extends JPanel implements MouseListener {
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
		addMouseListener(this);
		update();
	}
	
	public void update(){
		this.removeAll();
		this.grid = new GridLayout();
		this.grid.setColumns(1);
		this.grid.setRows(1);
		setLayout(this.grid);
		for(Event event : daysEvents){
			JLabel eLabel = new JLabel(event.getTitle());
			add(eLabel);
			this.grid.setRows(this.grid.getRows() + 1);
		}
	}
	
	public String toString(){
		String output = "" + (this.grid.getRows() - 1);
		output = output + " events for " + this.thisDate.toString(DateTimeFormat.shortDate());
		return output;
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		JFrame calendarFrame = new JFrame();
        //calendarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calendarFrame.setTitle("Calendar");
        calendarFrame.setResizable(true);
        calendarFrame.setPreferredSize(new Dimension(300, 500));
        calendarFrame.add(new DayPanel(daysEvents, thisDate));
        calendarFrame.pack();
        calendarFrame.setVisible(true);
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		setBackground(Color.WHITE);
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		setBackground(getParent().getBackground());
	}
	
	
}
