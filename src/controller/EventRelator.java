package controller;

import java.util.ArrayList;
import org.joda.time.LocalDate;
import org.joda.time.Period;

import model.Event;

public class EventRelator {
	/**
	 * If the list of events passed in is empty, then the process stops and returns the event exactly as it was passed in.
	 * The same happens if the words 'after' or 'before' are not found in the event name (input string).
	 * The method getReference() returns the substring appearing after the preposition, with an occurrence of the words 'the' or 'with' removed.
	 * This is checked against the concatenation of all event names (allEventNames()). If no match, the process stops. 
	 * If there is a match, getRelatedEvent() iterates through all the event in the list until it finds a name with which getReference() matches; 
	 * it finds the first event in the list for which the reference is a substring of the event name. This event is then used to work out the start
	 * time of the new event; if the preposition used was 'after', the start time of the new event is based on the end time (start time + period) of the referenced event,
	 * if the preposition used was 'before', the start time of the new event is based on the start time of the referenced event minus the period (of the new event).
	 * To work out the time, timeGap() uses the period enumerator to check for any period expressions to check if the user has specified any
	 * time gap between events, e.g. '2 hours before...'. If no such expression exists, the default gap is five minutes.
	 * 
	 * Would probably be better to check for a time gap in the EventController before passing to EventRelator;
	 * If a user has specified a gap of so many days, it may make more sense to check the entire list of events, 
	 * or perhaps just events with a day but no time.
	 */
	private static Event event;
	private static ArrayList<Event> list;
	
	public static Event compare(Event e, ArrayList<Event> l){
		System.out.println("Extracting relations...");
		event = e;
		list = l;
		doTheThing();
		return event;
	}
	
	private static void doTheThing(){
		if(list.size() == 0){
			System.out.println("No events on given day.");
			return;
		}
		if(!Regex.matches(event.getName(), Relation.all())){
			System.out.println("Didn't find 'after' or 'before'...");
			return;
		}
		System.out.println("Have found 'after' or 'before'...");
		System.out.println(allEventNames());
		System.out.println(getReference());
		if(Regex.matches(allEventNames(), getReference())){
			System.out.println("Found reference... "+getReference());
			Event related = getRelatedEvent();
			System.out.println("Related event: "+related.getName());
			Period gap = timeGap();
			if(Regex.matches(event.getName(), Relation.AFTER.regex())){
				if(related.getEnd() == null){
					event.setTime(related.getTime().plus(gap));
					event.setDay(related.getDay().plus(gap));
				} else {
					event.setTime(related.getEnd().toLocalTime().plus(gap));
					event.setDay(related.getDay().plus(gap));
				}
			} else {
				if(event.getPeriod() == null){
					event.setTime(related.getTime().minus(gap));
					event.setDay(related.getDay().minus(gap));
				} else {
					event.setTime(related.getTime().minus(gap).minus(event.getPeriod()));
					event.setDay(related.getDay().minus(gap).minus(event.getPeriod()));
				}
			}
				
		}
		String name = event.getName().replaceAll(Relation.all()+".*", "");
		event.setName(name);
		if(event.getDay() == null){
			event.setDay(LocalDate.now());
		}
	}
	
	private static Period timeGap(){
		Period gap = Period.minutes(5);
		for(PrdEnum val : PrdEnum.values()){
			if(Regex.matches(event.getName(), val.regex())){
				String match = Regex.getMatch(event.getName(), val.regex());
				gap = Period.parse(match, val.format()).normalizedStandard();
				event.setName(event.getName().replaceAll(match, ""));
			}
		}
		return gap;
	}
	
	private static String getReference(){
		String match = Regex.getMatch(event.getName().toLowerCase(), Relation.all() + ".+");
		match = match.replaceFirst(Relation.all(), "");
		match = match.replaceAll("[Tt]he ", "");
		match = match.replaceAll("with ", "");
		return match.toLowerCase();
	}
	
	private static Event getRelatedEvent(){
		Event toReturn = new Event();
		for(Event ev : list){
			if(Regex.matches(ev.getName().toLowerCase(), getReference())){
				toReturn = ev;
				break;
			}
		}
		return toReturn;
	}
	
	private static String allEventNames(){
		String output = "";
		for(Event ev : list){
			output = output + ev.getName();
		}
		return output.toLowerCase();
	}

}
