package dao;

import java.util.List;

public interface GenericDAO<P, T, V> {
    P create(P obj);
    P reedByKey(T key);
    boolean update(P obj);
    boolean delete(V id);

    List<P> getAll();
}
