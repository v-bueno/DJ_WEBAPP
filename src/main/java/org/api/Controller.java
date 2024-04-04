package org.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/controller")
public class Controller{
	
private EventDAO eventDAO = new EventDAOImpl();
private LieuDAO lieuDAO = new LieuDAOImpl();
private DJDao djDao = new DJDaoMockImpl();


//create post and store the form received in an array list
@POST
@Path("/event")
public Response postEvent(@FormParam("dj") String dj, @FormParam("club") String club, @FormParam("ville") String ville, @FormParam("jour_debut") String jour_debut, @FormParam("heure_debut") String heure_debut, @FormParam("jour_fin") String jour_fin, @FormParam("heure_fin") String heure_fin) {
	Event event = new Event(Timestamp.valueOf(jour_debut + " " + heure_debut + ":00"), Timestamp.valueOf(jour_fin + " " + heure_fin + ":00"),dj, club, ville);
	
	try {
		eventDAO.insertEvent(event);
		// Créer une réponse HTTP avec le code de statut 201 (Created) et un message indiquant que l'insertion s'est bien déroulée
		return Response.seeOther(new URI("/DJ-Agenda/ajoutEvent/succes.html")).build();
	}
	catch(Exception e) {
		e.printStackTrace();
		try {
			return Response.seeOther(new URI("/DJ-Agenda/ajoutEvent/echec.html")).build();
		}
		catch(URISyntaxException ep) {
			ep.printStackTrace();
		}
		// Créer une réponse HTTP avec le code de statut 500 (Internal Server Error) et un message indiquant que l'insertion a échoué
	}
	return null;

    // Create an HTTP response with the status code 200 (OK) and the JSON in the response body
}

	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/hello")
	public String hello() {
		return "Hello World!";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/event")
	public String getEvents(@QueryParam("dj") String dj) {
		List<Event> events = new ArrayList<Event>();
		if ("".equals(dj)) {
			events = eventDAO.findAll();
		} else {
			events = eventDAO.findByDJ(dj);
		}
		//print Events list
		System.out.println("Liste des événements :" + events.toString());
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(events);
		return json;	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/lieu")
	public String getLieux() {
		List<Lieu> lieux = new ArrayList<Lieu>();
		lieux = lieuDAO.findAll();
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(lieux);
		return json;	}

    @GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/djs")
    public String getDJs() {
		List<DJ> djs=djDao.getDJs();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json=gson.toJson(djs);
		return json;
    }
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/djs/{nom_scene}")
	public void deleteDJ(@PathParam("nom_scene") String nomDeScene) {
	    djDao.deleteDJ(nomDeScene);
	}
	
	@POST
	@Path("/djs")
	public Response AjouterDJ(@FormParam("nomDeScene") String nomDeScene, @FormParam("nom") String nom, @FormParam("prenom") String prenom, @FormParam("style_musical") String style_musical, @FormParam("lieuDeResidence") String lieuResidence,
            @FormParam("dateDeNaissance") Date dateNaissance) {
		System.out.println("Nom de scene: " + nomDeScene);
		djDao.addDJ(nomDeScene, nom, prenom, style_musical, lieuResidence, dateNaissance);
		return Response.status(201).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/djs")
	public void updateDJ(@FormParam("nom_sceneCh") String nomDeScene, @FormParam("nomCh") String nom, @FormParam("prenomCh") String prenom, @FormParam("style_musicalCh") String genre, @FormParam("lieu_residenceCh") String lieuResidence,
            @FormParam("date_naissanceCh") Date dateNaissance) {
		System.out.println("Nom de scene: " + nomDeScene);
		System.out.println("Nom: " + nom);
		System.out.println("Prenom: " + prenom);
		System.out.println("Genre: " + genre);
		System.out.println("Lieu de residence: " + lieuResidence);
		System.out.println("Date de naissance: " + dateNaissance);
		djDao.updateDJ(nomDeScene, nom, prenom, genre, lieuResidence, dateNaissance);
	}
}
