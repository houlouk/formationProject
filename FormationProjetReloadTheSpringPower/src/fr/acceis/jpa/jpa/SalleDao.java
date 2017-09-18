package fr.acceis.jpa.jpa;

import fr.m2i.jersey.ISalleDao;
import model.Salle;

public class SalleDao extends HibernateDaoSessionFactory<Salle, Long> implements ISalleDao {
	
	public SalleDao(){
		this.classT=Salle.class;
	}

}
