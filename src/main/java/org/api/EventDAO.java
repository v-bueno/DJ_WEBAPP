package org.api;

import java.util.List;

public interface EventDAO {
	
	List<Events> findByAll();

	List<Events> findByDJ(String nomDeScene);
	
	List<Events> findByLieu(String nomLieu);
	
	List<Events> findByMonth(int month);
}
