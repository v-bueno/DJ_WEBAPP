package org.api;

import java.util.List;
import java.sql.Timestamp;

public interface EventDAO {
	
	List<Events> findAll();

	List<Events> findByDJ(String nomDeScene);
	
	List<Events> findByClub(String nomClub);
	
	List<Events> findByVille(String nomVille);
	
	List<Events> findByDate(Timestamp dateDebut);
}
