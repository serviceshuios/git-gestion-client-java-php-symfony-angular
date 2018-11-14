package com.noelmace.gestionclient.nospring.business.entities;

import javax.persistence.*;

/**
 * @author Noël Macé (noelmace.com)
 *
 * Les annotations ne sont ici présentées que par souci d'exaustivité dans la présentation d'Hibernate.
 * Dans notre approche par composant, la configuration par XML est bien entendu à privilégier.
 */
@Entity
@Table(name = "BONDECOMMANDE")
public class BonDeCommande {
	
	/**
	 * identifiant (unique) de l'entité
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * référence (non-unique) du bon de commande
	 */
	@Column(name = "REF")
	private String ref;
	
	/**
	 * prix associé au bon de commande
	 */
	@Column(name = "PRICE")
	private Double price;
	
	/**
	 * client à l'origine du bon de commande
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)  
    @JoinColumn(name = "client_id")  
	private Client client;
	
	/**
	 * Constructeur par défaut, uniquement présent pour Hibernate et autre framework de DAO ne nécessitant pas d'accès publique
	 */
	@SuppressWarnings("unused")
	public BonDeCommande() {
	}

	/**
	 * Constructeur à utiliser pour initialiser un bon de commande
	 * l'id de cet éléménent sera null tant qu'il demeure transient
	 * @param ref
	 * @param price
	 * @param client
	 */
	public BonDeCommande(String ref, Double price, Client client) {
		this.ref = ref;
		this.price = price;
		this.client = client;
	}

	/**
	 * @return l'identifiant du bon de commande
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Update : public pour Jdbc
	 * @param id mutateur privé, uniquement présent pour Hibernate et autre framework de DAO ne nécessitant pas d'accès publique
	 */
	@SuppressWarnings("unused")
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return référence du bon de commande
	 */
	public String getRef() {
		return ref;
	}

	/**
	 * @param ref
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}

	/**
	 * @return prix associé au bon de commande
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return Client à l'origine du bon de commande
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Mutateur du Client associé au bon de commande.
	 * Ne réalise aucune vérification de l'association bi-directionnelle.
	 * @param client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "BonDeCommande [id=" + id + ", ref=" + ref + ", price=" + price
				+ "]";
	}

}
