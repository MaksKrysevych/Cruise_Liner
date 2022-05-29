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
    public static final String SQL_ADD_LINER = "INSERT INTO liner(name ,built, room_count, max_people, class, room_inner, room_with_window, room_with_balcony, room_luxury) VALUES ((?), (?), (?), (?), (?), (?), (?), (?), (?))";
    private static final String SQL_UPDATE_LINER = "UPDATE liner SET class = (?), room_inner = (?), room_with_window = (?), room_with_balcony = (?), room_luxury = (?) WHERE name = (?)";

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
    public static void updateLiner(String name, String type, int roomInner, int roomWithWindow, int roomWithBalcony, int roomLuxury) {
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE_LINER)) {
            ps.setString(1, type);
            ps.setInt(2, roomInner);
            ps.setInt(3, roomWithWindow);
            ps.setInt(4, roomWithBalcony);
            ps.setInt(5, roomLuxury);
            ps.setString(6, name);

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
