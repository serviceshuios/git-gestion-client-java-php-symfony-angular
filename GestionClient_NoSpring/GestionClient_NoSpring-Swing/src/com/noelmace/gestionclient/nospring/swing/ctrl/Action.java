package com.noelmace.gestionclient.nospring.swing.ctrl;

import java.util.HashMap;

import com.noelmace.gestionclient.nospring.swing.model.ModelAndGoal;

public interface Action {
	
	public abstract ViewId execute(ModelAndGoal modelAndGoal) throws Exception;
}
