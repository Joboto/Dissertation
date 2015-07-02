package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import org.joda.time.DateTime;

import controller.EventController;
import model.MyJodaCal;
import model.Event;

public class JodaViewFrame extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	
	private JPanel eastPanel;
	private JSplitPane mainSplit, subSplit;
	private JToolBar toolBar;
	private JPanel monthPanel;
	private InputView input;
	private MyJodaCal cal;
	private EventController controller;
	
	public JodaViewFrame(MyJodaCal c) {
		
		setLayout(new BorderLayout());
		
		cal = c;
		controller = new EventController(c);
		input = new InputView(controller);
		
		this.setBackground(new Color(240, 244, 248));
		
		mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		subSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		subSplit.setLeftComponent(getToolBar());
		subSplit.setRightComponent(getMonthPanel());
		mainSplit.setLeftComponent(input);
		mainSplit.setRightComponent(subSplit);
		this.add(mainSplit);
		
		cal.addObserver(this);
	}
	

	public void update(Observable calendar, Object msg) {
		subSplit.setRightComponent(getMonthPanel());
		
	}
	
	public JPanel getMonthPanel(){
		monthPanel = new JPanel();
		monthPanel.setPreferredSize(new Dimension(500, 325));
		String title = cal.getSelectedDate().monthOfYear().getAsText();
		title = title + " " + cal.getSelectedDate().getYear();
		monthPanel.setBorder(BorderFactory.createTitledBorder(title));
		monthPanel.setLayout(new GridLayout(5, 7));
		
		for(int loop = 0; loop < 35; loop++){
			monthPanel.add(new DayPanel(cal.getDaysEvents(loop), cal.getSelectedMonth().getDay(loop).getDate()));
		}
		return monthPanel;
	}
	
	public JToolBar getToolBar(){
		toolBar = new JToolBar("Navigation");
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
	
	public EventController getEventController(){
		return controller;
	}
	

}
