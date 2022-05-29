package DAO;

import Cruise.model.CruiseDAO;
import junit.framework.TestCase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CruiseDAOTest extends TestCase {

    public void testFindCruiseByName() {
        CruiseDAO cruiseDAO = new CruiseDAO();
        assertEquals("1", cruiseDAO.findCruiseByName("1").getName());
    }

    public void testFindCruiseByDays() {
        CruiseDAO cruiseDAO = new CruiseDAO();
        assertEquals(4, cruiseDAO.findCruiseByDays(4).getDays());
    }

    public void testFindCruiseByDate() {
        CruiseDAO cruiseDAO = new CruiseDAO();
        Date date1 = Date.valueOf("2022-05-25");
        Date date2 = Date.valueOf("2022-05-29");

        assertEquals("1", cruiseDAO.findCruiseByDate(date1, date2).getName());
    }

    public void testGetAllCruises() {
        CruiseDAO cruiseDAO = new CruiseDAO();
        List list = new ArrayList<>();
        list = cruiseDAO.getAllCruises();
        assertEquals(1, list.size());
    }

    public void testAddCruise() {
        CruiseDAO cruiseDAO = new CruiseDAO();

        assertEquals(true, cruiseDAO.addCruise("2","2", "Neptune", Date.valueOf("2022-12-12"), Date.valueOf("2022-12-15"), "2", "2", 3, "2dhbg"));
    }

    public void testUpdateCruise() {
        CruiseDAO cruiseDAO = new CruiseDAO();
        cruiseDAO.updateCruise("2","3", "Neptune", Date.valueOf("2022-12-02"), Date.valueOf("2022-12-15"), "1", "3", 13, "2deretythbg");
        assertEquals("2deretythbg", cruiseDAO.findCruiseByName("2").getDescription());
    }
}