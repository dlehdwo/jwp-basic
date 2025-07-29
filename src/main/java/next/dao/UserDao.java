package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import next.model.User;

public class UserDao {

    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public void insert(User user) {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.executeUpdate(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void update(User user) {
        String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
        jdbcTemplate.executeUpdate(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    public List<User> findAll() {
        RowMapper<List<User>> rm = rs -> {
            User user = null;
            List<User> queryResult = new ArrayList<>();
            while (rs.next()) {
                user = new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
                queryResult.add(user);
            }
            return queryResult;
        };

        String sql = "SELECT userId, password, name, email FROM USERS";
        return (List<User>) jdbcTemplate.executeQuery(sql, rm);
    }

    public User findByUserId(String userId) {
        RowMapper<User> rm = rs -> {
            User user = null;
            if (rs.next()) {
                return new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
            }
            return null;
        };

        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        return jdbcTemplate.executeQuery(sql, rm, userId);
     }
}
