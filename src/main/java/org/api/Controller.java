package org.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

@Path("/controller")
public class Controller {
	private  DJDao djDao=new DJDaoMockImpl();
	
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
	@Path("/djs")
	public void deleteDJ(@PathParam("nomDeScene") String nomDeScene) {
		djDao.deleteDJ(nomDeScene);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/djs")
	public void addDJ(@FormParam("nomDeScene") String nomDeScene, @FormParam("nom") String nom, @FormParam("prenom") String prenom, @FormParam("genre") String genre, @FormParam("lieuResidence") String lieuResidence,
            @FormParam("dateNaissance") Date dateNaissance) {
		djDao.addDJ(nomDeScene, nom, prenom, genre, lieuResidence, dateNaissance);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/djs")
	public void updateDJ(@PathParam("nomDeScene") String nomDeScene, @FormParam("nom") String nom, @FormParam("prenom") String prenom, @FormParam("genre") String genre, @FormParam("lieuResidence") String lieuResidence,
            @FormParam("dateNaissance") Date dateNaissance) {
		djDao.updateDJ(nomDeScene, nom, prenom, genre, lieuResidence, dateNaissance);
	}
}
