package Cruise.model;

import Cruise.model.entity.User;
import Cruise.database.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static final String SQL_GET_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    public static final String SQL_GET_ALL_USERS = "SELECT * FROM user";
    public static final String SQL_ADD_USER = "INSERT INTO user(login, password, role, first_name, last_name, phone_number, email, account, notification) VALUES ((?), (?), (?), (?), (?), (?), (?), (?), (?))";
    public static final String SQL_UPDATE_USER = "UPDATE user SET first_name = (?), last_name = (?), phone_number = (?), notification = (?) WHERE login = (?)";


    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String EMAIl = "email";
    private static final String ACCOUNT = "account";
    private static final String NOTIFICATION = "notification";

    /**
     * @param login User identifier
     * @return User entity or null if wasn't found
     */
    public static User findUserByLogin(String login) {
        User user = null;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_USER_BY_LOGIN)) {
            pst.setString(1, login);
            try (ResultSet rs = pst.executeQuery()) {
                if(rs.next())
                    user = mapResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    /**
     * Returns list of all users
     *
     * @return List of User entities. If any present returns empty list.
     */
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_USERS)) {
            try(ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    users.add(mapResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    /**
     * Adding user to DB with given parameters
     * @return true if User was successfully added, false otherwise
     */
    public static boolean addUser(String login, String password, String role, String first_name, String last_name,
                                  String phoneNumber, String email, int account, int notification) {
        boolean result = false;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_ADD_USER)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.setString(4, first_name);
            ps.setString(5, last_name);
            ps.setString(6, phoneNumber);
            ps.setString(7, email);
            ps.setInt(8, account);
            ps.setInt(9, notification);

            result = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Updating liner to DB with given parameters
     */
    public static void updateUser(String login, String first_name, String last_name, String phoneNumber, int notification) {
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE_USER)) {
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setString(3, phoneNumber);
            ps.setInt(4, notification);
            ps.setString(5, login);

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Extracts User from the result set row.
     */
    private static User mapResultSet(ResultSet rs) {
        User user = null;
        try {
            user = new User();
            user.setLogin(rs.getString(LOGIN));
            user.setPassword(rs.getString(PASSWORD));
            user.setRole(User.Role.valueOf(rs.getString(ROLE).toUpperCase()));
            user.setFirstName(rs.getString(FIRST_NAME));
            user.setLastName(rs.getString(LAST_NAME));
            user.setPhoneNumber(rs.getString(PHONE_NUMBER));
            user.setEmail(rs.getString(EMAIl));
            user.setAccount(rs.getInt(ACCOUNT));
            user.setNotification(rs.getInt(NOTIFICATION));

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return user;
    }
}
