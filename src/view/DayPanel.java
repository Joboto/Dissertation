package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Event;

import org.joda.time.DateTime;

import controller.EventController;

public class DayPanel extends JPanel implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridLayout grid;
	private ArrayList<Event> daysEvents;
	private DateTime thisDate;
	private EventController controller;
	
	public DayPanel(ArrayList<Event> events, DateTime date, EventController ctrlr) {
		daysEvents = events;
		thisDate = date;
		controller = ctrlr;
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
			add(new EventLabel(event, controller));
			this.grid.setRows(this.grid.getRows() + 1);
		}
	}
	
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		JFrame dayFrame = new JFrame();
        //calendarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dayFrame.setTitle("Day's events");
		dayFrame.setResizable(true);
		dayFrame.setPreferredSize(new Dimension(300, 500));
		dayFrame.add(new DayPanel(daysEvents, thisDate, controller));
		dayFrame.pack();
		dayFrame.setVisible(true);
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
