package org.example;

import org.example.config.HibernateUtils;
import org.example.repositories.ActorRepository;
import org.example.repositories.GenreRepository;
import org.example.repositories.MovieRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        ActorRepository actorRepository = new ActorRepository(session);
        MovieRepository movieRepository = new MovieRepository(session);
        GenreRepository genreRepository = new GenreRepository(session);
        System.out.println(genreRepository.getAll());
        System.out.println(actorRepository.getAll());
        System.out.println(movieRepository.findByTitle(""));
    }
}