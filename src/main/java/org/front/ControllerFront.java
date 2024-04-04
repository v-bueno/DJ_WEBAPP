package org.front;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControllerFront {
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

	@WebServlet("/lieu")
	public static class LieuServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        response.setContentType("text/html");
	        OutputStream outputStream = response.getOutputStream();
	        InputStream inputStream = getServletContext().getResourceAsStream("/lieu.html");
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
}
