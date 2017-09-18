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

import model.Salle;

@Controller
@RequestMapping("/salles")
public class ServiceSalle {

	@Autowired
	private ISalleDao daoSalle; 

	@RequestMapping(method=RequestMethod.GET, produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Salle>> getAllSalles() {
		List<Salle> salles = daoSalle.getAll();

		
		return new ResponseEntity<>(salles, HttpStatus.OK);

	}

	@RequestMapping(value="{idSalle}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Salle> getSalle(@PathVariable("idSalle") Long idSalle) {

		Salle salle = daoSalle.get(idSalle);

		return new ResponseEntity<>(salle, HttpStatus.OK);

	}


	@RequestMapping(method=RequestMethod.POST,value="add",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Salle> addSalle(RequestEntity<Salle> reqSalle) {
		
		Salle salle=reqSalle.getBody();
		
		
		Salle salleRes=daoSalle.add(salle);

		return new ResponseEntity<>(salleRes,HttpStatus.OK);

	}

	
	@RequestMapping(method=RequestMethod.PUT,value="{idSalle}/update",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateSalle(@PathVariable("idSalle") Long idSalle,Salle salle) {

		

		daoSalle.update(idSalle, salle);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	
	@RequestMapping(method=RequestMethod.DELETE,value="{idSalle}/delete")
	public ResponseEntity<?> deleteSalle(@PathVariable("idSalle") Long idSalle) {
		
		daoSalle.delete(idSalle);

		return new ResponseEntity<>(HttpStatus.OK);

	}

}


