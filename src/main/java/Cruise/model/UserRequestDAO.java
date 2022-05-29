package Cruise.model;

import Cruise.model.entity.UserRequest;
import Cruise.database.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class UserRequestDAO {
    public static final String SQL_GET_USER_BY_LOGIN = "SELECT * FROM user_request WHERE login = ?";
    public static final String SQL_GET_USER_BY_CRUISE = "SELECT * FROM user_request WHERE cruise_name = ?";
    public static final String SQL_GET_ALL_USERS_REQUEST = "SELECT * FROM user_request";
    public static final String SQL_ADD_USER = "INSERT INTO user_request(login, cruise_name, create_time, count_people, status) VALUES ((?), (?), (?), (?), (?))";
    private static final String SQL_UPDATE_REQUEST = "UPDATE user_request SET cruise_name = (?), count_people = (?), status = (?) WHERE login = (?)";

    public static final String LOGIN = "login";
    public static final String CRUISE_NAME = "cruise_name";
    public static final String CREATE_TIME = "create_time";
    public static final String COUNT_PEOPLE = "count_people";
    public static final String STATUS = "status";


    /**
     * @param login UserRequest identifier
     * @return UserRequest entity or null if wasn't found
     */
    public static UserRequest findUserRequestByLogin(String login) {
        UserRequest userRequest = null;
        try (Connection con = DBHelper.getInstance().getConnection();
            PreparedStatement pst = con.prepareStatement(SQL_GET_USER_BY_LOGIN)) {
            pst.setString(1, login);
            try (ResultSet rs = pst.executeQuery()) {
                if(rs.next())
                    userRequest = mapResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userRequest;
    }

    /**
     * @param cruise UserRequest identifier
     * @return UserRequest entity or null if wasn't found
     */
    public static UserRequest findUserRequestByCruise(String cruise) {
        UserRequest userRequest = null;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_USER_BY_CRUISE)) {
            pst.setString(1, cruise);
            try (ResultSet rs = pst.executeQuery()) {
                if(rs.next())
                    userRequest = mapResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userRequest;
    }

    /**
     * Returns list of all userRequest
     *
     * @return List of UserRequest entities. If any present returns empty list.
     */
    public static List<UserRequest> getAllUsersRequest() {
        List<UserRequest> userRequest = new ArrayList<>();
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_USERS_REQUEST)) {
            try(ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    userRequest.add(mapResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userRequest;

    }

    /**
     * Adding userRequest to DB with given parameters
     * @return true if UserRequest was successfully added, false otherwise
     */
    public static boolean addUserRequest(String login, String cruise_name, Date create_time, int count_people, UserRequest.Status status) {
        boolean result = false;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_ADD_USER)) {
            ps.setString(1, login);
            ps.setString(2, cruise_name);
            ps.setDate(3, create_time);
            ps.setInt(4, count_people);
            ps.setString(5, status.toString());

            result = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Updating userRequest to DB with given parameters
     */
    public static void updateUserRequest(String login, String cruiseName, int count_people, UserRequest.Status status) {
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_UPDATE_REQUEST)) {
            pst.setString(1, cruiseName);
            pst.setInt(2, count_people);
            pst.setString(3, String.valueOf(status));
            pst.setString(4, login);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Extracts UserRequest from the result set row.
     */
    private static UserRequest mapResultSet(ResultSet rs) {
        UserRequest userRequest = null;
        try {
            userRequest = new UserRequest();
            userRequest.setLogin(rs.getString(LOGIN));
            userRequest.setCruiseName(rs.getString(CRUISE_NAME));
            userRequest.setCreateTime(rs.getDate(CREATE_TIME));
            userRequest.setCountPeople(rs.getInt(COUNT_PEOPLE));
            userRequest.setStatus(UserRequest.Status.valueOf(rs.getString(STATUS).toUpperCase()));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return userRequest;
    }
}
