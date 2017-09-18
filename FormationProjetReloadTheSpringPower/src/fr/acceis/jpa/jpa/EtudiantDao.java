package fr.acceis.jpa.jpa;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import model.Cours;
import model.Cursus;
import model.Etudiant;
import model.Horaire;
import model.Matiere;
import model.Professeur;
import model.Salle;
import nonPojoModel.CreneauEmploiDuTemps;
import nonPojoModel.EmploiDuTemps;

public class EtudiantDao extends HibernateDaoSessionFactory<Etudiant, String> implements IEtudiantDao {

	public EtudiantDao() {
		this.classT = Etudiant.class;
	}

	@Override
	public void addCursus(String numeroEtudiant, Cursus cursus) {

		session.getTransaction().begin();
		Etudiant etud = get(numeroEtudiant);
		etud.setCursus(cursus);
		cursus.getEtudiants().add(etud);
		session.getTransaction().commit();

	}

	@Override
	public Cursus getCursus(String numeroEtudiant) {
		Etudiant etud = get(numeroEtudiant);
		return etud.getCursus();
	}

	@Override
	public EmploiDuTemps<Etudiant> getEmploiDuTemps(String numeroEtudiant) {
		// TODO Auto-generated method stub
		EmploiDuTemps<Etudiant> edt = new EmploiDuTemps<>();

		Etudiant etud = get(numeroEtudiant);
		edt.setBase(etud);

		List<Matiere> matieres = etud.getCursus().getMatieres();

		Set<Cours> cours = new TreeSet<Cours>(new Comparator<Cours>() {
			public int compare(Cours o1, Cours o2) {
				return o1.getCreneau().getHoraire().compareTo(o2.getCreneau().getHoraire());
			}
		});

		for (Matiere matiere : matieres)
			cours.addAll(matiere.getCours());

		for (Cours c : cours) {
			CreneauEmploiDuTemps creneau = new CreneauEmploiDuTemps();
			creneau.setMatiere(c.getMatiere());
			creneau.setSalle(c.getCreneau().getSalle());
			creneau.setHoraire(c.getCreneau().getHoraire());

			for (Professeur p : c.getProfesseurs()) {
				creneau.addProfesseur(p);
			}

			edt.addCreneau(creneau);

		}

		return edt;

	}

	@Override
	public void addCreneauEmploiDuTemps(String numeroEtudiant, Horaire horaire, Salle salle) {

	}

}
