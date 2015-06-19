package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import org.joda.time.DateTime;

import controller.EventController;
import model.MyJodaCal;

public class JodaViewFrame extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	
	private JPanel eastPanel;
	private JSplitPane mainSplit, subSplit;
	//private JPanel buttonPanel; don't need as view will not yet be changable
	private JToolBar toolBar;
	private JodaMonthView monthPanel;
	//private MeetingListView meetingPanel; should be event list view
	private MyJodaCal cal;
	private DateTime selectedDay;
	private EventController controller;
	
	//trying new inputview
	private InputView input;
	
	public JodaViewFrame(MyJodaCal c) {
		
		setLayout(new BorderLayout());
		
		cal = c;
		controller = new EventController(c);
		selectedDay = cal.getSelectedDate();
		
		
		monthPanel = new JodaMonthView(selectedDay, controller);
		
		input = new InputView(controller);
		//eastPanel = new JPanel(new BorderLayout());
		
		//set button panel size here
		//eastPanel.setPreferredSize(new Dimension(550, 450));
		//set meeting panel size here
		
		this.setBackground(new Color(240, 244, 248));
		//set button panel bg color here
		//eastPanel.setBackground(new Color(240, 244, 248));
		//set meeting/event panel bg color here
		
		//add buttons to east panel
		//eastPanel.add(monthPanel, BorderLayout.SOUTH);
		
		mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		subSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		subSplit.setLeftComponent(getToolBar());
		subSplit.setRightComponent(monthPanel);
		mainSplit.setLeftComponent(input);
		mainSplit.setRightComponent(subSplit);
		this.add(mainSplit);
		//this.add(eastPanel,  BorderLayout.EAST);
		//add meeting/event panle here
		
		cal.addObserver(this);
	}
	

	public void update(Observable calendar, Object msg) {
		/*if(cal instanceof MyJodaCal){
			selectedDay = ((MyJodaCal) calendar).getSelectedDate();
		}*/
		monthPanel.updateEvents();
		
	}
	
	public JToolBar getToolBar(){
		toolBar = new JToolBar("You're a tool bar");
		toolBar.add(makeNavButton("<<", "previous"));
		toolBar.add(makeNavButton("Today", "today"));
		toolBar.add(makeNavButton(">>", "next"));
		return toolBar;
	}
	
	public JButton makeNavButton(String text, String actionCommand) {
		JButton button = new JButton(text);
		button.setActionCommand(actionCommand);
		button.addActionListener(controller);
		return button;
	}
	

}
