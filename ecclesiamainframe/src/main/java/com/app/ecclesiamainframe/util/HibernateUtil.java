/**
 * 
 */
package com.app.ecclesiamainframe.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.app.ecclesiamainframe.entity.Members;

/**
 * @author Harry
 *
 */
import org.hibernate.cfg.Environment;


public class HibernateUtil {
   private static StandardServiceRegistry registry;
   private static SessionFactory sessionFactory;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory == null) {
         try {
        	 
        	 // Create StandardServiceRegistry
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

            //Configuration properties
            Map<String, Object> settings = new HashMap<>();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/ecclesia_mainframe_db?useSSL=false");
            settings.put(Environment.USER, "devteam");
            settings.put(Environment.PASS, "softmysql2019");
            settings.put(Environment.HBM2DDL_AUTO, "validate");
            settings.put(Environment.SHOW_SQL, true);
            
            registryBuilder.applySettings(settings);
            registry = registryBuilder.build();
            
            // Create MetadataSources
            MetadataSources sources = new MetadataSources(registry);
            sources.addAnnotatedClass(Members.class);
            
            // Create Metadata
            Metadata metadata = sources.getMetadataBuilder().build();
            
            // Create SessionFactory
            sessionFactory = metadata.getSessionFactoryBuilder().build();
         } catch (Exception e) {
            if (registry != null) {
               StandardServiceRegistryBuilder.destroy(registry);
            }
            e.printStackTrace();
         }
      }
      return sessionFactory;
   }
   
 //Utility method to return SessionFactory
   public static void shutdown() {
      if (registry != null) {
         StandardServiceRegistryBuilder.destroy(registry);
      }
   }
}