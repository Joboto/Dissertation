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
import model.MyJodaCal;

import org.joda.time.*;
import org.joda.*;

public class JodaMonthView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton previousButton;
	private JButton todayButton;
	private JButton nextButton;
	//private DateTime selectedDay;
	private JPanel monthTable;
	//private EventController controller;
	private MyJodaCal cal;
	
	public JodaMonthView(MyJodaCal c){
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(550, 400));
		setBackground(new Color(240, 244, 248));
				
		cal = c;
		
		
		add(monthTable);
	}
	
	public JPanel getMonthPanel(DateTime dt){
		JPanel theGrid = new JPanel();
		theGrid.setPreferredSize(new Dimension(500, 325));
		theGrid.setBorder(BorderFactory.createTitledBorder(dt.monthOfYear().getAsText()));
		theGrid.setLayout(new GridLayout(5, 7));
		
		for(int loop = 0; loop < 35; loop++){
			theGrid.add(new DayPanel(cal.getDaysEvents(loop + 1)));
			dt = dt.plusDays(1);
		}
		return theGrid;
	}
	
	public void updateEvents(){
		for(int loop = 0; loop < monthTable.getComponentCount(); loop++){
			DayPanel dp = (DayPanel) monthTable.getComponent(loop);
			System.out.println(dp.toString());
			
			dp.update();
		}
	}
}
