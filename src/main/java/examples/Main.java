package examples;

import dao.entities.Client;
import dao.implementations.MysqlClientDAO;
import dao.implementations.MysqlDishDAO;
import dao.entities.Dish;

public class Main {
    public static void main(String[] args) {

        MysqlDishDAO mysqlDishDao = new MysqlDishDAO();
        MysqlClientDAO mysqlClientDAO = new MysqlClientDAO();

        Dish potato = new Dish(8, "Potato", 20, 20.5, 200);

//        Dish dish = mysqlDishDao.reedByKey(2);
//        List<Dish> all = mysqlDishDao.getAll();
//        Dish dish1 = mysqlDishDao.create(potato);
//        boolean update = mysqlDishDao.update(potato);
//        boolean delete = mysqlDishDao.delete(8);
        Client client = mysqlClientDAO.reedByKey("+380997472365");

        System.out.println(client);

    }
}
