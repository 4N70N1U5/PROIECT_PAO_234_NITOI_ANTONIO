package repositories;

import java.util.ArrayList;

public interface IRepositoryGeneric<T> {
    void add(T object);

    ArrayList<T> getAll();
    T getById(int id);

    void update (T object);

    void delete (T object);
}
