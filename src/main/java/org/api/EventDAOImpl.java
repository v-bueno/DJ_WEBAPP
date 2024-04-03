package org.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
public class EventDAOImpl implements EventDAO {
	
	@Override
	public List<Events> findAll() {
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement;
		List<Events> ListeEvent = new ArrayList<Events>();
		try {
			statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("Select * FROM Evenement") ;
			while (rs.next()) {
				Timestamp dateDebut = rs.getTimestamp("date_Debut");
				Timestamp dateFin = rs.getTimestamp("date_Fin");
				String nomDj = rs.getString("nom_Dj");
				String nomClub = rs.getString("nom_lieu");
				String ville = rs.getString("ville_lieu");
				
				Events event = new Events(dateDebut, dateFin, nomDj, nomClub, ville);
				
				ListeEvent.add(event);
				//print the events
                System.out.println("Event: " + event.getDateDebut() + " " + event.getDateFin() + " " + event.getNomDj() + " " + event.getNomClub() + " " + event.getNomVille());			}
		} catch (SQLException e) {
			e.printStackTrace();  
		}

		return ListeEvent;
	}
	
	@Override
	public List<Events> findByDJ(String nomDeScene) {
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement;
		List<Events> ListeEvent = new ArrayList<Events>();
		try {
			statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("Select * FROM Evenement WHERE nom_DJ = '" + nomDeScene + "'") ;
			while (rs.next()) {
				Timestamp dateDebut = rs.getTimestamp("dateDebut");
				Timestamp dateFin = rs.getTimestamp("dateFin");
				String nomDj = rs.getString("nomDj");
				String nomClub = rs.getString("nomClub");
				String ville = rs.getString("ville");
				
				Events event = new Events(dateDebut, dateFin, nomDj, nomClub, ville);
				
				ListeEvent.add(event);
				//print the events
                System.out.println("Event: " + event.getDateDebut() + " " + event.getDateFin() + " " + event.getNomDj() + " " + event.getNomClub() + " " + event.getNomVille());			}
		} catch (SQLException e) {
			e.printStackTrace();  
		}

		return ListeEvent;
	}

	@Override
	public List<Events> findByClub(String nom_Club) {
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement;
		List<Events> ListeEvent = new ArrayList<Events>();
		try {
			statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("Select * FROM Evenement WHERE nom_lieu = '" + nom_Club + "'") ;
			while (rs.next()) {
				Timestamp dateDebut = rs.getTimestamp("dateDebut");
				Timestamp dateFin = rs.getTimestamp("dateFin");
				String nomDj = rs.getString("nomDj");
				String nomClub = rs.getString("nomClub");
				String ville = rs.getString("ville");
				
				Events event = new Events(dateDebut, dateFin, nomDj, nomClub, ville);
				
				ListeEvent.add(event);
				//print the events
                System.out.println("Event: " + event.getDateDebut() + " " + event.getDateFin() + " " + event.getNomDj() + " " + event.getNomClub() + " " + event.getNomVille());			}
		} catch (SQLException e) {
			e.printStackTrace();  
		}

		return ListeEvent;
	}
	
	@Override
	public List<Events> findByVille(String nomVille) {
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement;
		List<Events> ListeEvent = new ArrayList<Events>();
		try {
			statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("Select * FROM Evenement WHERE ville = '" + nomVille + "'");
			while (rs.next()) {
				Timestamp dateDebut = rs.getTimestamp("dateDebut");
				Timestamp dateFin = rs.getTimestamp("dateFin");
				String nomDj = rs.getString("nomDj");
				String nomClub = rs.getString("nomClub");
				String ville = rs.getString("ville");

				Events event = new Events(dateDebut, dateFin, nomDj, nomClub, ville);

				ListeEvent.add(event);
				// print the events
				System.out.println("Event: " + event.getDateDebut() + " " + event.getDateFin() + " " + event.getNomDj()
						+ " " + event.getNomClub() + " " + event.getNomVille());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ListeEvent;
	}
	
	@Override
	public List<Events> findByDate(Timestamp dateDebut) {
		// TODO Auto-generated method stub
		return null;
	}

}
