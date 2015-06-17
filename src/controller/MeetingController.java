package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import model.MyCalendar;
import model.MyCalendar.CurrentView;

public class MeetingController implements ActionListener{
        private MyCalendar cal;
        private GregorianCalendar selectedDay;
        private CurrentView currentView;
        public MeetingController(MyCalendar c) {
                cal = c;
                selectedDay = cal.getSelectedDay();
                currentView = cal.getCurrentView();
        }
        public void actionPerformed(ActionEvent e) {
                try {
                        String day[] = e.getActionCommand().split(" ");
                        if(day[0].equals("addevent")) {
                                int date = Integer.parseInt(day[2]);
                                int month = Integer.parseInt(day[4]);
                                int year = Integer.parseInt(day[3]);
                                String monthName = day[1];
                                String input = JOptionPane.showInputDialog("Date: " + monthName + " " + date  
                                       + ", " + year +"\n\n Event Title:");
                                selectedDay = new GregorianCalendar(year, month, date);
                                cal.addMeeting(selectedDay, input);
                        }
                        else if (e.getActionCommand().equals("DayView"))  currentView = CurrentView.DAY;
                        else if (e.getActionCommand().equals("MonthView")) currentView = CurrentView.MONTH;
                        else if (e.getActionCommand().equals("WeekView"))  currentView = CurrentView.WEEK;
                        else if ("previousMonth".equals(e.getActionCommand())) selectedDay.add(Calendar.MONTH, -1);
                        else if ("nextMonth".equals(e.getActionCommand())) selectedDay.add(Calendar.MONTH, 1);
                        else if ("previousDay".equals(e.getActionCommand())) selectedDay.add(Calendar.DATE, -1);
                        else if ("nextDay".equals(e.getActionCommand())) selectedDay.add(Calendar.DATE, 1);
                        else if ("previousWeek".equals(e.getActionCommand())) selectedDay.add(Calendar.WEEK_OF_YEAR, -1);
                        else if ("nextWeek".equals(e.getActionCommand())) selectedDay.add(Calendar.WEEK_OF_YEAR, 1);
                        else selectedDay = new GregorianCalendar();
                        cal.setCurrentView(currentView);
                        cal.setSelectedDay(selectedDay);
                        
                        /* ArrayList<Meeting> m = cal.getMeetingQueue();
                        for(int i = 0; i < m.size(); i++)
                                System.out.println(m.get(i).getName()); */
        } 
                catch (Exception ex) {
                        ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        }
}