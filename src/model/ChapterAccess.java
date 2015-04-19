package model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


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
	
	public List<Chapter> getChaptersByRoomId(Integer roomId){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();

			Criteria criteria = session.createCriteria(Chapter.class);
			criteria.add(Restrictions.eq("roomId", roomId));
			@SuppressWarnings("unchecked")
			List<Chapter> chapters = (List<Chapter>) criteria.list();
			
			transaction.commit();
			return chapters;
			
		}
		catch(Exception e)
		{
			if (transaction != null) transaction.rollback();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
}