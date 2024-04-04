package org.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/controller")
public class Controller{
	
private EventDAO eventDAO = new EventDAOImpl();
private LieuDAO lieuDAO = new LieuDAOImpl();
	


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
	public String getEvents() {
		List<Event> events = new ArrayList<Event>();
		events = eventDAO.findAll();
		
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
}
