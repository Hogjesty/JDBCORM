package dao.implementations;

import dao.AbstractDAO;
import dao.entities.Client;
import dao.entities.Dish;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlClientDAO extends AbstractDAO<Client, String, Integer> {
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO `restaurant`.`clients` (`name`, `phone_num`, `age`) " +
                "VALUE (?, ?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE `restaurant`.`clients`" +
                "SET `name` = ?, `phone_num` = ?, `age` = ? " +
                "WHERE `id` = ?;";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM" +
                " `restaurant`.`clients`";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM `restaurant`.`clients`" +
                "WHERE `id` = ?;";
    }

    @Override
    protected String getSelectByKeyQuery() {
        return "SELECT * FROM `restaurant`.`clients` " +
                "WHERE `phone_num` = ?;";
    }

    @Override
    protected void fillCreateStatement(PreparedStatement statement, Client obj) {
        try {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getPhoneNum());
            statement.setInt(3, obj.getAge());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void fillUpdateStatement(PreparedStatement statement, Client obj) {
        try {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getPhoneNum());
            statement.setInt(3, obj.getAge());
            statement.setInt(4, obj.getId());
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
    protected Client createObject(ResultSet resultSet) {
        Client client = null;
        try {
            if (resultSet.next()) {
                client = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getDate(5),
                        resultSet.getDate(6)
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return client;
    }
}
