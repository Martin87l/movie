package org.example.repositories;

import org.example.dao.Actor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ActorRepository implements BasicRepository<Actor, Long>{
    private final Session session;
    private final Transaction transaction;

    public ActorRepository(Session session) {
        this.session = session;
        this.transaction = session.getTransaction();
    }

    @Override
    public Actor createOrUpdate(Actor entity) {
        try {
            session.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Actor getById(Long id) {
        return session.find(Actor.class, id);
    }

    @Override
    public void deleteById(Long id) {
        try {
            session.remove(getById(id));
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public List<Actor> getAll() {
        String getAll = "select actor from Actor actor";
        Query<Actor> actorQuery = session.createQuery(getAll, Actor.class);
        return actorQuery.getResultList();
    }

    public List<Actor> findAllAfterYear(Integer year){
        String findAllAfterYear = "select actor from Actor actor where actor.yearOfBirth >= :year";
        Query<Actor> actorQuery = session.createQuery(findAllAfterYear, Actor.class);
        actorQuery.setParameter("year", year);
        return actorQuery.getResultList();
    }

    public List<Actor> findByEndName(String ends) {
        String findByEndName = "select actor from Actor actor where actor.name like ?1";
        Query<Actor> actorQuery = session.createQuery(findByEndName, Actor.class);
        actorQuery.setParameter(1, "%".concat(ends));
        return actorQuery.getResultList();
    }
}
