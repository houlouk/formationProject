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

import fr.acceis.jpa.jpa.ICursusDao;
import model.Cursus;


            

@Controller
@RequestMapping("/cursuss")
public class ServiceCursus {

	@Autowired
	private ICursusDao daoCursus; 

	@RequestMapping(method=RequestMethod.GET, produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Cursus>> getAllCursuss() {
		List<Cursus> cursuss = daoCursus.getAll();

		
		return new ResponseEntity<>(cursuss, HttpStatus.OK);

	}

	@RequestMapping(value="{idCursus}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cursus> getCursus(@PathVariable("idCursus") Long idCursus) {

		Cursus cursus = daoCursus.get(idCursus);

		return new ResponseEntity<>(cursus, HttpStatus.OK);

	}


	@RequestMapping(method=RequestMethod.POST,value="add",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Cursus> addCursus(RequestEntity<Cursus> reqCursus) {
		
		Cursus cursus=reqCursus.getBody();
		
		
		Cursus cursusRes=daoCursus.add(cursus);

		return new ResponseEntity<>(cursusRes,HttpStatus.OK);

	}

	
	@RequestMapping(method=RequestMethod.PUT,value="{idCursus}/update",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCursus(@PathVariable("idCursus") Long idCursus,Cursus cursus) {

		

		daoCursus.update(idCursus, cursus);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	
	@RequestMapping(method=RequestMethod.DELETE,value="{idCursus}/delete")
	public ResponseEntity<?> deleteCursus(@PathVariable("idCursus") Long idCursus) {
		
		daoCursus.delete(idCursus);

		return new ResponseEntity<>(HttpStatus.OK);

	}

}



