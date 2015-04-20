package model;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


public class UserAccess {

	private SessionFactory sf = new HibernateUtil(new User()).getSessionFactory();
	private Session session = null;
	private Transaction transaction = null;
	
	public Boolean saveOrUpdateUser(User user){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();
			
			for (Room room : user.getRooms()) {
				if (room.getId()==0)
				{
					room.setCreatedAt(new Date());
				}
			}
			
			session.saveOrUpdate(user);
			
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
	
	public User getUserByName(String userName){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();

			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("name", userName));
			User user = (User) criteria.list().get(0);
			
			transaction.commit();
			return user;
		}
		catch(Exception e)
		{
			if (transaction != null) transaction.rollback();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public User getUserByEmail(String email){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();

			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("email", email));
			User user = (User) criteria.list().get(0);
			
			transaction.commit();
			return user;
		}
		catch(Exception e)
		{
			if (transaction != null) transaction.rollback();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public User getUserByWhatEver(String columnName, String columnValue){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();

			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq(columnName, columnValue));
			User user = (User) criteria.list().get(0);
			
			transaction.commit();
			return user;
		}
		catch(Exception e)
		{
			if (transaction != null) transaction.rollback();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public Boolean deleteUser(User user){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();
			
			session.delete(user);
			
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