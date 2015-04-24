package api;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author marco
 */
public class HibernateUtil {
    
    public static SessionFactory getSessionFactory() {
    	
    	Configuration configuration = new Configuration().configure();
    	
    	StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
    	
    	applySettings(configuration.getProperties());
    	
    	SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }
}
