package org.api;

import java.util.List;
import java.sql.Date;

public interface DJDao {
	List<DJ> getDJs();
	void deleteDJ(String nomDeScene);
	void addDJ(String nomDeScene, String nom, String prenom, String style_musical, String lieuResidence, Date dateNaissance);
	void updateDJ(String nomDeScene, String nom, String prenom, String style_musical, String lieuResidence, Date dateNaissance);
}