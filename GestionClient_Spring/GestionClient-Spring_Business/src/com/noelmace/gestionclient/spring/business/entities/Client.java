package com.noelmace.gestionclient.spring.business.entities;

import java.util.Set;

/**
 * @author Noël Macé (noelmace.com)
 *
 * Entité représentant un client
 */
public class Client {
	
	// ========== Attributs ==========

	/**
	 * identifiant (unique) de l'entité
	 */
	private Long id;
	
	/**
	 * nom du client (non unique)
	 */
	private String nom;
	/**
	 * prenom du client (non unique)
	 */
	private String prenom;
	
	/**
	 * Ensemble des bons de commande associés au client
	 */
	private Set<BonDeCommande> bonsDeCommande;

	// ========== Constructeurs ==========
	
	/**
	 * Constructeur par défaut
	 */
	public Client() {
	}

	/**
	 * Constructeur pour le service ClientService
	 * @param nom
	 * @param prenom
	 * @see com.noelmace.gestioncompte.spring.business.services.ClientService
	 */
	public Client(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;	
	}
	
	/**
	 * l'id de cet éléménent sera null tant qu'il demeure transient
	 * @param nom
	 * @param prenom
	 * @param bonsDeCommande
	 */
	public Client(String nom, String prenom, Set<BonDeCommande> bonsDeCommande) {
		this.nom = nom;
		this.prenom = prenom;
		this.bonsDeCommande = bonsDeCommande;
	}

	// ========== Accesseurs et mutateurs ==========

	


	/**
	 * @return id (unique) de l'entité
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom du client
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return nom du client
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom du client
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return ensemble des bons de commande associés au client
	 * ne réalise aucune vérification de l'association bi-directionnelle
	 */
	public Set<BonDeCommande> getBonsDeCommande() {
		return bonsDeCommande;
	}

	/**
	 * @param bonsDeCommande ensemble des bons de commande à associer au client
	 * ne réalise aucune vérification de l'association bi-directionnelle
	 */
	public void setBonsDeCommande(Set<BonDeCommande> bonsDeCommande) {
		this.bonsDeCommande = bonsDeCommande;
	}
	
	/**
	 * @param bdc à associer au client
	 * ne réalise aucune vérification de l'association bi-directionnelle
	 */
	public void addBonDeCommande(BonDeCommande bdc){
		this.bonsDeCommande.add(bdc);
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
	
}
