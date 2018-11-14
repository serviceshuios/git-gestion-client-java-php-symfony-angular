package com.noelmace.gestionclient.spring.mvc.ctrl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public class ListClientsCtrl extends AbstractCtrl {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("clientsList");
		modelAndView.addObject("clientList", clientService.list());
		return modelAndView;
	}

}
