package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Etudiant {
	@Id
	private String numeroEtudiant;
	
	private String nom;
	private String prenom;
	
	@ManyToOne (cascade=CascadeType.PERSIST)
	@JoinColumn(name="cursus_id",referencedColumnName="id")
	private Cursus cursus;
	
	public String getNumeroEtudiant() {
		return numeroEtudiant;
	}
	public void setNumeroEtudiant(String id) {
		this.numeroEtudiant = id;
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
	public Cursus getCursus() {
		return cursus;
	}
	public void setCursus(Cursus cursus) {
		this.cursus = cursus;
	}
	
	@Override
	public String toString(){
		return nom+ " " + prenom;
	}
	
	
		
}
