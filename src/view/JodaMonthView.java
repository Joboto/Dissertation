package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import controller.EventController;

import org.joda.time.*;
import org.joda.*;

public class JodaMonthView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton previousButton;
	private JButton todayButton;
	private JButton nextButton;
	private DateTime selectedDay;
	private JPanel monthTable;
	private JLabel monthLabel;
	private EventController controller;
	
	public JodaMonthView(DateTime selected, EventController c){
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(550, 400));
		setBackground(new Color(240, 244, 248));
				
		controller = c;
		selectedDay = selected;
		
		monthTable = getMonthPanel(selectedDay);
		//monthLabel = new JLabel(selectedDay.monthOfYear().getAsShortText());
		
		/*JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(530, 30));
		topPanel.add(monthLabel, BorderLayout.WEST);
		topPanel.add(getButtonPanel() , BorderLayout.EAST);
		topPanel.setBackground(new Color(240, 244, 248));*/
		
		//add(topPanel, BorderLayout.EAST);
		//add(monthTable, BorderLayout.SOUTH);
		add(monthTable);
	}
	
	public JPanel getMonthPanel(DateTime dt){
		JPanel theGrid = new JPanel();
		theGrid.setPreferredSize(new Dimension(500, 325));
		theGrid.setBorder(BorderFactory.createTitledBorder(dt.monthOfYear().getAsText()));
		theGrid.setLayout(new GridLayout(5, 7));
		
		dt = dt.withDayOfMonth(1).minusDays(dt.withDayOfMonth(1).getDayOfWeek()-1);
		for(int loop = 0; loop < 35; loop++){
			//theGrid.add(new DayPanel(dt, controller));
			theGrid.add(new JScrollPane(new DayPanel(dt, controller)));
			dt = dt.plusDays(1);
		}
		return theGrid;
	}
	
	/*public JPanel getMonthPanel(DateTime dt){
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.setRowCount(5); //no need to set column count as this will be done as columns are added.
		JTable monthTable = new JTable(tableModel);
		monthTable.setPreferredScrollableViewportSize(new Dimension(500, 325));
		monthTable.setFillsViewportHeight(true);
		monthTable.setRowHeight(54);
		monthTable.setShowGrid(true);
		monthTable.setGridColor(Color.LIGHT_GRAY);
		
		dt = dt.withDayOfMonth(1).minusDays(dt.withDayOfMonth(1).getDayOfWeek()-1);
		for(int week = 0; week < 5; week++){
			for(int day = 0; day < 7; day++){
				if(week == 0){
					tableModel.addColumn(dt.dayOfWeek().getAsText());
				}
				tableModel.setValueAt(controller.getDaysEvents(dt), week, day);
				dt = dt.plusDays(1);
			}
		}
		
		JPanel tablePanel = new JPanel();
		tablePanel.add(new JScrollPane(monthTable));
		tablePanel.setBackground(new Color(240, 244, 248));
		return tablePanel;
	}*/


}
