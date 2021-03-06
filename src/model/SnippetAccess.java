package model;

import java.util.Date;

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

			if (snippet.getId() == 0)
			{
				snippet.setCreatedAt(new Date());
			}
			snippet.setUpdatedAt(new Date());
			
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
	
	public Snippet getSnippetById(Integer snippetId){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();

			Snippet snippet = (Snippet)session.get(Snippet.class, snippetId);
			
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
	
	public Boolean deleteSnippet(Snippet snippet){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();
			
			session.delete(snippet);
			
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
	
}