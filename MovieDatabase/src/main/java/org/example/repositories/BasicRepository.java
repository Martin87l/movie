package org.example.repositories;

import java.util.List;

public interface BasicRepository<E, I> {
    E createOrUpdate(E entity);
    E getById(I id);
    void deleteById(I id);
    List<E> getAll();
}
