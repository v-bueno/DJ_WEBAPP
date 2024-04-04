package org.api;

import java.util.List;

public interface LieuDAO {
	List<Lieu> findAll() ;
	List<Lieu> findByVille(String ville);
	List<Lieu> findByPays(String pays);
	List<Lieu> findByContinent(String continent);
}
