package model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Horaire implements Comparable<Horaire> {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date debut;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fin;
	
	@OneToMany(mappedBy="horaire")
	private List<Creneau> creneaux;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public List<Creneau> getCreneaux() {
		return creneaux;
	}

	public void setCreneaux(List<Creneau> creneaux) {
		this.creneaux = creneaux;
	}
	
	@Override
	public String toString(){
		return this.debut + " " +this.fin;

	}

	@Override
	public int compareTo(Horaire hor) {
		// TODO Auto-generated method stub
		return this.debut.compareTo(hor.debut);
	}
	


}
