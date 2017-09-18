package fr.acceis.jpa.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Etudiant;
import model.Matiere;

public class TestJdbc {

	private static Connection connexion;
	//private static final String matieresCours = "SELECT id from cours where matiere_id=?";

	public static void main(String[] args) throws Exception {

		// Instanciation du driver
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		// Connexion à la base de données
		connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa", "");
		// listerEtudiants();
		// listerProfesseurs();
		// listerSalles();
		// cursusEtudiant("21002128");
		// salleCours(67);
		// listerCoursSalle("i52");
		// listerEtudiantsCours(67);
		// listerProfesseursCursus(10);
		 //listerProfesseursMatiere(5);
		// listerProfsEtudiant("21002128");
		 //emploiDuTempsSalle("i51");
		//emploiDuTempsEtudiant("21002128");
		 //emploiDuTempsProfesseur(54);

		// removeEtudiant("21002128");
		// listerEtudiants();
		 listerMessages();
		// listerMessagesProfs();
		//
		// removeEtudiant("21002130");
		// listerEtudiants();

		/*removeMatiere(4);
		listerMessages();

		listerMessagesProfs();*/



	}

	@SuppressWarnings("unused")
	private static void removeEtudiant(String numeroEtudiant)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Long> idProfs = listerProfsEtudiant(numeroEtudiant);
		Etudiant etudiant = getEtudiant(numeroEtudiant);
		System.out.println(idProfs.size());
		long idMessage;

