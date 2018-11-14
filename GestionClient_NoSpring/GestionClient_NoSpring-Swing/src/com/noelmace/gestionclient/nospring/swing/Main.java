package com.noelmace.gestionclient.nospring.swing;

import com.noelmace.gestionclient.nospring.swing.ctrl.Controller;


/**
 * Classe d'entrée (minimaliste) du programme
 * 
 * @author Noël Macé (noelmace.com)
 *
 */
public class Main {

	/**
	 * Point d'entré
	 * se contente de rendre visible la fenêtre principale
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Controller.getInstance().run();
	}

}
