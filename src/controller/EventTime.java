package controller;

import org.joda.time.Chronology;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.ReadableInstant;

public class EventTime implements ReadableInstant {

	@Override
	public int compareTo(ReadableInstant o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get(DateTimeFieldType arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Chronology getChronology() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getMillis() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DateTimeZone getZone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAfter(ReadableInstant arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBefore(ReadableInstant arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEqual(ReadableInstant arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSupported(DateTimeFieldType arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Instant toInstant() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
