package dao;

import examples.Dish;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlDishDao extends AbstractDAO<Dish, Integer, Integer> {
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO `dishes` " +
                "(`name`, `cooking_time(mins)`, `cost`, `weight(grams)`) " +
                "VALUE (?, ?, ?, ?);";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM `dishes`";
    }

    @Override
    protected void fillStatement(PreparedStatement prstm, Dish dish) {
        try {
            prstm.setString(1, dish.getName());
            prstm.setInt(2, dish.getCookingTime());
            prstm.setDouble(3, dish.getCost());
            prstm.setInt(4, dish.getWeight());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected Dish createObject(ResultSet rs) {
        Dish dish = null;
        try {
            dish = new Dish(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt(3),
                    rs.getDouble("cost"),
                    rs.getInt(5)
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dish;
    }
}
