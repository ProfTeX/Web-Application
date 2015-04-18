package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class TagAccess {

	private SessionFactory sf = new HibernateUtil(new Tag()).getSessionFactory();
	private Session session = null;
	private Transaction transaction = null;
	
	public Boolean saveOrUpdateTag(Tag tag){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();

			session.saveOrUpdate(tag);
			
			transaction.commit();
			return true;
		}
		catch(Exception e)
		{
			if (transaction != null) transaction.rollback();
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public Tag getTag(){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();

			Tag tag = (Tag)session.get(Tag.class, 1);
			
			transaction.commit();
			return tag;
		}
		catch(Exception e)
		{
			if (transaction != null) transaction.rollback();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
}