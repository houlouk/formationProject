package fr.acceis.jpa.jpa;

import model.Cursus;
import model.Matiere;

public interface ICursusDao extends Dao<Cursus, Long> {
	
	
	public void addMatiere(long cursusId,Matiere m);

}
