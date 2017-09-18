package fr.acceis.jpa.jpa;

import model.Creneau;

public class CreneauDao extends HibernateDaoSessionFactory<Creneau, Long> {
	
	public CreneauDao(){
		this.classT = Creneau.class;
	}

}
