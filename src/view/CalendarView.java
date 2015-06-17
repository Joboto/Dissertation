package view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import org.joda.time.*;
import java.util.Observable;
import java.util.Observer;

public class CalendarView implements Observer {
	static JLabel lblMonth, lblYear;
	static JButton btnPrev, btnNext;
	static JFrame frame;
	static Container pane;
	static JScrollPane scrollPane;
	static JTable table;
	static DefaultTableModel tableModel;
	static JPanel panel;
	
	static DateTime now, current;

	public CalendarView() {
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		frame = new JFrame("JodaCal");
		frame.setSize(500, 550);
		pane = frame.getContentPane();
		pane.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblMonth = new JLabel();
		lblYear = new JLabel();
		btnPrev = new JButton("<<");
		btnNext = new JButton(">>");
		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		panel = new JPanel();
		
		btnPrev.addActionListener(new prev_Action());
		btnNext.addActionListener(new next_Action());
		
		pane.add(panel);
		panel.add(lblMonth);
		panel.add(lblYear);
		panel.add(btnPrev);
		panel.add(btnNext);
		panel.add(scrollPane);
		panel.setBorder(BorderFactory.createTitledBorder("Calendar"));
		
		panel.setBounds(0, 0, 480, 510);
		lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 100, 25);
		btnPrev.setBounds(10, 25, 50, 25);
		btnNext.setBounds(260, 25, 50, 25);
		scrollPane.setBounds(10, 50, 300, 300);
		
		String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
		for(int loop = 0; loop < 7; loop++){
			tableModel.addColumn(days[loop]);
		}
		table.getParent().setBackground(table.getBackground());
		
		table.setRowHeight(80);
		tableModel.setColumnCount(7);
		tableModel.setRowCount(5);
		
		frame.setVisible(true);
		
		now = new DateTime();
		current = now;
		refreshCal(current);

	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
	
	public static void refreshCal(DateTime date){
		lblMonth.setText(date.monthOfYear().getAsText());
		lblYear.setText(date.year().getAsText());
		date = date.withDayOfMonth(1).minusDays(date.withDayOfMonth(1).getDayOfWeek()-1);
		for(int week = 0; week < 5; week++){
			for(int day = 0; day < 7; day++){
				tableModel.setValueAt(new DaysEvents(date), week, day);
				date = date.plusDays(1);
			}
		}
	}
	
	static class tblRenderer extends DefaultTableCellRenderer{
		public Component getTableCellRenererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);
			setBackground(new Color(255, 255, 255));
			setBorder(null);
			setForeground(Color.black);
			return this;
		}
	}
	
	static class prev_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			current = current.minusMonths(1);
			refreshCal(current);
		}
		
	}
	
	static class next_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			current = current.plusMonths(1);
			refreshCal(current);
		}
	}

}
