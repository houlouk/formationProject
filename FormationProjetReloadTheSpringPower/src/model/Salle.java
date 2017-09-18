package model;

import java.util.SortedSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Salle {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	@OneToMany(mappedBy="salle")
	@OrderBy
	@JsonBackReference
	private SortedSet<Creneau> creneaux;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	public SortedSet<Creneau> getCreneaux() {
		return creneaux;
	}

	public void setCreneaux(SortedSet<Creneau> creneaux) {
		this.creneaux = creneaux;
	}

}
