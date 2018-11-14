package com.noelmace.gestionclient.nospring.swing.views;

import java.awt.event.ActionEvent;

import com.noelmace.gestionclient.nospring.swing.ctrl.ActionId;
import com.noelmace.gestionclient.nospring.swing.ctrl.Controller;
import com.noelmace.gestionclient.nospring.swing.model.ModelAndGoal;

public class BdcFormView extends FormView {

	public BdcFormView(ModelAndGoal modelAndGoal) {
		super(modelAndGoal, new String[] { "clientId", "ref", "price" });
	}
	
	public BdcFormView() {
		this(new ModelAndGoal());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		ModelAndGoal modelAndGoal = new ModelAndGoal(ActionId.CREATE_BDC);

		modelAndGoal.addData("clientId", textFields.get(0).getText());
		modelAndGoal.addData("ref", textFields.get(1).getText());
		modelAndGoal.addData("price", textFields.get(2).getText());

		Controller.getInstance().launch(modelAndGoal);

	}
}
