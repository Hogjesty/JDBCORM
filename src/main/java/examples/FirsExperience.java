package examples;

import dao.entities.Dish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FirsExperience {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String SELECT_QUERY = "SELECT * FROM `dishes`";
    private static final String INSERT_QUERY = "INSERT INTO `dishes` " +
            "(`name`, `cooking_time(mins)`, `cost`, `weight(grams)`) " +
            "VALUE (?, ?, ?, ?);";
    private static final String DELETE_QUERY = "DELETE FROM `restaurant`.`dishes`" +
            "WHERE `id` = ?;";
    private static final String UPDATE_QUERY = "UPDATE `restaurant`.`dishes` " +
            "SET `name` = ?, `cooking_time(mins)` = ?, `cost` = ?, `weight(grams)` = ? " +
            "WHERE `id` = ?;";


    public static void main(String[] args) {
//        Dish some = new Dish(0, "Samsa", 20, 50.5, 300);
//        createDish(some);

//        List<Dish> dishes = getDishes();
//        dishes.forEach(System.out::println);
//        System.out.println(some);

//        deleteDish(7);

//        Dish samsa = new Dish(0, "Samsa", 20, 51.0, 300);
//        updateDish(6, samsa);

    }


    public static List<Dish> getDishes() {
        List<Dish> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_QUERY)) {
            while (rs.next()) {
                list.add(new Dish(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt(3),
                        rs.getDouble("cost"),
                        rs.getInt(5)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void createDish(Dish dish) {

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement prstm = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            prstm.setString(1, dish.getName());
            prstm.setInt(2, dish.getCookingTime());
            prstm.setDouble(3, dish.getCost());
            prstm.setInt(4, dish.getWeight());
            prstm.executeUpdate();
            ResultSet resultSet = prstm.getGeneratedKeys();
            if (resultSet.next()) {
                dish.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteDish(int id) {
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = con.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateDish(Dish dish) {
        //todo refactor
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = con.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, dish.getName());
            statement.setInt(2, dish.getCookingTime());
            statement.setDouble(3, dish.getCost());
            statement.setInt(4, dish.getWeight());
            statement.setInt(5, dish.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //todo add method "get by id"
}
