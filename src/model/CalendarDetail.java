package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarDetail {
        
        public static final int MAX_WEEKS_IN_MONTH = 6;
        public static final int DAYS_IN_WEEK = 7;
        public static final String[] DAYS_OF_WEEK_LONG_NAMES = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        public static final String[] DAYS_OF_WEEK = {"Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat"};
        public static final String[] MONTH_NAMES = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        public static final String[] MONTH_SHORT_NAMES = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        
        // Get the year
        public static int getYear(GregorianCalendar day) {
                return day.get(Calendar.YEAR);
        }
        
        // Get the month
        public static int getMonth(GregorianCalendar day) {
                return day.get(Calendar.MONTH);
        }
        
        // Get the month name
        public static String getMonthName(GregorianCalendar day) {
                return MONTH_NAMES[getMonth(day)];
        }
        
        // Get the month short name
        public static String getMonthShortName(GregorianCalendar day) {
                return MONTH_SHORT_NAMES[getMonth(day)];
        }
        
        // 1, 2, .. 30, 31
        public static int getDateInMonth(GregorianCalendar day) {
                return day.get(Calendar.DAY_OF_MONTH); 
        }
        
        // 1, 2, .. 51, 52, 53
        public static int getWeekInYear(GregorianCalendar day) {
                return day.get(Calendar.WEEK_OF_YEAR); 
        }
        
        // Is it Monday or Tuesday or ... ?
        public static int getWeekDay(GregorianCalendar day) {
                return day.get(Calendar.DAY_OF_WEEK); 
        }
        
        // Is it Monday or Tuesday or ... ?
        public static String getWeekDayName(GregorianCalendar day) {
                return DAYS_OF_WEEK[getWeekDay(day) - 1]; 
        }
        
        // Is it Monday or Tuesday or ... ?
        public static String getWeekDayLongName(GregorianCalendar day) {
                return DAYS_OF_WEEK_LONG_NAMES[getWeekDay(day) - 1]; 
        }
        
        // 1st of that month start with Monday or Tuesday or ... ?
        public static int getFirstDayOfMonth(GregorianCalendar day) {
                GregorianCalendar firstDay = new GregorianCalendar(getYear(day), getMonth(day), 1);
                return firstDay.get(Calendar.DAY_OF_WEEK); 
        }
        
        // Get number of Days in that month
        public static int getDaysInMonth(GregorianCalendar day) {
                int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                if(day.isLeapYear(getYear(day))) 
                        DAYS_IN_MONTH[1] = 29;
                return DAYS_IN_MONTH[getMonth(day)];
        }
        
        // Get number of Days in that month
        public static int getDaysInMonth(int month, int year) {
                GregorianCalendar day = new GregorianCalendar(year, month, 1);
                return getDaysInMonth(day);
        }
        
        public static GregorianCalendar[] getCurrentWeek(GregorianCalendar day) {
                GregorianCalendar currentDay = (GregorianCalendar) day.clone();
                GregorianCalendar[] weekArray = new GregorianCalendar[CalendarDetail.DAYS_IN_WEEK];
                int numberOfDaysAfterSunday = getWeekDay(day) * -1;
                currentDay.add(Calendar.DATE, numberOfDaysAfterSunday + 1);
                
                for(int dayCount = 0; dayCount < 7; dayCount++) {
                        weekArray[dayCount] = (GregorianCalendar) currentDay.clone();
                        currentDay.add(Calendar.DATE, 1);
                }               
                return weekArray;
        }
        
        // String Array for a week with date and month
        /*      public static String[] getCurrentWeek(GregorianCalendar day) {
                String[] weekArray = new String[CalendarDetail.DAYS_IN_WEEK];
                int numberOfDaysAfterSunday = getWeekDay(day);
                int dateOnSunday = getDateInMonth(day) - numberOfDaysAfterSunday + 1;
                int lastDayInThisMonth = getDaysInMonth(day);
                if(dateOnSunday < 1) {
                        day.add(Calendar.MONTH, -1);
                        int remainingDaysInMonth = (dateOnSunday * -1) + 1;
                        int numberOfDaysInPreviousMonth = getDaysInMonth(day) - remainingDaysInMonth + 1;
                        
                        for(int dayCounter = 0; dayCounter < remainingDaysInMonth; dayCounter++) {
                        weekArray[dayCounter] = numberOfDaysInPreviousMonth + " " + CalendarDetail.getMonthShortName(day);
                        numberOfDaysInPreviousMonth++;
                }
                        day.add(Calendar.MONTH, 1);
                        int startDayOfMonth = 1;
                        for(int dayCounter = remainingDaysInMonth; dayCounter < 7; dayCounter++) {
                        weekArray[dayCounter] = startDayOfMonth + " " + CalendarDetail.getMonthShortName(day);
                        startDayOfMonth++;
                }
                }
                else if ((dateOnSunday + 7) > lastDayInThisMonth) {
                        int firstOfMonth = 1;
                        for(int dayCounter = 0; dayCounter <= lastDayInThisMonth - dateOnSunday; dayCounter++) 
                                weekArray[dayCounter] =  (dateOnSunday + dayCounter) + " " + CalendarDetail.getMonthShortName(day);
                        day.add(Calendar.MONTH, 1);
                        for(int dayCounter = lastDayInThisMonth - dateOnSunday + 1; dayCounter < 7; dayCounter++) {
                                weekArray[dayCounter] = firstOfMonth + " " + CalendarDetail.getMonthShortName(day);
                                firstOfMonth++;
                        }
                        day.add(Calendar.MONTH, -1);
                }
                else {
                        for(int dayCounter = 0; dayCounter < 7; dayCounter++) {
                        weekArray[dayCounter] =  dateOnSunday + " " + CalendarDetail.getMonthShortName(day);
                         dateOnSunday++;
                }
                }               
                return weekArray;
        } */
}