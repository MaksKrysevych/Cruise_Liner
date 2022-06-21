package Cruise.model;

import Cruise.model.entity.Liner;
import Cruise.database.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LinerDAO {

    public static final String SQL_GET_LINER_BY_NAME = "SELECT * FROM liner WHERE name = ?";
    public static final String SQL_GET_ALL_LINERS = "SELECT * FROM liner";
    public static final String SQL_GET_SOME_LINERS = "SELECT * FROM liner LIMIT ?, ?";
    public static final String SQL_ADD_LINER = "INSERT INTO liner(name ,built, room_count, max_people, class, room_inner, room_with_window, room_with_balcony, room_luxury) VALUES ((?), (?), (?), (?), (?), (?), (?), (?), (?))";
    private static final String SQL_UPDATE_LINER = "UPDATE liner SET name = (?), built = (?), room_count = (?), max_people = (?), class = (?), room_inner = (?), room_with_window = (?), room_with_balcony = (?), room_luxury = (?) WHERE name = (?)";
    private static final String SQL_DELETE_LINER = "DELETE FROM liner WHERE name = ?";

    public static final String NAME = "name";
    public static final String BUILT = "built";
    public static final String ROOM_COUNT = "room_count";
    public static final String MAX_PEOPLE = "max_people";
    public static final String TYPE = "class";
    public static final String ROOM_INNER = "room_inner";
    public static final String ROOM_WITH_WINDOW = "room_with_window";
    public static final String ROOM_WITH_BALCONY = "room_with_balcony";
    public static final String ROOM_LUXURY = "room_luxury";

    /**
     * @param name Liner's name
     * @return Liner entity or null if wasn't found
     */
    public static Liner findLinerByName(String name) {
        Liner liner = null;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_GET_LINER_BY_NAME)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next())
                    liner = mapResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return liner;
    }

    /**
     * Returns list of all liners
     *
     * @return List of Liner entities. If any present returns empty list.
     */
    public static List<Liner> getAllLiners() {
        List<Liner> liners = new ArrayList<>();
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_LINERS)) {
            try(ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    liners.add(mapResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return liners;
    }

    /**
     * Returns list of some liners
     *
     * @return List of Liner entities. If any present returns empty list.
     */
    public static List<Liner> getSomeLiners(int currentPage, int recordsPerPage) {
        List<Liner> liners = new ArrayList<>();

        int start = currentPage * recordsPerPage - recordsPerPage;

        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_SOME_LINERS)) {
            pst.setInt(1, start);
            pst.setInt(2, recordsPerPage);
            try(ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    liners.add(mapResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return liners;
    }

    /**
     * Adding liner to DB with given parameters
     * @return true if User was successfully added, false otherwise
     */
    public static boolean addLiner(String name, String built, int roomCount, int maxPeople, String type,
                                   int roomInner, int roomWithWindow, int roomWithBalcony, int roomLuxury) {
        boolean result = false;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_ADD_LINER)) {
             ps.setString(1, name);
             ps.setString(2, built);
             ps.setInt(3, roomCount);
             ps.setInt(4, maxPeople);
             ps.setString(5, type);
             ps.setInt(6, roomInner);
             ps.setInt(7, roomWithWindow);
             ps.setInt(8, roomWithBalcony);
             ps.setInt(9, roomLuxury);

             result = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Updating liner to DB with given parameters
     */
    public static boolean updateLiner(String newName, String built, int roomCount, int maxPeople, String type, int roomInner, int roomWithWindow, int roomWithBalcony, int roomLuxury, String name) {
        boolean result = false;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE_LINER)) {
            ps.setString(1, newName);
            ps.setString(2, built);
            ps.setInt(3, roomCount);
            ps.setInt(4, maxPeople);
            ps.setString(5, type);
            ps.setInt(6, roomInner);
            ps.setInt(7, roomWithWindow);
            ps.setInt(8, roomWithBalcony);
            ps.setInt(9, roomLuxury);
            ps.setString(10, name);

            result = ps.executeUpdate() > 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Delete liner from DB with given parameters
     */
    public static boolean deleteLiner(String liner) {
        boolean result = false;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_DELETE_LINER)) {
            pst.setString(1, liner);
            result = pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Extracts Liner from the result set row.
     */
    private static Liner mapResultSet(ResultSet rs) {
        Liner liner = null;
        try {
            liner = new Liner();
            liner.setName(rs.getString(NAME));
            liner.setBuilt(rs.getString(BUILT));
            liner.setRoomCount(rs.getInt(ROOM_COUNT));
            liner.setMax_people(rs.getInt(MAX_PEOPLE));
            liner.setType(Liner.Type.valueOf(rs.getString(TYPE).toUpperCase()));
            liner.setRoomInner(rs.getInt(ROOM_INNER));
            liner.setRoomWithWindow(rs.getInt(ROOM_WITH_WINDOW));
            liner.setRoomWithBalcony(rs.getInt(ROOM_WITH_BALCONY));
            liner.setRoomLuxury(rs.getInt(ROOM_LUXURY));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return liner;
    }
}
