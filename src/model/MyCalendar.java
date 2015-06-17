package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observable;

public class MyCalendar extends Observable {
        
        private GregorianCalendar selectedDay;
        public enum CurrentView { DAY, WEEK, MONTH }
        private CurrentView currentView;
        private CurrentView previousView;
        private MeetingQueue meetingQueue;
        
        public MyCalendar() {
                selectedDay = new GregorianCalendar();
                meetingQueue = new MeetingQueue();
                currentView = CurrentView.MONTH;
                previousView = currentView;
        }
        
        public GregorianCalendar getSelectedDay() {
                return selectedDay;
        }

        public ArrayList<Meeting> getMeetingQueue() {
                return meetingQueue.getMeetingList();
        }
        public void setSelectedDay(GregorianCalendar day) {
                selectedDay = day;
        setChanged();
        notifyObservers();
        clearChanged();
    }
        public CurrentView getCurrentView() {
                return currentView;
        }
        
        public CurrentView getPreviousView() {
                return previousView;
        }
        
        public void setCurrentView(CurrentView view) {
                previousView = currentView;
                currentView = view;
        }
        
        public void addMeeting(GregorianCalendar day, String name) {
                meetingQueue.addMeeting(new Meeting(day, name));
        }
}