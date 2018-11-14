package com.noelmace.gestionclient.nospring.swing.views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.noelmace.gestionclient.nospring.business.services.ClientService;
import com.noelmace.gestionclient.nospring.business.services.exception.ServiceLayerException;
import com.noelmace.gestionclient.nospring.ioc.ServiceFactory;
import com.noelmace.gestionclient.nospring.swing.ctrl.ActionId;
import com.noelmace.gestionclient.nospring.swing.ctrl.Controller;
import com.noelmace.gestionclient.nospring.swing.model.ModelAndGoal;

/**
 * Panel de contenu pour la fenêtre principale
 * 
 * @author Noël Macé (noelmace.com)
 *
 */
@SuppressWarnings("serial")
public class ClientFormView extends FormView {

	public ClientFormView(ModelAndGoal modelAndGoal) {
		super(modelAndGoal, new String[] { "nom", "prenom", "age" });
	}
	
	public ClientFormView() {
		this(new ModelAndGoal());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		ModelAndGoal modelAndGoal = new ModelAndGoal(ActionId.CREATE_CLIENT);

		modelAndGoal.addData("lastName", textFields.get(0).getText());
		modelAndGoal.addData("firstName", textFields.get(1).getText());
		modelAndGoal.addData("age", textFields.get(2).getText());

		Controller.getInstance().launch(modelAndGoal);

	}

}
