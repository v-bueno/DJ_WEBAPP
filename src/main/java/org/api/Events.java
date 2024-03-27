package org.api;

import java.util.Date;

public class Events {
	private Date dateEvent;
	private String nomDj;
	private String nomLieu;
	
	public Events(Date dateEvent, String nomDj, String nomLieu) {
		this.dateEvent = dateEvent;
		this.nomDj = nomDj;
		this.nomLieu = nomLieu;
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
	
	public String getNomLieu() {
		return nomLieu;
	}
	
	public void setNomLieu(String nomLieu) {
		this.nomLieu = nomLieu;
	}
	
	@Override
	public String toString() {
		return "Events [dateEvent=" + dateEvent + ", nomDj=" + nomDj + ", nomLieu=" + nomLieu + "]";
	}
	
	//add a poem making method about events
	public String poem() {
		return "In pulsing beats, souls unite, at the DJ's command, the night takes flight.";
	}
	
	
	
}
