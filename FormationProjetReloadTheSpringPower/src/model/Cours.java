package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;



@Entity
public class Cours implements Comparable<Cours> {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name="matiere_id",referencedColumnName="id")
	@ManyToOne
	private Matiere matiere;
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name="creneau_id",referencedColumnName="id")
	private Creneau creneau;

	@ManyToMany
	private List<Professeur> professeurs;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public List<Professeur> getProfesseurs() {
		return professeurs;
	}

	public void setProfesseurs(List<Professeur> professeurs) {
		this.professeurs = professeurs;
	}

	public Creneau getCreneau() {
		return creneau;
	}

	public void setCreneau(Creneau creneau) {
		this.creneau = creneau;
	}

	@Override
	public int compareTo(Cours o) {
		// TODO Auto-generated method stub
		return this.getCreneau().compareTo(o.getCreneau());
	}

}
