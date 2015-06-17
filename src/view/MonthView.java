package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import controller.MeetingController;
import model.CalendarDetail;

public class MonthView extends JPanel{

        private static final long serialVersionUID = 1L;
        private JButton previousButton;
        private JButton todayButton;
        private JButton nextButton;
        private GregorianCalendar selectedDay;
        private JPanel monthTable;
        private JLabel monthLabel;
        private MeetingController controller;
        
        public MonthView(GregorianCalendar selected, MeetingController c) {
                setLayout(new BorderLayout());
                setPreferredSize(new Dimension(550, 400));
                setBackground(new Color(240, 244, 248));
                
                controller      = c;
                selectedDay = selected;
                
                int month = CalendarDetail.getMonth(selectedDay);
                int year = CalendarDetail.getYear(selectedDay);
                int firstDayOfMonth = CalendarDetail.getFirstDayOfMonth(selectedDay);
                String monthName = CalendarDetail.getMonthName(selectedDay);
                
                monthTable = getMonthPanel(year, month, firstDayOfMonth);
                monthLabel = new JLabel(monthName + ", " + year);       
                
                JPanel topPanel = new JPanel(new BorderLayout());
                topPanel.setPreferredSize(new Dimension(530, 30));
                topPanel.add(monthLabel , BorderLayout.WEST);
                topPanel.add(getButtonPanel() , BorderLayout.EAST);
                topPanel.setBackground(new Color(240, 244, 248));

                add(topPanel, BorderLayout.EAST);
                add(monthTable, BorderLayout.SOUTH);
        }
        
        public JPanel getButtonPanel() {
                
                previousButton  = new JButton(new ImageIcon("previous.png"));
                todayButton = new JButton(new ImageIcon("today.png"));
                nextButton  = new JButton(new ImageIcon("next.png"));
                
                previousButton.setBorderPainted(false);
                todayButton.setBorderPainted(false);
                nextButton.setBorderPainted(false);
                
                previousButton.setActionCommand("previousMonth");               
                todayButton.setActionCommand("today");
                nextButton.setActionCommand("nextMonth");
                
                previousButton.addActionListener(controller);
                todayButton.addActionListener(controller);
                nextButton.addActionListener(controller);       
                
                JPanel buttonPanel = new JPanel(new GridLayout(1,5,5,5));               
                buttonPanel.add(previousButton);
                buttonPanel.add(todayButton);
                buttonPanel.add(nextButton);
                buttonPanel.setBackground(new Color(240, 244, 248));
                buttonPanel.setPreferredSize(new Dimension(350, 30));
                return buttonPanel;
        }
        
        public JPanel getMonthPanel(int year, int month, int firstDay) {
        
                Integer[][] monthArray = new Integer[CalendarDetail.MAX_WEEKS_IN_MONTH][CalendarDetail.DAYS_IN_WEEK];
        int startDayOfMonth = firstDay - 1;
        int dayIndex = startDayOfMonth;
        int endDateOfMonth = CalendarDetail.getDaysInMonth(month, year);
        int weekIndex = 0;
        
        for(int dayCounter = 1; dayCounter <= endDateOfMonth; dayCounter++) {
                monthArray[weekIndex][dayIndex] = dayCounter;
                dayIndex++;
                if(dayIndex > 6) {
                        dayIndex = 0;
                        weekIndex++;
                }
        }
        
        JTable monthTable = new JTable(monthArray, CalendarDetail.DAYS_OF_WEEK);
        monthTable.setPreferredScrollableViewportSize(new Dimension(500, 325));
        monthTable.setFillsViewportHeight(true);
        monthTable.setRowHeight(54);
        monthTable.setShowGrid(true);
        monthTable.setGridColor(Color.LIGHT_GRAY);
        
        JPanel tablePanel = new JPanel();
        tablePanel.add(new JScrollPane(monthTable));
        tablePanel.setBackground(new Color(240, 244, 248));
        return tablePanel;
        }
}