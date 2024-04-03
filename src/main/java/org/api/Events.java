package org.api;

import java.sql.Timestamp;
import java.util.Date;

public class Events {
	private Timestamp dateDebut;
	private Timestamp dateFin;
	private String nomDj;
	private String nomClub;
	private String ville;
	
	public Events(Timestamp dateDebut, Timestamp dateFin, String nomDj, String nomClub, String ville) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nomDj = nomDj;
		this.nomClub = nomClub;
		this.ville = ville;
	}
	
	public Date getDateDebut() {
		return dateDebut;
	}
	
	public void setDateDebut(Timestamp dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	public Date getDateFin() {
		return dateFin;
	}
	
	public void setDateFin(Timestamp dateFin) {
		this.dateFin = dateFin;
	}
	
	public String getNomDj() {
		return nomDj;
	}
	
	public void setNomDj(String nomDj) {
		this.nomDj = nomDj;
	}
	
	public String getNomClub() {
		return nomClub;
	}
	
	public void setNomClub(String nomClub) {
		this.nomClub = nomClub;
	}
	
	public String getNomVille() {
		return ville;
	}
	
	public void setNomVille(String ville) {
		this.ville = ville;
	}
	
	public String toString() {
		return "Event [dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", nomDj=" + nomDj + ", nomClub=" + nomClub
				+ ", ville=" + ville + "]";
	}
	
	//add a poem making method about events
	public String poem() {
		return "In pulsing beats, souls unite, at the DJ's command, the night takes flight.";
	}
	
	
	
}
