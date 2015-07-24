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
import model.Event;
import model.MyJodaCal;

public class JodaViewFrame extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	
	private JSplitPane mainSplit, subSplit, subSubSplit;
	private JToolBar toolBar;
	private JPanel monthPanel, unscheduledPanel;
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
		subSubSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		subSubSplit.setLeftComponent(getToolBar());
		subSubSplit.setRightComponent(getUnscheduledPanel());
		subSplit.setLeftComponent(subSubSplit);
		subSplit.setRightComponent(getMonthPanel());
		mainSplit.setLeftComponent(input);
		mainSplit.setRightComponent(subSplit);
		mainSplit.setDividerLocation(200);
		this.add(mainSplit);
		
		cal.addObserver(this);
	}
	

	public void update(Observable calendar, Object msg) {
		subSplit.setRightComponent(getMonthPanel());
		subSubSplit.setRightComponent(getUnscheduledPanel());
	}
	
	public JPanel getMonthPanel(){
		monthPanel = new JPanel();
		monthPanel.setPreferredSize(new Dimension(500, 325));
		String title = cal.getSelectedDate().monthOfYear().getAsText();
		title = title + " " + cal.getSelectedDate().getYear();
		monthPanel.setBorder(BorderFactory.createTitledBorder(title));
		monthPanel.setLayout(new GridLayout(5, 7));
		
		for(int loop = 0; loop < 35; loop++){
			ArrayList<Event> events = cal.getDaysEvents(loop);
			DateTime date = cal.getSelectedMonth().getDay(loop).getDate();
			monthPanel.add(new DayPanel(events, date, controller));
		}
		return monthPanel;
	}
	
	public JPanel getUnscheduledPanel() {
		GridLayout grid = new GridLayout(0, 1);
		unscheduledPanel = new JPanel(grid);
		for(Event event : cal.getUnscheduledEvents()){
			grid.setRows(grid.getRows() + 1);
			unscheduledPanel.add(new EventLabel(event, controller));
		}
		
		return unscheduledPanel;
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
