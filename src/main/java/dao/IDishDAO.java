package dao;

import dao.entities.Dish;

import java.util.List;

public interface IDishDAO {
    Dish create(Dish dish);
    Dish reedByKey(int key);
    boolean update(Dish dish);
    boolean delete(int id);

    List<Dish> getAll();
}
