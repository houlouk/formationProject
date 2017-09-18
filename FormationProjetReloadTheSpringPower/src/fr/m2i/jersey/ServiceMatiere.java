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

import fr.acceis.jpa.jpa.IMatiereDao;
import model.Matiere;


@Controller
@RequestMapping("/matieres")
public class ServiceMatiere {

	@Autowired
	private IMatiereDao daoMatiere; 

	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Matiere>> getAllMatieres() {
		List<Matiere> matieres = daoMatiere.getAll();

		
		return new ResponseEntity<>(matieres, HttpStatus.OK);

	}

	@RequestMapping(value="{idMatiere}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Matiere> getMatiere(@PathVariable("idMatiere") Long idMatiere) {

		Matiere matiere = daoMatiere.get(idMatiere);

		return new ResponseEntity<>(matiere, HttpStatus.OK);

	}


	@RequestMapping(method=RequestMethod.POST,value="add",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Matiere> addMatiere(RequestEntity<Matiere> reqMatiere) {
		
		Matiere matiere=reqMatiere.getBody();
		
		
		Matiere matiereRes=daoMatiere.add(matiere);

		return new ResponseEntity<>(matiereRes,HttpStatus.OK);

	}

	
	@RequestMapping(method=RequestMethod.PUT,value="{idMatiere}/update",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateMatiere(@PathVariable("idMatiere") Long idMatiere,Matiere matiere) {

		

		daoMatiere.update(idMatiere, matiere);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	
	@RequestMapping(method=RequestMethod.DELETE,value="{idMatiere}/delete")
	public ResponseEntity<?> deleteMatiere(@PathVariable("idMatiere") Long idMatiere) {
		
		daoMatiere.delete(idMatiere);

		return new ResponseEntity<>(HttpStatus.OK);

	}

}


