package org.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDAOMockImpl implements EventDAO {

	@Override
	public List<Events> findByAll() {
		
		List<Events> ListeEvent = new ArrayList<Events>();
		
		// Creating events with  specific dates, DJ names, and location names
		Events event1 = new Events(new Date(), "DJ John", "Club XYZ", "City A");
        Events event2 = new Events(new Date(), "DJ Jane", "Club ABC", "City B");
        Events event3 = new Events(new Date(), "DJ Mike", "Venue 123", "City C");
        Events event4 = new Events(new Date(), "DJ Sarah", "Nightspot Z", "City D");
        Events event5 = new Events(new Date(), "DJ Alex", "Club 456", "City E");
		
	    // Adding the events to the list
	    ListeEvent.add(event1);
        ListeEvent.add(event2);
        ListeEvent.add(event3);
        ListeEvent.add(event4);
        ListeEvent.add(event5);
	    return ListeEvent;
	}
	
	@Override
	public List<Events> findByDJ(String nomDeScene) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Events> findByLieu(String nomLieu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Events> findByMonth(int month) {
		// TODO Auto-generated method stub
		return null;
	}

}
