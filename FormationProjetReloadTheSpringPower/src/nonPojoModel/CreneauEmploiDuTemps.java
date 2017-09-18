package nonPojoModel;

import java.util.ArrayList;
import java.util.List;

import model.Horaire;
import model.Matiere;
import model.Professeur;
import model.Salle;

public class CreneauEmploiDuTemps {

	private Matiere matiere;
	private Salle salle;
	private Horaire horaire;
	private List<Professeur> professeurs = new ArrayList<>();

	public void addProfesseur(Professeur professeur) {
		this.professeurs.add(professeur);
		// TODO Auto-generated method stub

	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Horaire getHoraire() {
		return horaire;
	}

	public void setHoraire(Horaire horaire) {
		this.horaire = horaire;
	}

	public List<Professeur> getProfesseurs() {
		return professeurs;
	}

}