		if (idProfs.size() > 0) {
			idMessage = sendMessage("L'étudiant " + etudiant.getNom() + " " + etudiant.getPrenom() + "a été renvoyé");

			for (Long idProf : idProfs) {
				sendMessageToProf(idMessage, idProf);

				// System.out.println("L'étudiant " + etudiant.getNom() + " "
				// +etudiant.getPrenom() + "a été renvoyé");
			}

			deleteEtudiant(numeroEtudiant);

		}

	}

	private static void removeMatiere(long idMatiere)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Long> idProfs = listerProfesseursMatiere(idMatiere);
		Matiere matiere = getMatiere(idMatiere);
		System.out.println(idProfs.size());
		long idMessage;

		if (idProfs.size() > 0) {
			idMessage = sendMessage("La matiere " + matiere.getNom() + " a été supprimée");

			for (Long idProf : idProfs) {
				sendMessageToProf(idMessage, idProf);

				// System.out.println("L'étudiant " + etudiant.getNom() + " "
				// +etudiant.getPrenom() + "a été renvoyé");
			}
			deleteCursusMatiere(idMatiere);
			deleteCoursProfesseurWhenCoursMatiereIS(idMatiere);
			deleteCreneauWhenCoursMatiereIS(idMatiere);
			deleteCours(idMatiere);
			deleteMatiere(idMatiere);

		}

	}

	private static void deleteCreneauWhenCoursMatiereIS(long idMatiere)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		PreparedStatement stmt = prepareQuery(
				"delete from Creneau WHERE cours_id IN  (SELECT id from cours where matiere_id=?)");
		stmt.setLong(1, idMatiere);
		stmt.executeUpdate();
		// TODO Auto-generated method stub

	}

	private static void deleteCoursProfesseurWhenCoursMatiereIS(Long idMatiere)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		PreparedStatement stmt = prepareQuery(
				"delete from Cours_professeur WHERE cours_id IN (SELECT id from cours where matiere_id=?)");
		stmt.setLong(1, idMatiere);
		stmt.executeUpdate();
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unused")
	private static List<Long> getCours(long idMatiere)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		PreparedStatement stmt = prepareQuery("Select matiere_id FROM Cours where matiere_id=?");
		stmt.setLong(1, idMatiere);
		List<Long> cours = new ArrayList<Long>();
		ResultSet result = stmt.executeQuery();

		while (result.next()) {
			cours.add(result.getLong("matiere_id"));

		}

		return cours;
		// TODO Auto-generated method stub

	}

	private static void deleteCours(long idMatiere)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		PreparedStatement stmt = prepareQuery("delete from Cours WHERE matiere_id =?");
		stmt.setLong(1, idMatiere);
		stmt.executeUpdate();
		// TODO Auto-generated method stub

	}

	private static void deleteCursusMatiere(long idMatiere)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		PreparedStatement stmt = prepareQuery("delete from CURSUS_MATIERE WHERE matieres_id=?");
		stmt.setLong(1, idMatiere);
		stmt.executeUpdate();

		// TODO Auto-generated method stub

	}

	private static void deleteMatiere(long idMatiere)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		PreparedStatement stmt = prepareQuery("delete from MATIERE WHERE id=?");
		stmt.setLong(1, idMatiere);
		stmt.executeUpdate();
		// TODO Auto-generated method stub

	}

	private static void listerMessagesProfs()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		ResultSet result = prepareQuery("Select * FROM message_prof").executeQuery();

		while (result.next()) {
			String idMessage = result.getString("message_id");
			String idProf = result.getString("professeur_id");

			System.out.println("* message " + idMessage + " envoyé à " + idProf);

		}
		// TODO Auto-generated method stub

	}

	private static void listerMessages()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		ResultSet result = prepareQuery("Select message FROM message").executeQuery();

		while (result.next()) {
			String message = result.getString("message");
			System.out.println("* " + message);

		}
		// TODO
		// TODO Auto-generated method stub

	}

	private static void deleteEtudiant(String numeroEtudiant)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		PreparedStatement stmt = prepareQuery("delete from ETUDIANT WHERE numeroEtudiant=?");
		stmt.setString(1, numeroEtudiant);

		stmt.executeUpdate();

		// TODO Auto-generated method stub

	}

	private static void sendMessageToProf(long idMessage, long idProf)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		PreparedStatement stmt = prepareQuery("INSERT INTO Message_prof values(?,?)");
		stmt.setLong(1, idMessage);
		stmt.setLong(2, idProf);
		stmt.executeUpdate();
	}

	private static long sendMessage(String message)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		PreparedStatement stmt = prepareQuery("INSERT INTO Message(message) values(?)");
		stmt.setString(1, message);
		stmt.executeUpdate();
		long key = 0;
		ResultSet generatedKeys = stmt.getGeneratedKeys();
		if (generatedKeys.next()) {
			key = generatedKeys.getLong(1);
		}

		return key;
		// TODO Auto-generated method stub

	}

	private static PreparedStatement prepareQuery(String query)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		// Création de la requête préparée
		PreparedStatement stmt = connexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

		// Exécution de la requête préparée

		return stmt;

	}

	private static Matiere getMatiere(long idMatiere)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		PreparedStatement stmt = prepareQuery("Select * FROM matiere where id=?");
		stmt.setLong(1, idMatiere);
		ResultSet result = stmt.executeQuery();

		Matiere matiere = new Matiere();

		while (result.next()) {
			matiere.setId(result.getLong("id"));
			matiere.setNom(result.getString("nom"));

		}

		return matiere;

	}

	private static Etudiant getEtudiant(String numeroEtudiant)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		PreparedStatement stmt = prepareQuery("Select * FROM etudiant where numeroEtudiant=?");
		stmt.setString(1, numeroEtudiant);
		ResultSet result = stmt.executeQuery();

		Etudiant etudiant = new Etudiant();

		while (result.next()) {
			etudiant.setNumeroEtudiant(result.getString("numeroEtudiant"));
			etudiant.setNom(result.getString("nom"));
			etudiant.setPrenom(result.getString("prenom"));

		}

		return etudiant;

	}

	// Liste les étudiants
	@SuppressWarnings("unused")
	private static void listerEtudiants()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// Instanciation du driver
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		// Connexion à la base de données
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa", "");

		// Création de la requête préparée
		PreparedStatement stmt = connexion.prepareStatement("SELECT * FROM etudiant");

		// Exécution de la requête préparée
		ResultSet result = stmt.executeQuery();

		// Affichage des résultats
		while (result.next()) {
			String prenom = result.getString("prenom");
			String nom = result.getString("nom");
			String numero = result.getString("numeroEtudiant");

			System.out.println("* " + prenom + " " + nom + " " + numero);
		}

		// Fermeture de la connexion
		connexion.close();
	}

	// Liste les professeurs
	@SuppressWarnings("unused")
	private static void listerProfesseurs()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		ResultSet result = prepareQuery("Select * FROM professeur").executeQuery();

		while (result.next()) {
			String prenom = result.getString("prenom");
			String nom = result.getString("nom");
			System.out.println("* " + prenom + " " + nom);

		}
		// TODO

	}

	// Liste les salles
	
	@SuppressWarnings("unused")
	private static void listerSalles()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		ResultSet result = prepareQuery("Select * FROM salle").executeQuery();

		while (result.next()) {
			String nom = result.getString("nom");
			System.out.println("* " + nom);

		}
		// TODO

	}

	// Affiche le nom du cursus d'un étudiant
	@SuppressWarnings("unused")
	private static void cursusEtudiant(String numeroEtudiant)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		PreparedStatement stmt = prepareQuery(
				"select e.nom, c.nom from " + "cursus c inner join cursus_etudiant ce on c.id = ce.cursus_id "
						+ "inner join etudiant e on ce.etudiants_numeroetudiant=e.numeroEtudiant "
						+ "where e.numeroEtudiant=?");
		stmt.setString(1, numeroEtudiant);

		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			String nom = result.getString("e.nom");
			String cursus = result.getString("c.nom");

			System.out.println("* " + nom + " " + cursus);

		}

		// TODO

	}

	// Affiche le nom de la salle dans laquelle a lieu un cours
	@SuppressWarnings("unused")
	private static void salleCours(long idCours)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		PreparedStatement stmt = prepareQuery("select s.nom as salle_nom,c.id as cours_id " + "from salle s "
				+ "inner join creneau cren on s.id = cren.salle_id " + "inner join cours c on cren.id=c.creneau_id "
				+ "where c.id=?");

		stmt.setLong(1, idCours);

		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			String nom = result.getString("salle_nom");
			String cours_id = result.getString("cours_id");

			System.out.println("* " + nom + " " + cours_id);

		}
		// TODO

	}

	// Affiche le nom des cours ayant lieu dans une salle
	@SuppressWarnings("unused")
	private static void listerCoursSalle(String nomSalle)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		PreparedStatement stmt = prepareQuery("select m.nom as cours_nom " + "from salle s "
				+ "inner join creneau cren on s.id = cren.salle_id " + "inner join cours c on cren.id=c.creneau_id "
				+ "inner join matiere m on c.matiere_id=m.id " + "where s.nom=?");

		stmt.setString(1, nomSalle);

		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			String cours_nom = result.getString("cours_nom");

			System.out.println("* " + cours_nom);

		}
		// TODO
		// TODO

	}

	// Affiche le nom des étudiants qui assistent à un cours
	@SuppressWarnings("unused")
	private static void listerEtudiantsCours(long idCours)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		PreparedStatement stmt = prepareQuery(
				"select e.nom as etudiant_nom " + "from etudiant e " + "inner join cursus cur on e.cursus_id = cur.id "
						+ "inner join cursus_matiere cm on cm.cursus_id=cur.id "
						+ "inner join matiere m on cm.matieres_id=m.id " + "inner join cours c on c.matiere_id=m.id "
						+ "where c.id=?");

		stmt.setLong(1, idCours);

		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			String etudiant_nom = result.getString("etudiant_nom");

			System.out.println("* " + etudiant_nom);

		}
		// TODO
		// TODO

		// TODO

	}

	// Affiche le nom des professeurss qui enseignent dans un cursus
	@SuppressWarnings("unused")
	private static void listerProfesseursCursus(long idCursus)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		PreparedStatement stmt = prepareQuery("select distinct p.nom as prof_nom,p.prenom as prof_prenom "
				+ "from professeur p " + "inner join cours_professeur cp on p.id = cp.professeurs_id "
				+ "inner join cours c on cp.cours_id=c.id " + "inner join matiere m on c.matiere_id=m.id "
				+ "inner join cursus_matiere cm on cm.matieres_id=m.id "
				+ "inner join cursus cur on cur.id=cm.cursus_id " + "where cur.id=?");

		stmt.setLong(1, idCursus);

		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			String prof_nom = result.getString("prof_nom");
			String prof_prenom = result.getString("prof_prenom");

			System.out.println("* " + prof_nom + " " + prof_prenom);

		}
		// TODO
		// TODO

		// TODO
		// TODO

	}

	// Affiche le nom des professeurs qui enseignent une matière
	
	private static List<Long> listerProfesseursMatiere(long idMatiere)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Long> idProfs = new ArrayList<Long>();
		PreparedStatement stmt = prepareQuery(
				"select distinct p.nom as prof_nom,p.prenom as prof_prenom,p.id as prof_id " + "from professeur p "
						+ "inner join cours_professeur cp on p.id = cp.professeurs_id "
						+ "inner join cours c on cp.cours_id=c.id " + "inner join matiere m on c.matiere_id=m.id "
						+ "where m.id=?");

		stmt.setLong(1, idMatiere);

		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			String prof_nom = result.getString("prof_nom");
			String prof_prenom = result.getString("prof_prenom");
			idProfs.add(result.getLong("id"));

			System.out.println("* " + prof_nom + " " + prof_prenom);

		}

		return idProfs;
		// TODO

	}

	// Affiche des profs qui enseignent à un étudiant
	private static List<Long> listerProfsEtudiant(String numeroEtudiant)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		List<Long> lprofs = new ArrayList<Long>();
		PreparedStatement stmt = prepareQuery(
				"select distinct p.nom as prof_nom,p.prenom as prof_prenom,p.id as prof_id " + "from professeur p "
						+ "inner join cours_professeur cp on p.id = cp.professeurs_id "
						+ "inner join cours c on cp.cours_id=c.id " + "inner join matiere m on c.matiere_id=m.id "
						+ "inner join cursus_matiere cm on cm.matieres_id=m.id "
						+ "inner join cursus cur on cur.id=cm.cursus_id "
						+ "inner join etudiant e on cur.id=e.cursus_id " + "where e.numeroEtudiant=?");

		stmt.setString(1, numeroEtudiant);

		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			String prof_nom = result.getString("prof_nom");
			String prof_prenom = result.getString("prof_prenom");
			Long idProf = result.getLong("prof_id");

			lprofs.add(idProf);

			System.out.println("* " + prof_nom + " " + prof_prenom);

		}

		return lprofs;
		// TODO

		// TODO

	}

	// Affiche l'emploi du temps d'une salle
	@SuppressWarnings("unused")
	private static void emploiDuTempsSalle(String nomSalle)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		PreparedStatement stmt = prepareQuery(
				"select distinct h.debut as horaire_debut,h.fin as horaire_fin,p.nom as prof_nom,p.prenom as prof_prenom,s.nom as salle_nom, m.nom as matiere_nom "
						+ "from salle s " + "inner join creneau cren on cren.salle_id = s.id "
						+ "inner join horaire h on cren.horaire_id=h.id "
						+ "inner join cours c on c.creneau_id=cren.id "
						+ "inner join cours_professeur cp on cp.cours_id=c.id "
						+ "inner join professeur p on cp.professeurs_id = p.id "
						+ "inner join matiere m on c.matiere_id=m.id " + "where s.nom=? " + "order by horaire_debut");

		stmt.setString(1, nomSalle);

		ResultSet result = stmt.executeQuery();
		boolean first = true;
		while (result.next()) {
			String salle_nom = result.getString("salle_nom");
			String horaire_debut = result.getString("horaire_debut");
			String horaire_fin = result.getString("horaire_fin");
			String matiere_nom = result.getString("matiere_nom");
			String prof_nom = result.getString("prof_nom");
			String prof_prenom = result.getString("prof_prenom");

			if (first)
				System.out.println("* " + salle_nom);
			System.out.println("*** " + matiere_nom + " " + prof_nom + " " + prof_prenom + " " + horaire_debut + " "
					+ horaire_fin);

			first = false;
		}
		// TODO

		// TODO
		// TODO

	}

	// Affiche l'emploi du temps d'un étudiant
	
	@SuppressWarnings("unused")
	private static void emploiDuTempsEtudiant(String numeroEtudiant)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		PreparedStatement stmt = prepareQuery(
				"select distinct h.debut as horaire_debut,h.fin as horaire_fin,e.nom as etudiant_nom, e.prenom as etudiant_prenom, m.nom as matiere_nom, s.nom as salle_nom "
						+ "from etudiant e " + "inner join cursus cur on cur.id=e.cursus_id "
						+ "inner join cursus_matiere cm on cm.cursus_id = cur.id "
						+ "inner join matiere m on m.id=cm.matieres_id " + "inner join cours c on c.matiere_id=m.id "
						+ "inner join creneau cren on c.creneau_id=cren.id "
						+ "inner join salle s on s.id = cren.salle_id "
						+ "inner join horaire h on cren.horaire_id=h.id " + "where e.numeroEtudiant=? "
						+ "order by horaire_debut");

		stmt.setString(1, numeroEtudiant);

		ResultSet result = stmt.executeQuery();
		boolean first = true;
		while (result.next()) {
			String salle_nom = result.getString("salle_nom");
			String etudiant_nom = result.getString("etudiant_nom");
			String etudiant_prenom = result.getString("etudiant_prenom");

			String horaire_debut = result.getString("horaire_debut");
			String horaire_fin = result.getString("horaire_fin");
			String matiere_nom = result.getString("matiere_nom");

			if (first)
				System.out.println("* " + etudiant_nom + " " + etudiant_prenom);

			System.out.println("** " + matiere_nom);

			System.out.println("*** " + salle_nom + " " + horaire_debut + " " + horaire_fin);

			first = false;

		}

		// TODO

	}

	// Affiche l'emploi du temps d'un professeur
	
	@SuppressWarnings("unused")
	private static void emploiDuTempsProfesseur(long idProfesseur)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		PreparedStatement stmt = prepareQuery(
				"select distinct h.debut as horaire_debut,h.fin as horaire_fin,p.nom as prof_nom, p.prenom as prof_prenom, m.nom as matiere_nom, s.nom as salle_nom "
						+ "from  professeur p " + "inner join cours_professeur cp on cp.professeurs_id=p.id "
						+ "inner join cours c on cp.cours_id=c.id " + "inner join matiere m on c.matiere_id=m.id "
						+ "inner join creneau cren on c.creneau_id=cren.id "
						+ "inner join salle s on s.id = cren.salle_id "
						+ "inner join horaire h on cren.horaire_id=h.id " + "where p.id=? " + "order by horaire_debut");

		stmt.setLong(1, idProfesseur);

		ResultSet result = stmt.executeQuery();
		boolean first = true;
		while (result.next()) {
			String salle_nom = result.getString("salle_nom");
			String prof_nom = result.getString("prof_nom");
			String prof_prenom = result.getString("prof_prenom");

			String horaire_debut = result.getString("horaire_debut");
			String horaire_fin = result.getString("horaire_fin");
			String matiere_nom = result.getString("matiere_nom");

			if (first)
				System.out.println("* " + prof_nom + " " + prof_prenom);

			System.out.println("** " + matiere_nom);

			System.out.println("*** " + salle_nom + " " + horaire_debut + " " + horaire_fin);

			first = false;
			// TODO

		}

	}
}
