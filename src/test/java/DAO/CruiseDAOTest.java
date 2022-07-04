package DAO;

import Cruise.model.CruiseDAO;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CruiseDAOTest extends TestCase {

    @Test
    public void testFindCruiseByName() {
        CruiseDAO cruiseDAO = new CruiseDAO();
        assertEquals("Ensenada Cruise", cruiseDAO.findCruiseByName("Ensenada Cruise").getName());
    }

    @Test
    public void testFindCruiseByDays() {
        CruiseDAO cruiseDAO = new CruiseDAO();
        assertEquals(4, cruiseDAO.findCruiseByDays(4).getDays());
    }

    @Test
    public void testGetSomeCruises() {
        CruiseDAO cruiseDAO = new CruiseDAO();
        List list = cruiseDAO.getSomeCruises(1, 2);
        assertEquals(2, list.size());
    }

    @Test
    public void testAddCruise() {
        CruiseDAO cruiseDAO = new CruiseDAO();

        assertEquals(true, cruiseDAO.addCruise("2","2", "Neptune", Date.valueOf("2022-12-12"), Date.valueOf("2022-12-15"), "2", "2", 3, "2dhbg"));
    }

    @Test
    public void testUpdateCruise() {
        CruiseDAO cruiseDAO = new CruiseDAO();
        cruiseDAO.updateCruise("neew","3", "Neptune", Date.valueOf("2022-12-02"), Date.valueOf("2022-12-15"), "1", "3", 13, "2deretythbg", "3");
        assertEquals("2deretythbg", cruiseDAO.findCruiseByName("neew").getDescription());
    }
}