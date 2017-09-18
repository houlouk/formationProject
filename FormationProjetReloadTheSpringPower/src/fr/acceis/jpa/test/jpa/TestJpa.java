package fr.acceis.jpa.test.jpa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import fr.acceis.jpa.jpa.HibernateUtil;
import model.Cours;
import model.Creneau;
import model.Cursus;
import model.Etudiant;
import model.Matiere;
import model.Message;
import model.Professeur;
import model.Salle;
import nonPojoModel.CreneauEmploiDuTemps;
import nonPojoModel.EmploiDuTemps;

public class TestJpa {

	public static Session session = HibernateUtil.getSession();

	public static void main(String[] args) throws Exception {
		// listerEtudiants();
		// listerProfesseurs();
		// listerSalles();

		// cursusEtudiant("21002128");
		// salleCours(58);
		// listerCoursSalle("i51");
		// listerEtudiantsCours(58);

		// listerProfesseursCursus(10);
		// listerProfesseursMatiere(5);
		// listerProfsEtudiant("21002128");
		// emploiDuTempsSalle("i51");
		// emploiDuTempsEtudiant("21002128");
		// removeEtudiant("21002128");
		listerEtudiants();
		// emploiDuTempsProfesseur(54);
		session.close();
		//HibernateUtil.getSessionFactory().close();
	}

	public static <T> List<T> listAll(Class<T> classe) {

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(classe);
		Root<T> root = criteria.from(classe);
		criteria.select(root);
		Query<T> query = session.createQuery(criteria);

		List<T> listAll = query.getResultList();

		return listAll;

	}

	// Liste les étudiants
	public static List<Etudiant> listerEtudiants() {
		List<Etudiant> etudiantsList = listAll(Etudiant.class);

		for (Etudiant e : etudiantsList) {
			System.out.println(e.getNom() + " " + e.getPrenom());
		}

		return etudiantsList;

		// TODO

	}

	// Liste les étudiants
	public static Etudiant addEtudiant(Etudiant etud) {

		System.out.println(etud);
		session.getTransaction().begin();
		session.merge(etud);
		session.getTransaction().commit();

		return etud;

		// TODO

	}


	// Liste les professeurs
	public static List<Professeur> listerProfesseurs() {
		List<Professeur> professeursList = listAll(Professeur.class);

		for (Professeur p : professeursList) {
			System.out.println(p.getNom() + " " + p.getPrenom());
		}

		return professeursList;
		// TODO

	}


	// Liste les salles
	public static void listerSalles() {
		List<Salle> sallesList = listAll(Salle.class);

		for (Salle s : sallesList) {
			System.out.println(s.getNom());
		}
		// TODO

	}



	// Affiche le nom du cursus d'un étudiant
	public static void cursusEtudiant(String numeroEtudiant) {
		Etudiant etud = session.load(Etudiant.class, numeroEtudiant);

		System.out.println(etud.getCursus().getNom());

		// TODO

	}


	// Affiche le nom de la salle dans laquelle a lieu un cours
	public static void salleCours(long idCours) {
		Cours cours = session.load(Cours.class, idCours);

		System.out.println(cours.getCreneau().getSalle().getNom());

		// TODO

	}


	// Affiche le nom des cours ayant lieu dans une salle
	public static void listerCoursSalle(String nomSalle) {

		List<Salle> ls = getSalleFromNomSalle(nomSalle);

		for (Salle s : ls) {
			for (Creneau cren : s.getCreneaux()) {
				System.out.println(cren.getCours().getMatiere().getNom());
			}
		}

	}

	public static List<Salle> getSalleFromNomSalle(String nomSalle) {
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Salle> criteria = criteriaBuilder.createQuery(Salle.class);
		Root<Salle> root = criteria.from(Salle.class);
		criteria.select(root);
		criteria.where(criteriaBuilder.equal(root.get("nom"), nomSalle));
		Query<Salle> query = session.createQuery(criteria);

		List<Salle> ls = query.getResultList();
		return ls;
	}


	// Affiche le nom des étudiants qui assistent à un cours
	public static void listerEtudiantsCours(long idCours) {
		Cours cours = session.load(Cours.class, idCours);

		for (Cursus cur : cours.getMatiere().getCursus()) {
			for (Etudiant etud : cur.getEtudiants()) {
				System.out.println(etud.getNom() + " " + etud.getPrenom());
			}
		}
		// TODO

	}


	// Affiche le nom des professeurss qui enseignent dans un cursus
	public static void listerProfesseursCursus(long idCursus) {
		Cursus cur = session.load(Cursus.class, idCursus);

		Set<Professeur> profs = new HashSet<>();

		for (Matiere m : cur.getMatieres()) {
			for (Cours c : m.getCours()) {
				for (Professeur p : c.getProfesseurs()) {
					profs.add(p);
				}
			}
		}

		for (Professeur p : profs) {
			System.out.println(p.getNom() + " " + p.getPrenom());
		}
		// TODO

	}


