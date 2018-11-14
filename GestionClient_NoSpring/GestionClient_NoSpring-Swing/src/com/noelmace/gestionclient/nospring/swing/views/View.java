package com.noelmace.gestionclient.nospring.swing.views;

import javax.swing.JPanel;

import com.noelmace.gestionclient.nospring.swing.model.ModelAndGoal;

public class View extends JPanel {
	
	private ModelAndGoal baseMaG;

	public View(ModelAndGoal modelAndGoal) {
		this.baseMaG = modelAndGoal;
	}

	public View() {
		this.baseMaG = new ModelAndGoal();
	}
	
}
