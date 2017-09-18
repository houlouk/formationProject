package fr.acceis.jpa.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;


public abstract class HibernateDaoSessionFactory<T,KEY> implements Dao<T,KEY> {

	protected Class<T> classT;
	protected Session session;
  
	public HibernateDaoSessionFactory() {
	  	
	/*  if(HibernateUtil.getSessionFactory().getCurrentSession() != null) {
		  this.session = HibernateUtil.getSessionFactory().getCurrentSession();
	  }*/
		  //else {
		
	    this.session =HibernateUtil.getSession();
	    
	//}  
		
	    // TODO Auto-generated constructor stub
	  } 

	public Session getSession() {
		return session;
	}

	public void setSession(Session sf) {
		this.session = sf;
	}

	@Override
	public T add(T t) {
	    this.session =HibernateUtil.getSession();

		session.getTransaction().begin();
		T tLast = (T) session.merge(t);
		session.getTransaction().commit();
		HibernateUtil.close(this.session);

		return tLast;

	}

	@Override
	public int delete(KEY id) {
	    this.session =HibernateUtil.getSession();



		T t = session.load(classT,(Serializable)id);
		if (t == null) {
			return 0;
		}
		session.getTransaction().begin();
		session.delete(t);
		session.getTransaction().commit();
		
		HibernateUtil.close(this.session);

		return 1;
	}

	@Override
	public T get(KEY id) {
	    this.session =HibernateUtil.getSession();

		T t= this.session.get(classT, (Serializable) id);
		HibernateUtil.close(this.session);

		return t;


	}
	
	

	@Override
	public int update(KEY id, T t) {
	    this.session =HibernateUtil.getSession();

		session.getTransaction().begin();

		session.update(t);
		session.getTransaction().commit();

		HibernateUtil.close(this.session);

		return 0;
		//return res == null ? 0 : 1;

	}

	@Override
	public List<T> getAll() {
		
		
	    this.session =HibernateUtil.getSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(classT);
		
		
		List<T> res= session.createQuery(cq.select(cq.from(classT))).getResultList();
		System.out.println("test");
		HibernateUtil.close(this.session);

		
		return res;

	}

	
	
 
}
