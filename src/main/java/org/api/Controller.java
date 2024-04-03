package org.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

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

@WebServlet("/event")
public static class EventServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        OutputStream outputStream = response.getOutputStream();
        InputStream inputStream = getServletContext().getResourceAsStream("/event.html");
        if (inputStream == null || inputStream.available() == 0) {
            // Le fichier est vide ou n'existe pas
            throw new IOException("Le fichier event.html est vide ou n'existe pas.");
        }
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outputStream.close();
    }
}	

@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("/event")

public String getPlacesDjs() {
	// Return a JSON object with a list of places and DJs
	String json = "{\"places\": [\"Place 1\", \"Place 2\"], \"djs\": [\"DJ 1\", \"DJ 2\"]}";

    return json;
}
//create post and store the form received in an array list
@POST
@Produces(MediaType.APPLICATION_JSON)
@Path("/event")
public Response postEvent(@FormParam("dj") String dj, @FormParam("place") String place, @FormParam("ville") String ville, @FormParam("jour_debut") String jour_debut, @FormParam("heure_debut") String heure_debut, @FormParam("jour_fin") String jour_fin, @FormParam("heure_fin") String heure_fin) {
    // Create array lists to store the places and DJs
    JsonArray places = new JsonArray();
    JsonArray djs = new JsonArray();
    JsonArray villes = new JsonArray();
    JsonArray jours_debut = new JsonArray();
    JsonArray jours_fin = new JsonArray();
    JsonArray heures_debut = new JsonArray();
    JsonArray heures_fin = new JsonArray();
    // Store the form data in an array list
    places.add(place);
    djs.add(dj);
    villes.add(ville);
    jours_debut.add(jour_debut);
    jours_fin.add(jour_fin);
    heures_debut.add(heure_debut);
    heures_fin.add(heure_fin);
    // Return a JSON object with the list of places and DJs
    String json = "{\"dateDebut\": " + jours_debut.toString() + heures_debut.toString() + ", \"dateFin\": " + jours_fin.toString() + heures_fin.toString() + " , \"nomDj\": " + djs.toString() + " , \"nomClub\": " + places.toString() + " , \"ville\": " + villes.toString() + "}";
    // Create an HTTP response with the status code 200 (OK) and the JSON in the response body
    return Response.status(Status.OK).entity(json).build();
}

	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/hello")
	public String hello() {
		return "Hello World!";
	}
	
	private EventDAO eventDAO = new EventDAOImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/events")
	public String getEvents(@QueryParam("dj") String dj) {
		List<Events> events = new ArrayList<Events>();
		events = eventDAO.findAll();
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(events);
		return json;	}
}