	// Affiche le nom des professeurs qui enseignent une matière
	public static void listerProfesseursMatiere(long idMatiere) {
		Matiere m = session.load(Matiere.class, idMatiere);
		Set<Professeur> profs = new HashSet<>();

		for (Cours c : m.getCours()) {
			for (Professeur p : c.getProfesseurs()) {
				profs.add(p);
			}
		}

		// TODO

	}

	// Affiche des profs qui enseignent à un étudiant
	public static void listerProfsEtudiant(String numeroEtudiant) {
		Etudiant etud = session.load(Etudiant.class, numeroEtudiant);
		Set<Professeur> profs = new HashSet<>();

		for (Matiere m : etud.getCursus().getMatieres()) {
			for (Cours c : m.getCours()) {
				for (Professeur p : c.getProfesseurs()) {
					profs.add(p);

				}
			}
		}

		for (Professeur p : profs) {
			System.out.println(p);
		}
		// TODO

	}


	// Affiche l'emploi du temps d'une salle
	public static void emploiDuTempsSalle(String nomSalle) {
		List<Salle> ls = getSalleFromNomSalle(nomSalle);

		for (Salle s : ls) {
			System.out.println(s.getNom());
			for (Creneau cren : s.getCreneaux()) {
				System.out.print("**" + cren.getHoraire());
				System.out.print(" " + cren.getCours().getMatiere().getNom());
				for (Professeur p : cren.getCours().getProfesseurs()) {
					System.out.println(" " + p);
				}
			}
		}
		// TODO

	}

	// Affiche l'emploi du temps d'un étudiant
	public static EmploiDuTemps<Etudiant> emploiDuTempsEtudiant(String numeroEtudiant) {
		EmploiDuTemps<Etudiant> edt = new EmploiDuTemps<>();
		Etudiant etud = session.load(Etudiant.class, numeroEtudiant);

		edt.setBase(etud);

		List<Matiere> matieres = etud.getCursus().getMatieres();

		Set<Cours> cours = new TreeSet<Cours>(new Comparator<Cours>() {
			public int compare(Cours o1, Cours o2) {
				return o1.getCreneau().getHoraire().compareTo(o2.getCreneau().getHoraire());
			}
		});

		for (Matiere matiere : matieres)
			cours.addAll(matiere.getCours());

		System.out.println(etud.getNom() + " " + etud.getPrenom());

		for (Cours c : cours) {
			CreneauEmploiDuTemps creneau = new CreneauEmploiDuTemps();
			creneau.setMatiere(c.getMatiere());
			System.out.println(" " + c.getMatiere().getNom());
			creneau.setSalle(c.getCreneau().getSalle());
			System.out.print(" " + c.getCreneau().getSalle().getNom());
			creneau.setHoraire(c.getCreneau().getHoraire());
			System.out.print(" " + c.getCreneau().getHoraire());

			for (Professeur p : c.getProfesseurs()) {
				creneau.addProfesseur(p);

				System.out.println(p);
			}

			edt.addCreneau(creneau);

		}

		return edt;

		// TODO

	}



	// Affiche l'emploi du temps d'un professeur
	public static void emploiDuTempsProfesseur(long idProfesseur) {

		Professeur prof = session.load(Professeur.class, idProfesseur);

		System.out.println(prof.getNom() + " " + prof.getPrenom());

		for (Cours c : prof.getCours()) {
			System.out.print(" " + c.getMatiere().getNom());
			System.out.print(" " + c.getCreneau().getSalle().getNom());
			System.out.println(" " + c.getCreneau().getHoraire());

		}

	}


	public static void removeEtudiant(String numeroEtudiant) {

		Etudiant etud = session.load(Etudiant.class, numeroEtudiant);
		session.getTransaction().begin();
		session.delete(etud);
		Message msg = new Message();
		msg.setProfesseurs(new ArrayList<>());
		msg.setMessage("L'étudiant " + etud + " a été renvoyé");

		if (etud.getCursus() != null) {
			for (Matiere m : etud.getCursus().getMatieres()) {
				for (Cours c : m.getCours()) {
					for (Professeur p : c.getProfesseurs()) {
						msg.getProfesseurs().add(p);
						p.getMessage().add(msg);
					}
				}

			}

			session.persist(msg);

			List<Message> messages = listAll(Message.class);

			messages.forEach(mes -> System.out.println(mes));

		}

		session.getTransaction().commit();

	}


	public static void removeMatiere(long idMatiere) {

		Matiere mat = session.load(Matiere.class, idMatiere);
		session.getTransaction().begin();
		session.delete(mat);
		Message msg = new Message();
		msg.setProfesseurs(new ArrayList<>());
		msg.setMessage("La matière " + mat + " a été supprimée");

		for (Cours c : mat.getCours()) {
			for (Professeur p : c.getProfesseurs()) {
				msg.getProfesseurs().add(p);
				p.getMessage().add(msg);
			}
		}

		session.persist(msg);

		session.getTransaction().commit();

		List<Message> messages = listAll(Message.class);

		messages.forEach(mes -> System.out.println(mes));

	}

	// TODO

	// TODO

}
