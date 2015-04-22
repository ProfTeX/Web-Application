package model;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


public class TagAccess {

	private SessionFactory sf = new HibernateUtil(new Tag()).getSessionFactory();
	private Session session = null;
	private Transaction transaction = null;
	
	public Boolean saveOrUpdateTag(Tag tag){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();
			
			if (tag.getId() == 0)
			{
				tag.setCreatedAt(new Date());
			}
			tag.setUpdatedAt(new Date());

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
	
	public Tag getTagByName(String tagName){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();

			Criteria criteria = session.createCriteria(Tag.class);
			criteria.add(Restrictions.eq("name", tagName));
			Tag tag = (Tag) criteria.list().get(0);
			
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
	
	public Boolean deleteTag(Tag tag){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();
			
			session.delete(tag);
			
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