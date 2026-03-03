package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;

import java.util.*;

public abstract class RepositoryImpl<T> implements Repository<T, String> {

    protected Map<String, T> storage = new HashMap<>();

    protected abstract String getId(T entity);

    @Override
    public T create(T entity) {
        storage.put(getId(entity), entity);
        return entity;
    }

    @Override
    public T update(String id, T entity) {
        return storage.replace(id, entity);
    }

    @Override
    public boolean delete(String id) {
        return storage.remove(id) != null;
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }

    @Override
    public Iterator<T> findAll() {
        return storage.values().iterator();
    }
}