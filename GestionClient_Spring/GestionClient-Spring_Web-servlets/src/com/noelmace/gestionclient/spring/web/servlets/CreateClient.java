package com.noelmace.gestionclient.spring.web.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateClient
 */
//@WebServlet("/CreateClient")
public class CreateClient extends AbstractServiceServlet {
 
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			clientService.create(request.getParameter("nom"), request.getParameter("prenom"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("./index.html");
	}

}
