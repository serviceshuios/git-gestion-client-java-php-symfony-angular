package com.noelmace.gestionclient.nospring.swing.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.noelmace.gestionclient.nospring.swing.ctrl.Controller;
import com.noelmace.gestionclient.nospring.swing.ctrl.ViewId;
import com.noelmace.gestionclient.nospring.swing.model.ModelAndGoal;

@SuppressWarnings("serial")
public class MainMenuBar extends JMenuBar {

	private JMenu menuCreate = new JMenu("Créer");
	
	private JMenuItem createClientMenuItem = new JMenuItem("client");
	private JMenuItem createBdcMenuItem = new JMenuItem("bon de commande");
	
	private ActionListener menuItemListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem source = (JMenuItem) e.getSource();
			if(source == createClientMenuItem){
				Controller.getInstance().showView(ViewId.CLIENT_FORM);
			} else if (source == createBdcMenuItem){
				Controller.getInstance().showView(ViewId.BDC_FORM);
			}
		}
		
	};
	
	public MainMenuBar(){
		createClientMenuItem.addActionListener(menuItemListener);
		createBdcMenuItem.addActionListener(menuItemListener);
		
		menuCreate.add(createClientMenuItem);
		menuCreate.add(createBdcMenuItem);
		
		this.add(menuCreate);
	}
	
	
}
