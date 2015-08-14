package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.EventController;
import model.Event;

public class EventLabel extends JLabel implements MouseListener{
	/**
	 * Clickable label showing event title and time. 
	 * Appears in relevant day panel or in the 'unscheduled events panel'.
	 * Clicking lable opens Event Panel for given event.
	 */
	private static final long serialVersionUID = 1L;
	private Event thisEvent;
	private EventController controller;
	
	public EventLabel(Event event, EventController ctrl) {
		thisEvent = event;
		controller = ctrl;
		setText(thisEvent.getTitle());
		addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JFrame eventFrame = new JFrame();
        //calendarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		eventFrame.setTitle("Event details");
		eventFrame.setResizable(true);
		eventFrame.setPreferredSize(new Dimension(300, 500));
		eventFrame.add(new EventPanel(thisEvent, controller));
		eventFrame.pack();
		eventFrame.setVisible(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setBackground(Color.WHITE);
		setForeground(Color.BLUE);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setBackground(getParent().getBackground());
		setForeground(Color.BLACK);
	}

}
