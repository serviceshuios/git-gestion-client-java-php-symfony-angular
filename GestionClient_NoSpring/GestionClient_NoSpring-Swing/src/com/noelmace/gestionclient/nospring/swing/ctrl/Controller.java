package com.noelmace.gestionclient.nospring.swing.ctrl;

import java.util.HashMap;

import javax.swing.JOptionPane;

import com.noelmace.gestionclient.nospring.swing.ctrl.actions.CreateBdcAction;
import com.noelmace.gestionclient.nospring.swing.ctrl.actions.CreateClientAction;
import com.noelmace.gestionclient.nospring.swing.model.ModelAndGoal;
import com.noelmace.gestionclient.nospring.swing.views.BdcFormView;
import com.noelmace.gestionclient.nospring.swing.views.ClientFormView;
import com.noelmace.gestionclient.nospring.swing.views.MainFrame;

public class Controller {

	private static ClientFormView clientFormView = new ClientFormView();
	private static BdcFormView bdcFormView = new BdcFormView();
	
	private static HashMap<ActionId, Action> actionsMap = new HashMap<ActionId, Action>();
	
	static {
		actionsMap.put(ActionId.CREATE_CLIENT, new CreateClientAction());
		actionsMap.put(ActionId.CREATE_BDC, new CreateBdcAction());
	}

	private static MainFrame frame = MainFrame.getInstance();
	
	private static Controller instance;

	private Controller() {
		frame.addView(clientFormView, ViewId.CLIENT_FORM);
		frame.addView(bdcFormView, ViewId.BDC_FORM);
		frame.showView(ViewId.CLIENT_FORM);
	}

	public void launch(ModelAndGoal modelAndGoal) {
		try {
			Action goal = actionsMap.get(modelAndGoal.getGoal());
			if(goal == null){
				//frame.showView(DEFAULT_VIEW_ID);
			} else {
				goal.execute(modelAndGoal);
			}
		} catch (Exception e) {
			e.printStackTrace();
			String msg = (e.getMessage() != null) ? e.getMessage()
					: "une erreur est survenue";
			JOptionPane.showMessageDialog(frame, msg, "Inane error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void showView(ViewId id){
		frame.showView(id);
	}

	public static Controller getInstance() {
		if(instance == null){
			instance = new Controller();
		}
		return instance;
	}
	
	public void run(){
		frame.pack();
		frame.setVisible(true);
	}
}
