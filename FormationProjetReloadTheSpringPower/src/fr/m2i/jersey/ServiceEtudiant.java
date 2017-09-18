package fr.m2i.jersey;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.acceis.jpa.jpa.CoursDao;
import fr.acceis.jpa.jpa.CreneauDao;
import fr.acceis.jpa.jpa.Dao;
import fr.acceis.jpa.jpa.EtudiantDao;
import fr.acceis.jpa.jpa.HoraireDao;
import fr.acceis.jpa.jpa.IEtudiantDao;
import fr.acceis.jpa.jpa.MatiereDao;
import fr.acceis.jpa.jpa.SalleDao;
import model.Cours;
import model.Creneau;
import model.Cursus;
import model.Etudiant;
import model.Horaire;
import model.Matiere;
import model.Salle;

@Controller
@RequestMapping("/etudiants")
public class ServiceEtudiant {

	@Autowired
	private IEtudiantDao daoEtudiant;

	@RequestMapping(method=RequestMethod.GET, produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Etudiant>> getAllEtudiants() {
		List<Etudiant> etudiants = daoEtudiant.getAll();
		System.out.println("passe");

		etudiants.stream().filter(etud -> etud.getCursus() == null).forEach(etud -> etud.setCursus(new Cursus()));
		
		// for(Etudiant etud:etudiants){
		// System.out.println(etud.getCursus().getEtudiants());
		// }

		// etudiants.stream()
		// .filter(etud -> etud.getCursus().getEtudiants()!=null )
		// .forEach(etud->etud.getCursus().setEtudiants(null));
		//
		return new ResponseEntity<>(etudiants, HttpStatus.OK);

	}

	@RequestMapping(value="{numeroEtudiant}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Etudiant> getEtudiant(@PathVariable("numeroEtudiant") String numeroEtudiant) {

		Etudiant etudiant = daoEtudiant.get(numeroEtudiant);

		return new ResponseEntity<>(etudiant, HttpStatus.OK);

	}

	@RequestMapping(value="{numeroEtudiant}/edt/add",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addCreneauEmploiDuTemps(@PathVariable("numeroEtudiant") String numeroEtudiant, Horaire horaire,
			@RequestParam("idSalle") Long idSalle, @RequestParam("idMatiere") Long idMatiere) {

		Dao<Horaire, Long> daoHoraire = new HoraireDao();
		Dao<Matiere,Long> daoMatiere = new MatiereDao();
		Dao<Salle, Long> daoSalle = new SalleDao();
		Dao<Creneau, Long> daoCreneau = new CreneauDao();
		Dao<Cours, Long> daoCours = new CoursDao();

		System.out.println("creneau");

		Horaire horaireIdentified = daoHoraire.add(horaire);
		Salle salle = daoSalle.get(idSalle);
		Matiere matiere = daoMatiere.get(idMatiere);
		
		Creneau creneau = new Creneau();
		creneau.setHoraire(horaireIdentified);
		creneau.setSalle(salle);
		Cours cours= new Cours();
		cours = daoCours.add(cours);

		cours.setMatiere(matiere);
		creneau.setCours(cours);
		cours.setCreneau(creneau);
		
		System.out.println(creneau.getCours().getId());
		daoCreneau.add(creneau);
		
		
		((IEtudiantDao) daoEtudiant).addCreneauEmploiDuTemps(numeroEtudiant,horaireIdentified,salle);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(method=RequestMethod.POST,value="add",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Etudiant> addEtudiant(RequestEntity<Etudiant> reqEtudiant) {
		
		Etudiant etudiant=reqEtudiant.getBody();
		/*
		 * Etudiant etudiant = new Etudiant();
		 * 
		 * etudiant.setNumeroEtudiant(numeroEtudiant); etudiant.setNom(nom);
		 * etudiant.setPrenom(prenom);
		 */
		
		if(daoEtudiant.get(etudiant.getNumeroEtudiant())!=null){
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		Etudiant etudiantRes=daoEtudiant.add(etudiant);

		return new ResponseEntity<>(etudiantRes,HttpStatus.OK);

	}

	
	@RequestMapping(method=RequestMethod.PUT,value="{etudiantNumeroEtudiant}/update",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateEtudiant(@PathVariable("etudiantNumeroEtudiant") String numeroEtudiant,Etudiant etudiant) {

		

		daoEtudiant.update(numeroEtudiant, etudiant);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	
	@RequestMapping(method=RequestMethod.DELETE,value="{numeroEtudiant}/delete")
	public ResponseEntity<?> deleteEtudiant(@PathVariable("numeroEtudiant") String numeroEtudiant) {
		
		daoEtudiant.delete(numeroEtudiant);

		return new ResponseEntity<>(HttpStatus.OK);

	}

}
