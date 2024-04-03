package org.api;

import java.util.ArrayList;
import java.util.Date;
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
				String nomDeScene = rs.getString("nomDeScene");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String genre = rs.getString("genre");
				String lieuResidence = rs.getString("lieuResidence");
				Date dateNaissance = rs.getDate("dateNaissance");
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
			statement.executeUpdate("DELETE FROM DJ WHERE nomDeScene = '" + nomDeScene + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void addDJ(String nomDeScene, String nom, String prenom, String genre, String lieuResidence,Date dateNaissance) {
		Connection connection = DBManager.getInstance().getConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(
					"INSERT INTO DJ (nomDeScene, nom, prenom, genre, lieuResidence, dateNaissance) VALUES ('"
							+ nomDeScene + "', '" + nom + "', '" + prenom + "', '" + genre + "', '" + lieuResidence
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
			statement.executeUpdate("UPDATE DJ SET nom = '" + nom + "', prenom = '" + prenom + "', genre = '" + genre
					+ "', lieuResidence = '" + lieuResidence + "', dateNaissance = '" + dateNaissance
					+ "' WHERE nomDeScene = '" + nomDeScene + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public DJDaoMockImpl() {
		// TODO Auto-generated constructor stub
	}

}