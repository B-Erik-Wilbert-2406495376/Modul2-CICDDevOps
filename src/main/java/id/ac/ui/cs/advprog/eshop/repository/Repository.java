package id.ac.ui.cs.advprog.eshop.repository;
import java.util.List;

public interface Repository<T, ID> {
    T create(T entity);
    T update(ID id, T entity);
    void delete(ID id);
    T findById(ID id);
    List<T> findAll();
}