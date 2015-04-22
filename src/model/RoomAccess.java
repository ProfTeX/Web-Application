package model;

import java.util.Date;
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

			if (room.getId() == 0)
			{
				room.setCreatedAt(new Date());
			}
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
	
	public Boolean deleteRoom(Room room){
		try
		{
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();
			
			ChapterAccess ca = new ChapterAccess();
			List<Chapter> chapters = ca.getChaptersByRoomId(room.getId());
			
			for (Chapter chapter : chapters){
				ca.deleteChapter(chapter);
			}
			
			session.delete(room);
			
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