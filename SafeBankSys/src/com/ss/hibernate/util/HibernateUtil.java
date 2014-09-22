/**
 * 
 */
package com.ss.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.ss.exception.ApplicationException;


/**
 * @author Banu Prakash
 * © 2011 MindTree Limited
 * 
 * HibernateUtil helper class that takes care of 
 * startup and makes accessing 
 * the org.hibernate.SessionFactory more convenient.
 */
public class HibernateUtil {
	
	 private static SessionFactory sessionFactory = null;
	    private static ServiceRegistry serviceRegistry;

	    /**
	     * @return
	     * @throws ApplicationException
	     */
	    private static SessionFactory buildSessionFactory() throws ApplicationException{
	        try {
	            // Create the SessionFactory from hibernate.cfg.xml
	        	 Configuration configuration = new Configuration();
	        	    configuration.configure();
	        	    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
	        	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	        	    return sessionFactory;
	        }
	        catch (Throwable ex) {
 	            throw new ApplicationException("Initial SessionFactory creation failed.",ex);
	        }
	    }
	    
	    /**
	     * @return
	     * @throws ApplicationException
	     */
	    public static SessionFactory getSessionFactory() throws ApplicationException{
	        if( sessionFactory == null) {
	        	sessionFactory = buildSessionFactory();
	        }
	    	return sessionFactory;
	    }
}
