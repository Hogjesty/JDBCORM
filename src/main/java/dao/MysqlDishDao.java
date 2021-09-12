package dao;

import dao.entities.Dish;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlDishDao extends AbstractDAO<Dish, Integer, Integer> {
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO `restaurant`.`dishes` " +
                "(`name`, `cooking_time(mins)`, `cost`, `weight(grams)`) " +
                "VALUE (?, ?, ?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE `restaurant`.`dishes` " +
                "SET `name` = ?, `cooking_time(mins)` = ?, `cost` = ?, `weight(grams)` = ? " +
                "WHERE `id` = ?;";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM" +
                " `restaurant`.`dishes`";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM `restaurant`.`dishes`" +
                "WHERE `id` = ?;";
    }

    @Override
    protected String getSelectByKeyQuery() {
        return "SELECT * FROM `restaurant`.`dishes` " +
                "WHERE `id` = ?;";
    }

    @Override
    protected void fillCreateStatement(PreparedStatement statement, Dish dish) {
        try {
            statement.setString(1, dish.getName());
            statement.setInt(2, dish.getCookingTime());
            statement.setDouble(3, dish.getCost());
            statement.setInt(4, dish.getWeight());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void fillUpdateStatement(PreparedStatement statement, Dish obj) {
        try {
            statement.setString(1, obj.getName());
            statement.setInt(2, obj.getCookingTime());
            statement.setDouble(3, obj.getCost());
            statement.setInt(4, obj.getWeight());
            statement.setInt(5, obj.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void fillDeleteStatement(PreparedStatement statement, Integer id) {
        try {
            statement.setInt(1, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected Dish createObject(ResultSet resultSet) {
        Dish dish = null;
        try {
            if (resultSet.next()) {
                dish = new Dish(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt(3),
                        resultSet.getDouble("cost"),
                        resultSet.getInt(5)
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dish;
    }
}
