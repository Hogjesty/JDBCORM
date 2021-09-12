package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<P, T, V> implements GenericDAO<P, T, V> {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";

    protected abstract String getInsertQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getSelectQuery();
    protected abstract String getDeleteQuery();
    protected abstract String getSelectByKeyQuery();

    protected abstract void fillCreateStatement(PreparedStatement statement, P obj);
    protected abstract void fillUpdateStatement(PreparedStatement statement, P obj);
    protected abstract void fillDeleteStatement(PreparedStatement statement, V id);
    protected abstract P createObject(ResultSet rs);


    @Override
    public P create(P obj) {
        String INSERT_QUERY = getInsertQuery();
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            fillCreateStatement(statement, obj);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
//            if (resultSet.next()) {
//                dish.setId(resultSet.getInt(1));
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; //todo refactor
    }

    @Override
    public P reedByKey(T key) {
        String SELECT_BY_ID = getSelectByKeyQuery();
        P object = null;
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = con.prepareStatement(SELECT_BY_ID)) {
            statement.setString(1, key.toString()); //todo refactor
            ResultSet resultSet = statement.executeQuery();
            object = createObject(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return object;
    }

    @Override
    public boolean update(P obj) {
        String UPDATE_QUERY = getUpdateQuery();
        int updateCount = 0;
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = con.prepareStatement(UPDATE_QUERY)) {
            fillUpdateStatement(statement, obj);
            statement.executeUpdate();
            updateCount = statement.getUpdateCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return updateCount > 0;
    }

    @Override
    public boolean delete(V id) {
        String DELETE_QUERY = getDeleteQuery();
        int updateCount = 0;
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = con.prepareStatement(DELETE_QUERY)) {
            fillDeleteStatement(statement, id);
            statement.executeUpdate();
            updateCount = statement.getUpdateCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateCount > 0;
    }

    @Override
    public List<P> getAll() {
        List<P> list = new ArrayList<>();
        String SELECT_QUERY = getSelectQuery();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_QUERY)) {
            while (true) {
                P obj = createObject(rs);
                if (obj == null) break;
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
