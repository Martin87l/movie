package org.example.repositories;

import org.example.dao.Movie;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MovieRepository implements BasicRepository<Movie, Long>{
    private final Session session;
    private final Transaction transaction;

    public MovieRepository(Session session){
        this.session = session;
        this.transaction = session.getTransaction();
    }


    @Override
    public Movie createOrUpdate(Movie entity) {
        try {
            session.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Movie getById(Long id) {
        return session.find(Movie.class, id);
    }

    @Override
    public void deleteById(Long id) {
        try {
            Movie movie = session.find(Movie.class, id);
            session.remove(movie);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Movie> getAll() {
        String getAll = "select m from Movie m";
        Query<Movie> findAll = session.createQuery(getAll, Movie.class);
        return  findAll.getResultList();
    }

    public List<Movie> findByTitle(String title) {
        String getAllByTitle = "select m from Movie m where m.title = :title";
        Query<Movie> findAByTitle = session.createQuery(getAllByTitle, Movie.class);
        findAByTitle.setParameter("title", "%".concat(title).concat("%"));
        return findAByTitle.getResultList();
    }

    List<Movie> findByActorName(String actorName) {
        String getAllByActor = "select m from Movie m left join fetch m.actors actors where actors.name = :name";
        Query<Movie> findAllByActor = session.createQuery(getAllByActor, Movie.class);
        findAllByActor.setParameter("name", actorName);
        return findAllByActor.getResultList();
    }


}
