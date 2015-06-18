package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.MeetingController;

import org.joda.time.*;

public class JodaMonthView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton previousButton;
	private JButton todayButton;
	private JButton nextButton;
	private DateTime selectedDay;
	private JPanel monthTable;
	private JLabel monthLabel;
	private MeetingController controller;
	
	public JodaMonthView(DateTime selected, MeetingController c){
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(550, 400));
		setBackground(new Color(240, 244, 248));
		
		controller = c;
		selectedDay = selected;
		
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(530, 30));
		topPanel.add(new JLabel("June"), BorderLayout.WEST);
		//topPanel.add(getButtonPanel() , BorderLayout.EAST);
		topPanel.setBackground(new Color(240, 244, 248));
		
		add(topPanel, BorderLayout.EAST);
		//add(monthTable, BorderLayout.SOUTH);
	}


}
