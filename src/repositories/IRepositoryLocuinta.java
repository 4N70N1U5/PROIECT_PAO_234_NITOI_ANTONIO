package repositories;

import java.util.ArrayList;

public interface IRepositoryLocuinta<T> extends IRepositoryGeneric<T> {
    ArrayList<T> getAllByForeignId(int foreignId);
}
