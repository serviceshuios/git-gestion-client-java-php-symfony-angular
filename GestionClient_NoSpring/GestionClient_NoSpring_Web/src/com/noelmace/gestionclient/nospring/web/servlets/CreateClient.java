package com.noelmace.gestionclient.nospring.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noelmace.gestionclient.nospring.business.services.ClientService;
import com.noelmace.gestionclient.nospring.business.services.exception.ServiceLayerException;
import com.noelmace.gestionclient.nospring.ioc.ServiceFactory;

/**
 * Servlet implementation class CreateClient
 */
@WebServlet(description = "Permet de créer un client", urlPatterns = { "/CreateClient" })
public class CreateClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ClientService clientServ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateClient() {
        super();
        try {
			this.clientServ = ServiceFactory.getClientService();
		} catch (ServiceLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int age = Integer.parseInt(request.getParameter("age"));
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");

			clientServ.create(age, nom, prenom);
			response.sendRedirect("../success.html");
		} catch (NumberFormatException | ServiceLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("../error.html");
		}
	}

}
