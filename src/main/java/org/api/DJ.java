package org.api;

import java.sql.Date;

public class DJ{
	private String nomDeScene;
	private String nom;
	private String prenom;
	private String style_musical;
	private String lieuResidence;
	private Date dateNaissance;
	
	public DJ(String nomDeScene, String nom, String prenom, String style_musical, String lieuResidence, Date dateNaissance) {
		this.nomDeScene = nomDeScene;
		this.nom = nom;
		this.prenom = prenom;
		this.style_musical = style_musical;
		this.lieuResidence = lieuResidence;
		this.dateNaissance = dateNaissance;
	}
	
	public String getNomDeScene() {
		return nomDeScene;
	}
	
	public void setNomDeScene(String nomDeScene) {
		this.nomDeScene = nomDeScene;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getStyleMusical() {
		return style_musical;
	}
	
	public void setStyleMusical(String style_musical) {
		this.style_musical = style_musical;
	}
	
	public String getLieuResidence() {
		return lieuResidence;
	}
	
	public void setLieuResidence(String lieuResidence) {
		this.lieuResidence = lieuResidence;
	}
	
	public Date getDateNaissance() {
		return dateNaissance;
	}
	
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	@Override
	public String toString() {
		return "DJ [nomDeScene=" + nomDeScene + ", nom=" + nom + ", prenom=" + prenom + ", style_musical="
				+ style_musical + ", lieu_residence=" + lieuResidence + ", date_naissance=" + dateNaissance + "]";
	}
	
	public String poem() {
		return "roses are red, violets are blue, I am a DJ, and so are you";
	}
	
	
	
	
	
}