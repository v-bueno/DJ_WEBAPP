package org.api;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DJDaoMockImpl implements DJDao {

	@Override
	public List<DJ> getDJs() {
		List<DJ> djs=new ArrayList<>();
		
		Connection connection = DBManager.getInstance().getConnection();
		Statement statement;
		ResultSet rs;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM DJ");
			while (rs.next()) {
				String nomDeScene = rs.getString("nom_scene");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String genre = rs.getString("style_musical");
				String lieuResidence = rs.getString("lieu_residence");
				Date dateNaissance = rs.getDate("date_naissance");
				DJ dj = new DJ(nomDeScene, nom, prenom, genre, lieuResidence, dateNaissance);
				djs.add(dj);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return djs;
	}
	
	@Override
	public void deleteDJ(String nomDeScene) {
		Connection connection = DBManager.getInstance().getConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM DJ WHERE nom_scene = '" + nomDeScene + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void addDJ(String nomDeScene, String nom, String prenom, String style_musical, String lieuResidence,Date dateNaissance) {
		Connection connection = DBManager.getInstance().getConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(
					"INSERT INTO DJ (nom_scene, nom, prenom, style_musical, lieu_residence, date_naissance) VALUES ('"
							+ nomDeScene + "', '" + nom + "', '" + prenom + "', '" + style_musical + "', '" + lieuResidence
							+ "', '" + dateNaissance + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateDJ(String nomDeScene, String nom, String prenom, String genre, String lieuResidence,Date dateNaissance) {
		Connection connection = DBManager.getInstance().getConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate("UPDATE DJ SET nom = '" + nom + "', prenom = '" + prenom + "', style_musical = '" + genre
					+ "', lieu_residence = '" + lieuResidence + "', date_naissance = '" + dateNaissance
					+ "' WHERE nom_scene = '" + nomDeScene + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public DJDaoMockImpl() {
		// TODO Auto-generated constructor stub
	}

}