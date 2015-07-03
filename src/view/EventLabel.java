package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Event;

public class EventLabel extends JLabel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Event thisEvent;
	
	public EventLabel(Event event) {
		thisEvent = event;
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
		eventFrame.add(new EventPanel(thisEvent));
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
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setBackground(getParent().getBackground());
	}

}
