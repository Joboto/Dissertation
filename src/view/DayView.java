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

public class DayView extends JPanel {
        
        private static final long serialVersionUID = 1L;
        private JButton previousButton;
        private JButton todayButton;
        private JButton nextButton;
        private GregorianCalendar selectedDay;
        private JPanel dayTable;
        private MeetingController controller;
        private JLabel dayLabel;
        
        public DayView(GregorianCalendar selected, MeetingController c) {
                
                setLayout(new BorderLayout());
                setPreferredSize(new Dimension(550, 400));
                setBackground(new Color(240, 244, 248));
                
                controller = c;
                selectedDay = selected;
                
                String monthShortName = CalendarDetail.getMonthShortName(selectedDay);
                String dayOfWeek = CalendarDetail.getWeekDayLongName(selectedDay);
                int year = CalendarDetail.getYear(selectedDay);
                int date = CalendarDetail.getDateInMonth(selectedDay);
                
                dayTable = getDayPanel(dayOfWeek);
                dayLabel = new JLabel(monthShortName + " " + date  + ", " + year);
                
                JPanel topPanel = new JPanel(new BorderLayout());
                topPanel.setPreferredSize(new Dimension(530, 30));
                topPanel.add(dayLabel , BorderLayout.WEST);
                topPanel.add(getButtonPanel() , BorderLayout.EAST);
                topPanel.setBackground(new Color(240, 244, 248));

                add(topPanel, BorderLayout.EAST);
                add(dayTable, BorderLayout.SOUTH);
        }
        
        public JPanel getButtonPanel() {

                previousButton  = new JButton(new ImageIcon("previous.png"));
                todayButton = new JButton(new ImageIcon("today.png"));
                nextButton  = new JButton(new ImageIcon("next.png"));
                
                previousButton.setBorderPainted(false);
                todayButton.setBorderPainted(false);
                nextButton.setBorderPainted(false);
                
                previousButton.setActionCommand("previousDay");         
                todayButton.setActionCommand("today");
                nextButton.setActionCommand("nextDay");

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
        
        public JPanel getDayPanel(String dayOfWeek) {
                
                String[][] dayArray = new String[1][1];
        dayArray[0][0] = "No meetings.";     
        String[] todayString = { dayOfWeek };

        JTable dayTable = new JTable(dayArray, todayString);
        dayTable.setPreferredScrollableViewportSize(new Dimension(500, 325));
        dayTable.setFillsViewportHeight(true);
        dayTable.setRowHeight(324);
        dayTable.setShowGrid(true);
        dayTable.setGridColor(Color.LIGHT_GRAY);
                
                JPanel tablePanel = new JPanel();
                tablePanel.add(new JScrollPane(dayTable));
                tablePanel.setBackground(new Color(240, 244, 248));
                return tablePanel;        
        }
}
