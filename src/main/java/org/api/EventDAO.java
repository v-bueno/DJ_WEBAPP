package org.api;

import java.util.List;
import java.sql.Timestamp;

public interface EventDAO {
	
	List<Event> findAll();

	List<Event> findByDJ(String nomDeScene);
	
	List<Event> findByClub(String nomClub);
	
	List<Event> findByVille(String nomVille);
	
	List<Event> findByDate(Timestamp dateDebut);
	
	void insertEvent(Event event);
}
