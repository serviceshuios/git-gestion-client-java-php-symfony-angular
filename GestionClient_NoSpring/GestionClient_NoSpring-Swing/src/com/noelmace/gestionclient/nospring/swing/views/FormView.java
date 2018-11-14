package com.noelmace.gestionclient.nospring.swing.views;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.noelmace.gestionclient.nospring.swing.model.ModelAndGoal;

@SuppressWarnings("serial")
public abstract class FormView extends View implements ActionListener {
	
	/**
	 * Tableau des textes par défaut pour les champs textuels
	 * doit être initialisé lors de l'héritage
	 */
	protected String[] textfieldsHints;
	
	/**
	 * Liste des champs textuels
	 * 
	 */
	protected List<JTextField> textFields = new ArrayList<JTextField>();
	
	/**
	 * Bouton pour lancer l'action de création d'un nouveau client à partir du texte des champs
	 */
	protected JButton okButton = new JButton("save");
	
	/**
	 * Taille maximum d'un champs textuel
	 */
	protected static final Dimension FIELD_MAX_DIM = new Dimension(200, 30);
	/**
	 * Taille "favorite" pour le panel
	 */
	protected static final Dimension PREFERED_SIZE = new Dimension(300, 300);
	/**
	 * Marge intérieure du panel
	 */
	protected static final int PANEL_PADDING = 20;
	/**
	 * Marge extérieur en dessous des champs textuels
	 */
	protected static final int FIELDS_VERTICAL_MARGING = 10;
	
	
	protected FormView(ModelAndGoal modelAndGoal, String[] textfieldsHints){
		super(modelAndGoal);
		this.textfieldsHints = textfieldsHints;
		init();
	}

	/**
	 * Initialisation du panel
	 * Création, configuration et ajout au panel des composants
	 */
	private void init(){
		// taille "favorite" pour le panel
		this.setPreferredSize(PREFERED_SIZE);
		
		this.setBorder(new EmptyBorder(PANEL_PADDING, PANEL_PADDING, PANEL_PADDING, PANEL_PADDING));

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		// parcours du tableau des textes par défaut des champs textuels
		for(String hint : textfieldsHints){
			 // création d'un champ textuel, en initialisatant son texte à partir du tableau TEXTFIELDS_HINT, 
			 // et ajout de celui-ci à la liste des textFields
			 textFields.add(new JTextField(hint));
		}

		// parcours de la liste des champs textuels via un iterateur
		for (Iterator<JTextField> iterator = textFields.iterator(); iterator.hasNext();) {
			// stockage du champs textuel à la position courante dans une variable locale
			JTextField field = iterator.next();
			// définition de la taille maximum du champs textuel
			field.setMaximumSize(FIELD_MAX_DIM);
			// ajout du champs textuel au panel
			this.add(field);
			// ajout d'un élément vide pour mettre en place une marge entre le champs textuel et le composant suivant
			this.add(Box.createVerticalStrut(FIELDS_VERTICAL_MARGING));
		}
		
		// aligner le bouton au centre (à gauche par défaut)
		okButton.setAlignmentX(CENTER_ALIGNMENT);
		// ajout du listener pour les actions sur le bouton
		okButton.addActionListener(this);
		
		// ajout du bouton au panel
		this.add(okButton);
	}
}
