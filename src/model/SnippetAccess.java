package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class SnippetAccess {

	private SessionFactory sf = new HibernateUtil(new Snippet()).getSessionFactory();
	private Session session = null;
	private Transaction transaction = null;
	
	public Boolean saveOrUpdateSnippet(Snippet snippet){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();

			session.saveOrUpdate(snippet);
			
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
	
	public Snippet getSnippet(){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();

			Snippet snippet = (Snippet)session.get(Snippet.class, 1);
			
			transaction.commit();
			return snippet;
		}
		catch(Exception e)
		{
			if (transaction != null) transaction.rollback();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
}