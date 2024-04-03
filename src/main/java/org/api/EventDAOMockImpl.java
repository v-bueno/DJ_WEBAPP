package org.api;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EventDAOMockImpl implements EventDAO {
	
	@Override
	public List<Events> findAll() {
		
		List<Events> ListeEvent = new ArrayList<Events>();
		
		// Creating events with  specific beginning dates of Timestamp class and ending dates,  DJ names, and location names
		Events event1 = new Events(new Timestamp(2021, 11, 10, 22, 0, 0, 0), new Timestamp(2021, 11, 11, 4, 0, 0, 0), "DJ1", "Club1", "Ville1");
		Events event2 = new Events(new Timestamp(2021, 11, 10, 22, 0, 0, 0), new Timestamp(2021, 11, 11, 4, 0, 0, 0), "DJ2", "Club2", "Ville2");
		Events event3 = new Events(new Timestamp(2021, 11, 10, 22, 0, 0, 0), new Timestamp(2021, 11, 11, 4, 0, 0, 0), "DJ3", "Club3", "Ville3");
		Events event4 = new Events(new Timestamp(2021, 11, 10, 22, 0, 0, 0), new Timestamp(2021, 11, 11, 4, 0, 0, 0), "DJ4", "Club4", "Ville4");
		Events event5 = new Events(new Timestamp(2021, 11, 10, 22, 0, 0, 0), new Timestamp(2021, 11, 11, 4, 0, 0, 0), "DJ5", "Club5", "Ville5");
		
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
	public List<Events> findByClub(String nomLieu) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Events> findByVille(String nomVille) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Events> findByDate(Timestamp dateDebut) {
		// TODO Auto-generated method stub
		return null;
	}

}
