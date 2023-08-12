package org.example.repositories;

import org.example.dao.Genre;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class GenreRepository implements BasicRepository<Genre, Long> {
    private final Session session;
    private final Transaction transaction;

    public GenreRepository(Session session) {
        this.session = session;
        this.transaction = session.getTransaction();
    }

    @Override
    public Genre createOrUpdate(Genre genre){
        try {
            session.persist(genre);
            transaction.commit();
            return genre;
        } catch (Exception e){
            transaction.rollback();
        }
        return null;
    }

    @Override
    public void deleteById(Long id){
        String hql = "delete from Genre genre where genre.genreId=:id";
        Query<Genre> deleteQuery = session.createQuery(hql, Genre.class);
        deleteQuery.setParameter("id", id);
        deleteQuery.executeUpdate();
        transaction.commit();
    }

    @Override
    public Genre getById(Long id){
        Genre genre = session.get(Genre.class, id);
        return genre;
    }
    public Genre findByName(String name){
        String hql = "select genre from Genre genre where UPPER(genre.name) like UPPER(:name)";
        Query<Genre> getByName = session.createQuery(hql, Genre.class);
        getByName.setParameter("name", name);
        return getByName.getSingleResult();
    }

    @Override
    public List<Genre> getAll(){
        String hql = "select genre from Genre genre";
        Query<Genre> findAll = session.createQuery(hql, Genre.class);
        return findAll.getResultList();
    }
}
