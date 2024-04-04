package org.api;

public class Lieu {
	private String nomSite;
	private String villeSite;
	private String pays;
	private String continent;
	
	public Lieu(String nomSite, String villeSite, String pays, String continent) {
		super();
		this.nomSite = nomSite;
		this.villeSite = villeSite;
		this.pays = pays;
		this.continent = continent;
	}
	public String getNomSite() {
		return nomSite;
	}
	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}
	public String getVilleSite() {
		return villeSite;
	}
	public void setVilleSite(String villeSite) {
		this.villeSite = villeSite;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	@Override
	public String toString() {
		return "Lieu [nomSite=" + nomSite + ", villeSite=" + villeSite + ", pays=" + pays + ", continent=" + continent
				+ "]";
	}
	
}
