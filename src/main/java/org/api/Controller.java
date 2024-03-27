package org.api;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

@Path("/controller")
public class Controller {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/hello")
	public String hello() {
		return "Hello World!";
	}
	
	private EventDAO eventDAO = new EventDAOMockImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/events")
	public String getEvents(@QueryParam("dj") String dj) {
		List<Events> events = new ArrayList<Events>();
		events = eventDAO.findByAll();
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(events);
		return json;	}
}
