package examples;

import dao.MysqlDishDao;
import dao.entities.Dish;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        MysqlDishDao mysqlDishDao = new MysqlDishDao();

        Dish potato = new Dish(0, "Potato", 15, 20.5, 200);

        Dish dish = mysqlDishDao.reedByKey(2);
        List<Dish> all = mysqlDishDao.getAll();
        Dish dish1 = mysqlDishDao.create(potato);
        boolean update = mysqlDishDao.update(potato);
    }
}
