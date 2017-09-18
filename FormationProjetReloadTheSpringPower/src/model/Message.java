package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String message;
	
	@ManyToMany
	@JoinTable(name="Message_prof")
	@JoinColumn(name="message_id")
	private List<Professeur> professeur;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message= message;
	}

	public List<Professeur> getProfesseurs() {
		return professeur;
	}

	public void setProfesseurs(List<Professeur> professeurs) {
		this.professeur = professeurs;
	}
	
	@Override
	public String toString(){
		return message;
	}
	

}
