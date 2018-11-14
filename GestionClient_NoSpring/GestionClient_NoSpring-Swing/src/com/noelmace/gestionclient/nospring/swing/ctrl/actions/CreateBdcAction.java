package com.noelmace.gestionclient.nospring.swing.ctrl.actions;

import javax.swing.JOptionPane;

import com.noelmace.gestionclient.nospring.ioc.ServiceFactory;
import com.noelmace.gestionclient.nospring.swing.ctrl.Action;
import com.noelmace.gestionclient.nospring.swing.ctrl.ViewId;
import com.noelmace.gestionclient.nospring.swing.model.ModelAndGoal;
import com.noelmace.gestionclient.nospring.swing.views.MainFrame;

public class CreateBdcAction implements Action {

	@Override
	public ViewId execute(ModelAndGoal modelAndGoal) throws Exception {
		long id = -1;
		try {
			id = ServiceFactory.getOrderingService().makeOrder(
					modelAndGoal.getLong("clientId"),
					modelAndGoal.getString("ref"),
					modelAndGoal.getDouble("price"));
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "Le bon de commande "
					+ id + " a  bien été enregistré !");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(MainFrame.getInstance(),
					"Le nombre entré est incorrect !", "Inane warning",
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

}
