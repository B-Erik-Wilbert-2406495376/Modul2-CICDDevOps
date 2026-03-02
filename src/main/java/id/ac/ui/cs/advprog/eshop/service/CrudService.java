package id.ac.ui.cs.advprog.eshop.service;

import java.util.Iterator;
import java.util.List;

public interface CrudService<T, ID> {
    T create(T entity);
    T update(ID id, T entity);
    boolean delete(ID id);
    T findById(ID id);
    List<T> findAll();
}
