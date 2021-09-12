package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<P, T, V> implements GenericDAO<P, T, V> {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";

    protected abstract String getInsertQuery();
    protected abstract String getSelectQuery();
    protected abstract String getSelectByKeyQuery();

    protected abstract void fillStatement(PreparedStatement prstm, P obj);

    protected abstract P createObject(ResultSet rs);


    @Override
    public P create(P obj) {
        String INSERT_QUERY = getInsertQuery();
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement prstm = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            fillStatement(prstm, obj);
            prstm.executeUpdate();
            ResultSet resultSet = prstm.getGeneratedKeys();
//            if (resultSet.next()) {
//                dish.setId(resultSet.getInt(1));
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public P reedByKey(T key) {
        String SELECT_BY_ID = getSelectByKeyQuery();
        P object = null;
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement prstm = con.prepareStatement(SELECT_BY_ID)) {
            prstm.setString(1, key.toString()); //todo refactor
            ResultSet resultSet = prstm.executeQuery();
            object = createObject(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return object;
    }

    @Override
    public boolean update(P obj) {
        return false;
    }

    @Override
    public boolean delete(V id) {
        return false;
    }

    @Override
    public List<P> getAll() {
        List<P> list = new ArrayList<>();
        String SELECT_QUERY = getSelectQuery();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_QUERY)) {
            while (rs.next()) {
                list.add(createObject(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
