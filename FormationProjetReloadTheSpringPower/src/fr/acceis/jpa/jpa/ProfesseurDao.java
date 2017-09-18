package fr.acceis.jpa.jpa;

import model.Professeur;

public class ProfesseurDao extends HibernateDaoSessionFactory<Professeur, Long> implements IProfesseurDao{

	public ProfesseurDao() {
		super();
		this.classT = Professeur.class;
		// TODO Auto-generated constructor stub
	}

	
}
