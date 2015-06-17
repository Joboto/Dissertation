package model;
import java.util.GregorianCalendar;

public class Meeting {
        private GregorianCalendar day;
        private String name; 
        
        public Meeting(GregorianCalendar d, String n){
                day = d;
                name = n; 
        }

        public GregorianCalendar getDay() {
                return day;
        }

        
        public String getName() {
                return name;
        }
}