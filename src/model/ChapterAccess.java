package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class ChapterAccess {
	
	private SessionFactory sf = new HibernateUtil(new Chapter()).getSessionFactory();
	private Session session = null;
	private Transaction transaction = null;
	
	public Boolean saveOrUpdateChapter(Chapter chapter){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(chapter);
			
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
	
	public Chapter getChapter(){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();

			Chapter chapter = (Chapter)session.get(Chapter.class, 1);
			
			transaction.commit();
			return chapter;
			
		}
		catch(Exception e)
		{
			if (transaction != null) transaction.rollback();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
}