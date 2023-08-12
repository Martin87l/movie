package org.example.config;

import org.example.dao.Actor;
import org.example.dao.Genre;
import org.example.dao.Movie;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            Configuration configuration = new Configuration();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            configuration.addAnnotatedClass(Actor.class);
            configuration.addAnnotatedClass(Movie.class);
            configuration.addAnnotatedClass(Genre.class);
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
