package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Creneau implements Comparable<Creneau> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "horaire_id", referencedColumnName = "id")
	private Horaire horaire;

	@ManyToOne
	@JoinColumn(name = "salle_id", referencedColumnName = "id")
	private Salle salle;

	@OneToOne(mappedBy = "creneau",cascade=CascadeType.ALL)
	private Cours cours;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Horaire getHoraire() {
		return horaire;
	}

	public void setHoraire(Horaire horaire) {
		this.horaire = horaire;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	@Override
	public int compareTo(Creneau o) {
		// TODO Auto-generated method stub
		return this.getHoraire().compareTo(o.getHoraire());
	}

}
