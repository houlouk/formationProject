package fr.acceis.jpa.jpa;

import model.Cours;

public class CoursDao extends HibernateDaoSessionFactory<Cours, Long> {
	
	public CoursDao(){
		this.classT = Cours.class;
	}

}
