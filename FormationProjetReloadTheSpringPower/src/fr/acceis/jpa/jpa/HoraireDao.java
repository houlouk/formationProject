package fr.acceis.jpa.jpa;

import model.Horaire;

public class HoraireDao extends HibernateDaoSessionFactory<Horaire, Long> {
	
	public HoraireDao(){
		this.classT = Horaire.class;
	}

	
}
