package com.noelmace.gestionclient.nospring.business.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * @author Noël Macé (noelmace.com)
 *
 * Les annotations ne sont ici présentées que par souci d'exaustivité dans la présentation d'Hibernate.
 * Dans notre approche par composant, la configuration par XML est bien entendu à privilégier
 */
@Entity
@Table(name = "CLIENT", uniqueConstraints=@UniqueConstraint(columnNames = {"nom", "prenom"}))
public class Client {

	// Génération de l'id par méthode native. La version avec génération via Hibernate est présentée en commentaire.
	@Id
	/*@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")*/
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "AGE")
	private Integer age;
	
	@Column(name = "NOM")
	private String nom;
	
	@Column(name = "PRENOM")
	private String prenom;
	
	
	// le fetch est placé ici à eager (lazy=false) du fait de l'usage de la méthode toString dans les tests unitaires
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "client", cascade=CascadeType.ALL)
	private Set<BonDeCommande> bonsDeCommande = new HashSet<BonDeCommande>();
	
	
	@SuppressWarnings("unused")
	public Client() {
	}

	public Client(Integer age, String nom, String prenom) {
		this.age = age;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Client(Long id, Integer age, String nom, String prenom) {
		this.id = id;
		this.age = age;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Set<BonDeCommande> getBonsDeCommande() {
		return bonsDeCommande;
	}

	public void setBonsDeCommande(Set<BonDeCommande> bonsDeCommande) {
		this.bonsDeCommande = bonsDeCommande;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", age=" + age + ", nom=" + nom
				+ ", prenom=" + prenom + ", bonsDeCommande=" + bonsDeCommande
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Client))
			return false;
		Client other = (Client) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}
}
