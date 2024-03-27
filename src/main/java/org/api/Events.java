package org.api;

import java.util.Date;

public class Events {
	private Date dateEvent;
	private String nomDj;
	private String nomClub;
	private String ville;
	
	public Events(Date dateEvent, String nomDj, String nomClub, String ville) {
		super();
		this.dateEvent = dateEvent;
		this.nomDj = nomDj;
		this.nomClub = nomClub;
		this.ville = ville;
	}
	
	public Date getDateEvent() {
		return dateEvent;
	}
	
	public void setDateEvent(Date dateEvent) {
		this.dateEvent = dateEvent;
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
	
	@Override
	public String toString() {
		return "Events [dateEvent=" + dateEvent + ", nomDj=" + nomDj + ", nomClub=" + nomClub + ", nomVille=" + ville
				+ "]";
	}
	
	//add a poem making method about events
	public String poem() {
		return "In pulsing beats, souls unite, at the DJ's command, the night takes flight.";
	}
	
	
	
}
