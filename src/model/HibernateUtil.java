package model;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


@SuppressWarnings("deprecation")
public class HibernateUtil {
	
	private SessionFactory sessionFactory;
	
	public HibernateUtil(Object type){		
		try
		{
			sessionFactory = new AnnotationConfiguration()
							.addPackage("model")
							.addAnnotatedClass(type.getClass())
							.configure()
							.buildSessionFactory();
		}
		catch (RuntimeException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
			
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
}