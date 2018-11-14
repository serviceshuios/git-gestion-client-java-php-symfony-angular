package com.noelmace.gestionclient.nospring.swing.views;

import java.awt.CardLayout;
import java.awt.MenuBar;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.noelmace.gestionclient.nospring.swing.ctrl.ViewId;

/**
 * Fenêtre principale de l'application
 * 
 * @author Noël Macé (noelmace.com)
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	/**
	 * Instance (singleton) de la classe
	 */
	private static MainFrame instance;
	
	/**
	 * Titre de la fenêtre
	 */
	private static final String TITLE = "Gestion Client";
	
	private CardLayout contentLayout = new CardLayout();
	
	private JPanel content = new JPanel(contentLayout);
	
	private ViewId currentView;
	
	private MainMenuBar menuBar = new MainMenuBar();
	
	/**
	 * Initialisation de la fenêtre
	 */
	private MainFrame() {
		// titre
		this.setTitle(TITLE);
		// fermer toute l'application quand on ferme la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// placer la fenêtre au centre de l'écran
	    this.setLocationRelativeTo(null);
	    // la fenêtre n'est pas redimensionnable
	    this.setResizable(false);
	    // définir container pour le contenu de la fenêtre
		this.setContentPane(content);
		// définir la barre des menus
		this.setJMenuBar(menuBar);
	}

	/**
	 * @return instance unique (singleton) de la classe
	 */
	public static MainFrame getInstance() {
		if(instance == null){
			instance = new MainFrame();
		}
		return instance;
	}
	
	public void addView(JPanel panel, ViewId id){
		this.content.add(panel, id.toString());
	}
	
	public void showView(ViewId id){
		this.contentLayout.show(content, id.toString());
		this.currentView = id;
	}
}
