package fr.acceis.jpa.jpa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	


	public static Session getSession() {
		if(sessionFactory ==  null){
			sessionFactory = new Configuration()
				.configure("resources/hibernate.cfg.xml")
				.buildSessionFactory();
		
		}
		
		/*else {
			sessionFactory.close();
			
			sessionFactory = new Configuration()
					.configure("resources/hibernate.cfg.xml")
					.buildSessionFactory();
					}*/
		// TODO Auto-generated method stub
		return sessionFactory.openSession();
	}

	public static void close(Session session) {
		session.close();
		
		// TODO Auto-generated method stub
		
	}
	
	

}
