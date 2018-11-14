package com.noelmace.gestionclient.spring.mvc.ctrl;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class CreateClientCtrl extends AbstractCtrl {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			clientService.create(request.getParameter("nom"), request.getParameter("prenom"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView(new RedirectView("index.html"));
		return modelAndView;
	}

}
