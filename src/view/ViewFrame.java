package view;

import controller.MeetingController;
import model.Meeting;
import model.MyCalendar;
import model.MyCalendar.CurrentView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ViewFrame extends JPanel implements Observer {
        
        private static final long serialVersionUID = 1L;
        private JButton dayButton;
        private JButton weekButton;
        private JButton monthButton;
        private JPanel eastPanel;
        private JPanel buttonPanel;
        private DayView dayPanel;
        private MonthView monthPanel;
        private WeekView weekPanel;
        private MeetingListView meetingPanel;
        private CurrentView currentView;
        private CurrentView previousView;
        private MyCalendar cal;
        private GregorianCalendar selectedDay;
        private MeetingController controller;
        
        public ViewFrame(MyCalendar c) {
                
                setLayout(new BorderLayout());
                
                cal = c;
                controller = new MeetingController(cal);
                selectedDay = cal.getSelectedDay();
                currentView = cal.getCurrentView();
                ArrayList<Meeting> meetingList = cal.getMeetingQueue();
                
                monthPanel = new MonthView(selectedDay, controller);            
                buttonPanel = getButtonPanel();
                meetingPanel = new MeetingListView(meetingList, controller);
                eastPanel = new JPanel(new BorderLayout());

                buttonPanel.setPreferredSize(new Dimension(350, 30));
                eastPanel.setPreferredSize(new Dimension(550, 450));
                meetingPanel.setPreferredSize(new Dimension(240, 450)); 
                
                this.setBackground(new Color(240, 244, 248));
                buttonPanel.setBackground(new Color(240, 244, 248));
                eastPanel.setBackground(new Color(240, 244, 248));
                meetingPanel.setBackground(new Color(240, 244, 248));           
                
                eastPanel.add(buttonPanel, BorderLayout.EAST);
                eastPanel.add(monthPanel, BorderLayout.SOUTH);
                this.add(eastPanel,  BorderLayout.EAST);
                this.add(meetingPanel, BorderLayout.WEST);
                
                cal.addObserver(this);
        }
        
        public JPanel getButtonPanel() {
                
                dayButton = new JButton("Day View");
                weekButton = new JButton("Week View");
                monthButton = new JButton("Month View");
                
                dayButton.setBorderPainted(false);
                weekButton.setBorderPainted(false);
                monthButton.setBorderPainted(false);
                                                
                dayButton.setActionCommand("DayView");  
                weekButton.setActionCommand("WeekView");        
                monthButton.setActionCommand("MonthView");      
                
                dayButton.addActionListener(controller);        
                weekButton.addActionListener(controller);       
                monthButton.addActionListener(controller);
                
                JPanel buttonPanel = new JPanel(new GridLayout(1,5,5,5));
                buttonPanel.add(dayButton);
                buttonPanel.add(weekButton);
                buttonPanel.add(monthButton);
                return buttonPanel;
        }
        
        public void update(Observable calendar, Object msg) {
                if(cal instanceof MyCalendar) {
                        selectedDay  = ((MyCalendar)  calendar).getSelectedDay();
                currentView = ((MyCalendar)  calendar).getCurrentView();
                previousView = ((MyCalendar)  calendar).getPreviousView();
                removedPreviousPanel();
                if(currentView.equals(CurrentView.DAY)) {
                        dayPanel = new DayView(selectedDay, controller);
                        eastPanel.add(dayPanel , BorderLayout.SOUTH);
                }
                else if(currentView.equals(CurrentView.MONTH)) {
                    monthPanel = new MonthView(selectedDay, controller);
                    eastPanel.add(monthPanel , BorderLayout.SOUTH);
                }
                else if(currentView.equals(CurrentView.WEEK)) {
                    weekPanel = new WeekView(selectedDay, controller);
                    eastPanel.add(weekPanel , BorderLayout.SOUTH);
                }
                meetingPanel = new MeetingListView(cal.getMeetingQueue(), controller);
                this.add(meetingPanel, BorderLayout.WEST);
                validate();
                }
    }
        
        public void removedPreviousPanel() {
                if(previousView.equals(CurrentView.DAY)) eastPanel.remove(dayPanel);
                else if(previousView.equals(CurrentView.MONTH)) eastPanel.remove(monthPanel);
                else if(previousView.equals(CurrentView.WEEK)) eastPanel.remove(weekPanel);
                remove(meetingPanel);
        }
}
