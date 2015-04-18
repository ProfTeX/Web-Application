package model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class RoomAccess {

	private SessionFactory sf = new HibernateUtil(new Room()).getSessionFactory();
	private Session session = null;
	private Transaction transaction = null;
	
	public Boolean saveOrUpdateRoom(Room room){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();

			session.saveOrUpdate(room);
			
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
	
	public Room getRoomById(Integer roomId){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();

			Room room = (Room) session.get(Room.class, roomId);
			
			transaction.commit();
			return room;
		}
		catch(Exception e)
		{
			if (transaction != null) transaction.rollback();
			System.out.println(e.getMessage());
			return null;
		}
	}
	

	
}