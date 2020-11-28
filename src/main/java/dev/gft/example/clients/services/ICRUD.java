package dev.gft.example.clients.services;

import java.util.List;

public interface ICRUD<T, I> {

    T findOneById(I id);

    List<T> findAll();

    T createOne(T newRegistry);

    T updateOne(T updatedRegistry);

    void deleteOne(I id);
}
