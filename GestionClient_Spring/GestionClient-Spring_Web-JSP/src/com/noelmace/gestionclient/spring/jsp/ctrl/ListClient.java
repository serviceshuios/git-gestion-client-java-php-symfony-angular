package com.noelmace.gestionclient.spring.jsp.ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noelmace.gestionclient.spring.business.dto.ClientTO;

public class ListClient extends AbstractServiceRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<ClientTO> clientList = null;
		try {
			clientList = clientService.list();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("clientList", clientList);
		req.getRequestDispatcher("/list.jsp").forward(req, resp);
	}

}
