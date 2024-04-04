package org.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LieuDAOImpl implements LieuDAO {

	@Override
	public List<Lieu> findAll() {
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement;
		List<Lieu> ListeLieu = new ArrayList<Lieu>();
		try {
			statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("Select * FROM Lieu") ;
			while (rs.next()) {
				String nomSite = rs.getString("nom_site");
				String villeSite = rs.getString("ville_site");
				String pays = rs.getString("pays");
				String continent = rs.getString("continent");
				
				Lieu lieu = new Lieu(nomSite, villeSite, pays, continent);
				
				ListeLieu.add(lieu);
            }
		} catch (SQLException e) {
			e.printStackTrace();  
		}

		return ListeLieu;
	}
	
	@Override
	public List<Lieu> findByVille(String ville) {
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement;
		List<Lieu> ListeLieu = new ArrayList<Lieu>();
		try {
			statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("Select * FROM Lieu WHERE ville_Site = '" + ville + "'") ;
			while (rs.next()) {
				String nomSite = rs.getString("nom_site");
				String villeSite = rs.getString("ville_site");
				String pays = rs.getString("pays");
				String continent = rs.getString("continent");
				
				Lieu lieu = new Lieu(nomSite, villeSite, pays, continent);
				
				ListeLieu.add(lieu);
            }
		} catch (SQLException e) {
			e.printStackTrace();  
		}

		return ListeLieu;
	}

	@Override
	public List<Lieu> findByPays(String pays_find) {
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement;
		List<Lieu> ListeLieu = new ArrayList<Lieu>();
		try {
			statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("Select * FROM Lieu WHERE pays = '" + pays_find + "'") ;
			while (rs.next()) {
				String nomSite = rs.getString("nom_site");
				String villeSite = rs.getString("ville_site");
				String pays = rs.getString("pays");
				String continent = rs.getString("continent");
				
				Lieu lieu = new Lieu(nomSite, villeSite, pays, continent);
				
				ListeLieu.add(lieu);
            }
		} catch (SQLException e) {
			e.printStackTrace();  
		}

		return ListeLieu;
	}

	@Override
	public List<Lieu> findByContinent(String continent_find) {
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement;
		List<Lieu> ListeLieu = new ArrayList<Lieu>();
		try {
			statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("Select * FROM Lieu WHERE continent = '" + continent_find + "'") ;
			while (rs.next()) {
				String nomSite = rs.getString("nom_site");
				String villeSite = rs.getString("ville_site");
				String pays = rs.getString("pays");
				String continent = rs.getString("continent");
				
				Lieu lieu = new Lieu(nomSite, villeSite, pays, continent);
				
				ListeLieu.add(lieu);
            }
		} catch (SQLException e) {
			e.printStackTrace();  
		}

		return ListeLieu;
	}

}
