package org.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
public class EventDAOImpl implements EventDAO {
	
	@Override
	public List<Event> findAll() {
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement;
		List<Event> ListeEvent = new ArrayList<Event>();
		try {
			statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("Select * FROM Evenement") ;
			while (rs.next()) {
				Timestamp dateDebut = rs.getTimestamp("date_Debut");
				Timestamp dateFin = rs.getTimestamp("date_Fin");
				String nomDj = rs.getString("nom_Dj");
				String nomClub = rs.getString("nom_lieu");
				String ville = rs.getString("ville_lieu");
				
				Event event = new Event(dateDebut, dateFin, nomDj, nomClub, ville);
				
				ListeEvent.add(event);
				//print the Event
                System.out.println("Event: " + event.getDateDebut() + " " + event.getDateFin() + " " + event.getNomDj() + " " + event.getNomClub() + " " + event.getNomVille());			}
		} catch (SQLException e) {
			e.printStackTrace();  
		}

		return ListeEvent;
	}
	
	@Override
	public List<Event> findByDJ(String nomDeScene) {
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement;
		List<Event> ListeEvent = new ArrayList<Event>();
		try {
			statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("Select * FROM Evenement WHERE nom_DJ = '" + nomDeScene + "'") ;
			while (rs.next()) {
				Timestamp dateDebut = rs.getTimestamp("dateDebut");
				Timestamp dateFin = rs.getTimestamp("dateFin");
				String nomDj = rs.getString("nomDj");
				String nomClub = rs.getString("nomClub");
				String ville = rs.getString("ville");
				
				Event event = new Event(dateDebut, dateFin, nomDj, nomClub, ville);
				
				ListeEvent.add(event);
				//print the Event
                System.out.println("Event: " + event.getDateDebut() + " " + event.getDateFin() + " " + event.getNomDj() + " " + event.getNomClub() + " " + event.getNomVille());			}
		} catch (SQLException e) {
			e.printStackTrace();  
		}

		return ListeEvent;
	}

	@Override
	public List<Event> findByClub(String nom_Club) {
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement;
		List<Event> ListeEvent = new ArrayList<Event>();
		try {
			statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("Select * FROM Evenement WHERE nom_lieu = '" + nom_Club + "'") ;
			while (rs.next()) {
				Timestamp dateDebut = rs.getTimestamp("dateDebut");
				Timestamp dateFin = rs.getTimestamp("dateFin");
				String nomDj = rs.getString("nomDj");
				String nomClub = rs.getString("nomClub");
				String ville = rs.getString("ville");
				
				Event event = new Event(dateDebut, dateFin, nomDj, nomClub, ville);
				
				ListeEvent.add(event);
				//print the Event
                System.out.println("Event: " + event.getDateDebut() + " " + event.getDateFin() + " " + event.getNomDj() + " " + event.getNomClub() + " " + event.getNomVille());			}
		} catch (SQLException e) {
			e.printStackTrace();  
		}

		return ListeEvent;
	}
	
	@Override
	public List<Event> findByVille(String nomVille) {
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement;
		List<Event> ListeEvent = new ArrayList<Event>();
		try {
			statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("Select * FROM Evenement WHERE ville = '" + nomVille + "'");
			while (rs.next()) {
				Timestamp dateDebut = rs.getTimestamp("dateDebut");
				Timestamp dateFin = rs.getTimestamp("dateFin");
				String nomDj = rs.getString("nomDj");
				String nomClub = rs.getString("nomClub");
				String ville = rs.getString("ville");

				Event event = new Event(dateDebut, dateFin, nomDj, nomClub, ville);

				ListeEvent.add(event);
				// print the Event
				System.out.println("Event: " + event.getDateDebut() + " " + event.getDateFin() + " " + event.getNomDj()
						+ " " + event.getNomClub() + " " + event.getNomVille());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ListeEvent;
	}
	
	@Override
	public List<Event> findByDate(Timestamp dateDebut) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertEvent(Event event) {
	    Connection connexion = null;
	    PreparedStatement statement = null;
	    try {
	        connexion = DBManager.getInstance().getConnection();
	        String query = "call Ajout_Event(?, ?, ?, ?, ?)";
	        statement = connexion.prepareStatement(query);
	        statement.setString(1, event.getDateDebut().toString());
	        statement.setString(2, event.getDateFin().toString());
	        statement.setString(3, event.getNomDj());
	        statement.setString(4, event.getNomClub());
	        statement.setString(5, event.getNomVille());
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (connexion != null) {
	            try {
	                connexion.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
}
