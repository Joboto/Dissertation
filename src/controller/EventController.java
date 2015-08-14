package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import model.Event;
import model.MyJodaCal;

public class EventController implements ActionListener {
	/**
	 * Primary class within the Controller.
	 * Responsible for changing the month being viewed and adding, updating and deleting events.
	 * When adding, it first creates an event with just an event name which is set as the input string.
	 * It then passes the event to the DateTimeExtractor. Once date, time and period are extracted it then checks for relevant prepositions for the other extractors.
	 * The event is passed to the relevant extractors in reverse order of the corresponding preposition's appearance in the input string;
	 * i.e. if 'with John' appears at the very end of the input statement, participants will be extracted first.
	 * When extracting relations (EventRelator) it passes in an ArrayList of events for the same day as the event. If the event does not have a day set,
	 * the events from the current day are passed in. 
	 * 
	 * Each extractor is accessed/used statically.
	 */
	private MyJodaCal cal;
	private DateTime selectedDay;
	
	public EventController(MyJodaCal c){
		cal = c;
		selectedDay = cal.getSelectedDate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try{
			if(e.getActionCommand().equals("previous")) {selectedDay = selectedDay.minusMonths(1);}
			if(e.getActionCommand().equals("today")) {selectedDay = new DateTime();}
			if(e.getActionCommand().equals("next")) {selectedDay = selectedDay.plusMonths(1);}
			if(e.getActionCommand().equals("deleteEvent")){System.out.println(e.getSource().getClass().getName());}
			cal.setSelectedDate(selectedDay);
			//cal.notifyObservers();
		} catch(Exception ex){
			ex.printStackTrace();
		}

	}
	
	public void addEvent(String input){
		goodBye(input);
		Event toAdd = DateTimeExtractor.extract(input);
		ArrayList<Object> prepositions = collectPreps(toAdd);
		System.out.println(prepositions.size()+" prepositions found.");
		for(Object prep : prepositions){
			switch((PrepCombo) prep){
			case LOC:
				System.out.println("Case: "+PrepCombo.LOC.toString());
				toAdd = LocationExtractor.extract(toAdd);
				break;
			case PRTS:
				System.out.println("Case: "+PrepCombo.PRTS.toString());
				if(Regex.matches(toAdd.getName(), Relation.all() + "meet(ing)? .*with .*")){
					toAdd = EventRelator.compare(toAdd, relevantEvents(toAdd));
				} //else {
					toAdd = ParticipantExtractor.extract(toAdd);
				//}//prepositions.remove(PrepCombo.REL);
				break;
			case REL:
				System.out.println("Case: "+PrepCombo.REL.toString());
				toAdd = EventRelator.compare(toAdd, relevantEvents(toAdd));
				break;
			case AGENDA:
				System.out.println("Case: "+PrepCombo.AGENDA);
				toAdd = AgendaExtractor.extract(toAdd);
				break;
			}
		}
		
		toAdd = MeetingBuilder.check(toAdd);
		
		if(toAdd.getAgenda() != null){
			toAdd.setName(toAdd.getName().concat(toAdd.getAgenda()));
		}
		
		
		cal.addEvent(toAdd);
		
		if(toAdd.getDay() != null){
			selectedDay = toAdd.getStart();
		}
		cal.setSelectedDate(selectedDay);
	}
	
	private ArrayList<Event> relevantEvents(Event event){
		DateTime dt;
		if(event.getDay() == null){
			dt = DateTime.now();
		} else {
			dt = event.getDay().toDateTime(event.getTime());
		}
		return cal.getDaysEvents(dt);
	}
	
	private static ArrayList<Object> collectPreps(Event event){
		String[] words = event.getName().split(" ");
		ArrayList<Object> found = new ArrayList<>();
		for(int loop = words.length - 1; loop >= 0; loop--){
			for(PrepCombo combo : PrepCombo.values()){
				if(Regex.matches(" "+words[loop]+" ", combo.regex())){
					if(!found.contains(combo)){
						System.out.println("Found '"+words[loop]+"', adding '"+combo.toString()+"'");
						found.add(combo);
					} else {
						System.out.println("Found '"+words[loop]+"', but not adding '"+combo.toString()+"' as it's there already");
					}
					
				}
			}
		}
		return found;
	}
	
	public void deleteEvent(Event event){
		cal.getEvents().removeEvent(event);
		cal.setSelectedDate(selectedDay);
	}

	public void updateEvent(Event event, HashMap<String, String>fields) {
		cal.getEvents().getEventList().remove(event);
		Event newEvent = new Event(fields.get("name"));
		if(!fields.get("startDay").isEmpty()){
			int day = Integer.parseInt(fields.get("startDay"));
			int month = Integer.parseInt(fields.get("startMonth"));
			int year = Integer.parseInt(fields.get("startYear"));
			newEvent.setDay(new LocalDate(year, month, day));
		}
		if(!fields.get("startHours").isEmpty()){
			System.out.println("Updating start time.");
			int hours = Integer.parseInt(fields.get("startHours"));
			int minutes = Integer.parseInt(fields.get("startMinutes"));
			LocalTime update = new LocalTime(hours, minutes);
			TimePhrase phrase = event.getTimePhrase();
			newEvent.setTime(update);
			if(phrase != null){
				System.out.println("Correcting '"+phrase.toString()+"' to "+update.toString());
				phrase.correction(update);
			}
			if(event.getDay() == null){
				event.setDay(LocalDate.now());
			}
		}
		if(!fields.get("periodDays").isEmpty() || !fields.get("periodDays").isEmpty() || !fields.get("periodMinutes").isEmpty()){
			int days, hours, minutes;
			if(!fields.get("periodDays").isEmpty()){
				days = Integer.parseInt(fields.get("periodDays"));
			} else {
				days = 0;
			}
			if(!fields.get("periodHours").isEmpty()){
				hours = Integer.parseInt(fields.get("periodHours"));
			} else {
				hours = 0;
			}
			if(!fields.get("periodMinutes").isEmpty()){
				minutes = Integer.parseInt(fields.get("periodMinutes"));
			} else {
				minutes = 0;
			}
			newEvent.setPeriod(Period.days(days).withHours(hours).withMinutes(minutes).normalizedStandard());
		}
		if(!fields.get("participants").isEmpty()){
			String[] partspts = fields.get("participants").split(", ");
			for(String pt : partspts){
				newEvent.addParticipant(pt);
			}
			
		}
		if(!fields.get("location").isEmpty()){
			newEvent.setLocation(fields.get("location"));
		}
		System.out.println("adding "+newEvent.getTitle());
		cal.addEvent(newEvent);
		if(newEvent.getDay() != null){
			selectedDay = newEvent.getStart();
		}
		cal.setSelectedDate(selectedDay);
	}
	
	public void goodBye(String input){
		if(input.toLowerCase().matches("goodbye|quit|exit")){
			JOptionPane.showMessageDialog(null, "See ya!", "Goodbye", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
	}
	

}
