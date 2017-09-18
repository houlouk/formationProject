package fr.m2i.jersey;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.acceis.jpa.jpa.IProfesseurDao;
import model.Professeur;

@Controller
@RequestMapping("/professeurs")
public class ServiceProfesseur {

	@Autowired
	private IProfesseurDao daoProfesseur;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Professeur>> getAllProfesseurs() {
		List<Professeur> professeurs = daoProfesseur.getAll();

		return new ResponseEntity<>(professeurs, HttpStatus.OK);

	}

	@RequestMapping(value = "{idProfesseur}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Professeur> getProfesseur(@PathVariable("idProfesseur") Long idProfesseur) {

		Professeur professeur = daoProfesseur.get(idProfesseur);

		return new ResponseEntity<>(professeur, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST, value = "add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Professeur> addProfesseur(RequestEntity<Professeur> reqProfesseur) {

		Professeur professeur = reqProfesseur.getBody();

	
		Professeur professeurRes = daoProfesseur.add(professeur);

		return new ResponseEntity<>(professeurRes, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PUT, value = "{idProfesseur}/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateProfesseur(@PathVariable("idProfesseur") Long idProfesseur, Professeur professeur) {

		daoProfesseur.update(idProfesseur, professeur);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{idProfesseur}/delete")
	public ResponseEntity<?> deleteProfesseur(@PathVariable("idProfesseur") Long idProfesseur) {

		daoProfesseur.delete(idProfesseur);

		return new ResponseEntity<>(HttpStatus.OK);

	}

}
