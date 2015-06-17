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
import controller.MeetingController;
import model.CalendarDetail;

public class WeekView extends JPanel {
        
        private static final long serialVersionUID = 1L;
        private JButton previousButton;
        private JButton todayButton;
        private JButton nextButton;
        private GregorianCalendar selectedDay;
        private JPanel weekTable;
        private JLabel weekLabel;
        private MeetingController controller;
        
        public WeekView(GregorianCalendar selected, MeetingController c) {
                
                setLayout(new BorderLayout());
                setPreferredSize(new Dimension(550, 400));
                setBackground(new Color(240, 244, 248));
                
                controller = c;
                selectedDay = selected;

                int year = CalendarDetail.getYear(selectedDay);
                int weekNumber = CalendarDetail.getWeekInYear(selectedDay);
                int date = CalendarDetail.getDateInMonth(selectedDay);
                if(weekNumber == 1 && date > 7) year++;
                
                weekTable = getWeekPanel();
                weekLabel = new JLabel("Week " + weekNumber + " of " + year);
                
                JPanel topPanel = new JPanel(new BorderLayout());
                topPanel.setPreferredSize(new Dimension(530, 30));
                topPanel.add(weekLabel, BorderLayout.WEST);
                topPanel.add(getButtonPanel() , BorderLayout.EAST);
                topPanel.setBackground(new Color(240, 244, 248));

                add(topPanel, BorderLayout.EAST);
                add(weekTable, BorderLayout.SOUTH);
        }
        
        public JPanel getButtonPanel() {
                
                previousButton  = new JButton(new ImageIcon("previous.png"));
                todayButton = new JButton(new ImageIcon("today.png"));
                nextButton  = new JButton(new ImageIcon("next.png"));
                
                previousButton.setBorderPainted(false);
                todayButton.setBorderPainted(false);
                nextButton.setBorderPainted(false);
                
                previousButton.setActionCommand("previousWeek");                
                todayButton.setActionCommand("today");
                nextButton.setActionCommand("nextWeek");

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
        
        public JPanel getWeekPanel() {
                GregorianCalendar[] weekArray = CalendarDetail.getCurrentWeek(selectedDay);  
                
                JButton currentDay;
                JPanel daysPanel = new JPanel(new GridLayout(1, 7, -1, 0));
                JPanel tableTopPanel = new JPanel(new GridLayout(1, 1));
                JPanel tablePanel = new JPanel(new BorderLayout());
                                
                daysPanel.setBackground(new Color(240, 244, 248));
                tableTopPanel.setBackground(new Color(240, 244, 248));
                tablePanel.setBackground(new Color(240, 244, 248));
                
                tableTopPanel.add(new JLabel(new ImageIcon("daysOfWeek.png")));
                
                int year, date, month;
                String monthName = "";
                
                for(int dayCount  = 0; dayCount < 7; dayCount++) {
                        year = CalendarDetail.getYear(weekArray[dayCount]);
                        month = CalendarDetail.getMonth(weekArray[dayCount]);
                        date = CalendarDetail.getDateInMonth(weekArray[dayCount]);
                        monthName = CalendarDetail.getMonthShortName(weekArray[dayCount]);
                        
                        currentDay  = new JButton(monthName + " " + date);
                        //currentDay.setBorderPainted(false);
                        currentDay.setActionCommand("addevent" + " " + monthName + " " 
                                        + date + " " + year + " " + month);             
                        currentDay.addActionListener(controller);
                        currentDay.setPreferredSize(new Dimension(72, 325));
                        daysPanel.add(currentDay);
                                                
                        /* 
                        currentDay = new JButton();
                        currentDay.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                        currentDay.setBackground(new Color(255, 255, 255));
                        currentDay.setPreferredSize(new Dimension(50, 325));
                        currentDay.addActionListener(controller);
                         */
                }
                
                tablePanel.add(tableTopPanel, BorderLayout.NORTH);
                tablePanel.add(daysPanel, BorderLayout.SOUTH);
                return tablePanel;
        }

}
