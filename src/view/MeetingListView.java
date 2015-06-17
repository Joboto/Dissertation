package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.MeetingController;
import model.CalendarDetail;
import model.Meeting;

public class MeetingListView extends JPanel {

        private static final long serialVersionUID = 1L;
        JButton addEventButton;
        
        public MeetingListView(ArrayList<Meeting> meetingList, MeetingController controller) {
                
                setLayout(new BorderLayout());
                setPreferredSize(new Dimension(240, 450));
                setBackground(new Color(240, 244, 248));
                
                addEventButton = new JButton("ADD EVENT");
                //add(addEventButton, BorderLayout.CENTER);
                add(getInstructionPanel(), BorderLayout.NORTH);
                add(getMeetingListPanel(meetingList), BorderLayout.SOUTH);
                
        }
        
        public JPanel getInstructionPanel() {
                Font font = new Font(Font.SANS_SERIF, Font.ITALIC, 10);
                
                JLabel instruction = new JLabel("Click any day on the calendar to add meetings.");
                instruction.setFont(font);
                
                JPanel instructionPanel = new JPanel();
                instructionPanel.add(instruction);
                instructionPanel.setPreferredSize(new Dimension(240, 100));
                instructionPanel.setBackground(new Color(240, 244, 248));
                return instructionPanel;
        }
        
        public JPanel getMeetingListPanel(ArrayList<Meeting> meetingList) {
                String eventString = "";
                String dateString = "";
                GregorianCalendar day;
                if(meetingList.size() > 0) {
                        for(int i = 0; i < meetingList.size(); i++) {
                        day = meetingList.get(i).getDay();
                        dateString = CalendarDetail.getDateInMonth(day) + " " 
                                                + CalendarDetail.getMonthShortName(day) + " " 
                                                + CalendarDetail.getYear(day);
                        eventString += dateString + "\n" + meetingList.get(i).getName() + "\n\n";
                }
                }
                else 
                        eventString = "No Meeting Yet"; 
                
                JTextArea eventLabel = new JTextArea(eventString);
        JPanel tablePanel = new JPanel();
        tablePanel.add(eventLabel);
        //tablePanel.add(new JTextArea(eventString));
        tablePanel.add(new JScrollPane(eventLabel));
        tablePanel.setPreferredSize(new Dimension(230, 325));
        tablePanel.setBackground(new Color(240, 244, 248));
        return tablePanel;
        
        }
}
