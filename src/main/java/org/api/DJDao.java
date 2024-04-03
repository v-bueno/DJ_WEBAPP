package org.api;

import java.util.List;
import java.util.Date;

public interface DJDao {

	public List<DJ> getDJs();
	public void deleteDJ(String nomDeScene);
	public void addDJ(String nomDeScene, String nom, String prenom, String genre, String lieuResidence, Date dateNaissance);
	public void updateDJ(String nomDeScene, String nom, String prenom, String genre, String lieuResidence, Date dateNaissance);
}