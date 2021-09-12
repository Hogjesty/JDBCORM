package examples;

import dao.MysqlDishDao;
import dao.entities.Dish;

public class Main {
    public static void main(String[] args) {

        MysqlDishDao mysqlDishDao = new MysqlDishDao();


        Dish dish = mysqlDishDao.reedByKey(2);

    }
}
