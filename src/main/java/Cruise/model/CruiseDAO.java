package Cruise.model;

import Cruise.model.entity.Cruise;
import Cruise.database.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CruiseDAO {
    public static final String SQL_GET_CRUISE_BY_DAYS = "SELECT * FROM cruise WHERE days = ?";
    public static final String SQL_GET_CRUISE_BY_NAME = "SELECT * FROM cruise WHERE name = ?";
    public static final String SQL_GET_CRUISE_BY_DATE = "SELECT * FROM cruise WHERE start_day = (?) && finish_day = (?)";
    public static final String SQL_GET_ALL_CRUISES = "SELECT * FROM cruise";
    public static final String SQL_ADD_CRUISE = "INSERT INTO cruise(name, regions, liner, start_day, finish_day, from_port, to_port, days, description) VALUES ((?), (?), (?), (?), (?), (?), (?), (?), (?))";
    public static final String SQL_UPDATE_CRUISE = "UPDATE cruise SET name = (?), regions = (?), liner = (?), start_day = (?), finish_day = (?), from_port = (?), to_port = (?), days = (?), description = (?) WHERE name = (?)";

    private static final String CRUISE_ID = "cruise_id";
    private static final String NAME = "name";
    private static final String REGIONS = "regions";
    private static final String LINER = "liner";
    private static final String START_DAY = "start_day";
    private static final String FINISH_DAY = "finish_day";
    private static final String FROM_PORT = "from_port";
    private static final String TO_PORT = "to_port";
    private static final String DAYS = "days";
    private static final String DESCRIPTION = "description";

    /**
     * @param name User identifier
     * @return Cruise entity or null if wasn't found
     */
    public static Cruise findCruiseByName(String name) {
        Cruise cruise = null;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_CRUISE_BY_NAME)) {
            pst.setString(1, name);
            try (ResultSet rs = pst.executeQuery()) {
                if(rs.next())
                    cruise = mapResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cruise;
    }

    /**
     * @param days User identifier
     * @return Cruise entity or null if wasn't found
     */
    public static Cruise findCruiseByDays(int days) {
        Cruise cruise = null;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_CRUISE_BY_DAYS)) {
            pst.setInt(1, days);
            try (ResultSet rs = pst.executeQuery()) {
                if(rs.next())
                    cruise = mapResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cruise;
    }

    /**
     * params startDay, finishDay  Cruise's email
     * @return Cruise entity or null if wasn't found
     */
    public static Cruise findCruiseByDate(Date startDay, Date finishDay) {
        Cruise cruise = null;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_GET_CRUISE_BY_DATE)) {
            ps.setDate(1, startDay);
            ps.setDate(2, finishDay);

            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next())
                    cruise = mapResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cruise;
    }

    /**
     * Returns list of all users
     *
     * @return List of Cruise entities. If any present returns empty list.
     */
    public static List<Cruise> getAllCruises() {
        List<Cruise> cruises = new ArrayList<>();
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_CRUISES)) {
            try(ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    cruises.add(mapResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cruises;
    }

    /**
     * Adding user to DB with given parameters
     * @return true if Cruise was successfully added, false otherwise
     */
    public static boolean addCruise(String name, String regions, String liner, Date startDay, Date finishDay,
                                  String fromPort, String toPort, int days, String description) {
        boolean result = false;
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_ADD_CRUISE)) {
            ps.setString(1, name);
            ps.setString(2, regions);
            ps.setString(3, liner);
            ps.setDate(4, startDay);
            ps.setDate(5, finishDay);
            ps.setString(6, fromPort);
            ps.setString(7, toPort);
            ps.setInt(8, days);
            ps.setString(9, description);

            result = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static void updateCruise(String name, String regions, String liner, Date startDay, Date finishDay,
                                    String fromPort, String toPort, int days, String description){
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE_CRUISE)) {
            ps.setString(1, name);
            ps.setString(2, regions);
            ps.setString(3, liner);
            ps.setDate(4, startDay);
            ps.setDate(5, finishDay);
            ps.setString(6, fromPort);
            ps.setString(7, toPort);
            ps.setInt(8, days);
            ps.setString(9, description);
            ps.setString(10, name);



            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Extracts Cruise from the result set row.
     */
    private static Cruise mapResultSet(ResultSet rs) {
        Cruise cruise = null;
        try {
            cruise = new Cruise();
            cruise.setName(rs.getString(NAME));
            cruise.setRegions(rs.getString(REGIONS));
            cruise.setLiner(rs.getString(LINER));
            cruise.setStart_day(rs.getDate(START_DAY));
            cruise.setFinish_day(rs.getDate(FINISH_DAY));
            cruise.setFrom_port(rs.getString(FROM_PORT));
            cruise.setTo_port(rs.getString(TO_PORT));
            cruise.setDays(rs.getInt(DAYS));
            cruise.setDescription(rs.getString(DESCRIPTION));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return cruise;
    }
}
