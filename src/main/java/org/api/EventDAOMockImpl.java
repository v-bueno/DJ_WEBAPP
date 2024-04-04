package org.api;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EventDAOMockImpl implements EventDAO {
	
	@Override
	public List<Event> findAll() {
		
		List<Event> ListeEvent = new ArrayList<Event>();
		
		// Creating Event with  specific beginning dates of Timestamp class and ending dates,  DJ names, and location names
		Event event1 = new Event(new Timestamp(2021, 11, 10, 22, 0, 0, 0), new Timestamp(2021, 11, 11, 4, 0, 0, 0), "DJ1", "Club1", "Ville1");
		Event event2 = new Event(new Timestamp(2021, 11, 10, 22, 0, 0, 0), new Timestamp(2021, 11, 11, 4, 0, 0, 0), "DJ2", "Club2", "Ville2");
		Event event3 = new Event(new Timestamp(2021, 11, 10, 22, 0, 0, 0), new Timestamp(2021, 11, 11, 4, 0, 0, 0), "DJ3", "Club3", "Ville3");
		Event event4 = new Event(new Timestamp(2021, 11, 10, 22, 0, 0, 0), new Timestamp(2021, 11, 11, 4, 0, 0, 0), "DJ4", "Club4", "Ville4");
		Event event5 = new Event(new Timestamp(2021, 11, 10, 22, 0, 0, 0), new Timestamp(2021, 11, 11, 4, 0, 0, 0), "DJ5", "Club5", "Ville5");
		
	    // Adding the Event to the list
	    ListeEvent.add(event1);
        ListeEvent.add(event2);
        ListeEvent.add(event3);
        ListeEvent.add(event4);
        ListeEvent.add(event5);
	    return ListeEvent;
	}
	
	@Override
	public List<Event> findByDJ(String nomDeScene) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> findByClub(String nomLieu) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Event> findByVille(String nomVille) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> findByDate(Timestamp dateDebut) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void insertEvent(Event event) {
	}

}
