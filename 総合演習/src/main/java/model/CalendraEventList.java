package model;

import java.util.List;

public class CalendraEventList {

	public void execute(CalendarEvent calendarEvent, List<CalendarEvent> CalendraList) {
		CalendraList.add(0,calendarEvent);
	}
}
