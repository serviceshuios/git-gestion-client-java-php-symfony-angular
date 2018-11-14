package com.noelmace.gestionclient.spring.jsf.validation;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.noelmace.gestionclient.spring.business.dto.ClientTO;
import com.noelmace.gestionclient.spring.business.services.ClientService;

@Component
@Scope("request")
public class ClientIdValidator implements Validator {
	
	@Autowired
	private ClientService clientService;

	@Override
	public void validate(FacesContext fc, UIComponent comp, Object value)
			throws ValidatorException {
			Long id = null;
			try {
				id = Long.parseLong(value.toString());
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new ValidatorException(new FacesMessage("n'est pas un nombre"));
			}
			ClientTO client = null;
			try {
				client = clientService.get(id);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ValidatorException(new FacesMessage("impossible de récupérer le client"));
			}
			if(client == null){
				throw new ValidatorException(new FacesMessage("aucun client ne correspond à cette id"));
			}
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
}
