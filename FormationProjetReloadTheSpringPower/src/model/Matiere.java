package model;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class Matiere {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	private String nom;
	
	@OneToMany(mappedBy="matiere",cascade={CascadeType.ALL})
	@OrderBy
	private SortedSet<Cours> cours;
	
	@ManyToMany(mappedBy="matieres")
    @JsonBackReference
	private List<Cursus> cursus = new ArrayList<>();
	
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
	
	public SortedSet<Cours> getCours() {
		return cours;
	}
	public void setCours(SortedSet<Cours> cours) {
		this.cours = cours;
	}
	public List<Cursus> getCursus() {
		return cursus;
	}
	public void setCursus(List<Cursus> cursus) {
		this.cursus = cursus;
	}


}
