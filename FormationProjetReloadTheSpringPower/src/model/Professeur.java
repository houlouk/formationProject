package model;

import java.util.List;
import java.util.SortedSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Professeur {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	private String prenom;
	
	@ManyToMany (mappedBy="professeurs")
	@OrderBy
	@JsonIgnore
	private SortedSet<Cours> cours;
	
	@ManyToMany
	@JoinTable(name="Message_prof")
	@JoinColumn(name="message_id")
	@JsonIgnore
	private List<Message> message;

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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public SortedSet<Cours> getCours() {
		return cours;
	}

	public void setCours(SortedSet<Cours> cours) {
		this.cours = cours;
	}
	
	@Override
	public String toString(){
		return this.nom+ " " +this.prenom;

	}

	public List<Message> getMessage() {
		return message;
	}

	public void setMessage(List<Message> message) {
		this.message = message;
	}

}
