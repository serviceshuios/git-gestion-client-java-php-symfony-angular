package com.noelmace.gestionclient.spring.jsp.ctrl;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateClient extends AbstractServiceRequestHandler {

	/*
	 * @Override protected void doPost(HttpServletRequest req,
	 * HttpServletResponse resp) throws ServletException, IOException { String
	 * nom = req.getParameter("nom"); String prenom =
	 * req.getParameter("prenom"); if(!nom.equals("") && !nom.equals("")){ try {
	 * clientService.create(nom, prenom); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } } List<ClientTO>
	 * clientList = null; try { clientList = clientService.list(); } catch
	 * (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } req.setAttribute("clientList", clientList);
	 * ServletContext sc = getServletContext(); RequestDispatcher rd =
	 * sc.getRequestDispatcher("/list.jsp"); rd.forward(req, resp); }
	 */

	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		long id = -1;
		if (nom != null && !nom.equals("") && prenom != null && !prenom.equals("")) {
			try {
				id = clientService.create(nom, prenom);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		req.setAttribute("clientId", id);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
