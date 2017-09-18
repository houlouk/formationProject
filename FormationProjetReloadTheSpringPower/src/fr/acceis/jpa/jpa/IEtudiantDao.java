package fr.acceis.jpa.jpa;

import model.Cursus;
import model.Etudiant;
import model.Horaire;
import model.Salle;
import nonPojoModel.EmploiDuTemps;

public interface IEtudiantDao extends Dao<Etudiant, String> {
	
	public void addCursus(String numeroEtudiant, Cursus cursus);

	public Cursus getCursus(String numeroEtudiant);
	
	public EmploiDuTemps<Etudiant> getEmploiDuTemps(String numeroEtudiant);


	void addCreneauEmploiDuTemps(String numeroEtudiant, Horaire horaire, Salle salle);


}
