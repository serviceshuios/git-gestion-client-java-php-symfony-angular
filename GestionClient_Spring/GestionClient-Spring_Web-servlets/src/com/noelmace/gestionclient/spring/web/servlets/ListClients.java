package com.noelmace.gestionclient.spring.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noelmace.gestionclient.spring.business.dto.ClientTO;

public class ListClients extends AbstractServiceServlet {

	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		out.println("<head><title>Liste des clients</title></head>");
		out.println("<h1>Liste des clients</h1>");
		List<ClientTO> clientList = null;
		try {
			clientList = clientService.list();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<table border=\"1\"><tr><th>Id</th><th>Nom</th><th>Prenom</th></tr>");
		for(ClientTO client : clientList){
			out.println("<tr><td>"+ client.getId() +"</td><td>"+client.getNom()+"</td><td>"
					+client.getPrenom()+"</td></tr>");
		}
		out.println("</table></body></html>");
	}

}
