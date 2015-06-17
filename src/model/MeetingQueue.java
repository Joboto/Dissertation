package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MeetingQueue {
        private ArrayList<Meeting> meetingList;
        
        public MeetingQueue() {
                meetingList = new ArrayList<Meeting>();
        }

        public void addMeeting(Meeting m) {
                GregorianCalendar mDay = m.getDay();
                if(meetingList.size() == 0) {
                        meetingList.add(m);
                        return;
                }
                GregorianCalendar dayBefore = meetingList.get(0).getDay();
                GregorianCalendar dayAfter = meetingList.get(meetingList.size()-1).getDay();
                if(mDay.before(dayBefore)) {
                        meetingList.add(0, m);
                        return;
                }
                if(mDay.after(dayAfter)) {
                        meetingList.add(m);
                        return;
                }
                for(int i = 1; i < meetingList.size(); i++) {
                        dayBefore = meetingList.get(i-1).getDay();
                        dayAfter = meetingList.get(i).getDay();
                        if(mDay.after(dayBefore) && mDay.before(dayAfter)) {
                                meetingList.add(i, m);
                                return;
                        }
                        if(mDay.equals(dayAfter)) {
                                meetingList.add(i, m);
                                return;
                        }
                }       
        }

        public void removeMeeting(Meeting m) {
                if(meetingList.size() == 0) return;
                meetingList.remove(meetingList.indexOf(m)); // Check
        }
        
        public int getSize() {
                return meetingList.size();
        }
        
        public ArrayList<Meeting>getMeetingList() {
                return meetingList;
        }
}