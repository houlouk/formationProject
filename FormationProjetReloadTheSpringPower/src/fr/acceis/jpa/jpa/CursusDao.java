package fr.acceis.jpa.jpa;

import model.Cursus;
import model.Matiere;

public class CursusDao extends HibernateDaoSessionFactory<Cursus, Long> implements ICursusDao {
	
	public CursusDao(){
		this.classT=Cursus.class;
	}

	@Override
	public void addMatiere(long cursusId,Matiere m) {
		session.getTransaction().begin();

		Cursus cursus = get(cursusId);
		cursus.getMatieres().add(m);
		m.getCursus().add(cursus);
		session.getTransaction().commit();

		
	}
	
}
