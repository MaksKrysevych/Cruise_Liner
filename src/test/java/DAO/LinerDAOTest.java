package DAO;

import Cruise.model.LinerDAO;
import Cruise.model.entity.Liner;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LinerDAOTest extends TestCase {
    @Test
    public void testFindLinerByName() {
        Liner liner = LinerDAO.findLinerByName("Neptune");

        assertEquals("Neptune", liner.getName());
    }

    @Test
    public void testGetAllLiners() {
        List<Liner> list = LinerDAO.getAllLiners();

        assertEquals(3, list.size());
    }

    @Test
    public void testAddLiner() {
        LinerDAO linerDAO = new LinerDAO();
        assertEquals(true, linerDAO.addLiner("fdggh", "2000", 1, 1,"standard",1,1,1,1));
    }

    @Test
    public void testUpdateLiner(){
        LinerDAO linerDAO = new LinerDAO();
        linerDAO.updateLiner("1", "2022", 200, 350, "standard",100,100,100,100, "fdggh");

        assertEquals("1", linerDAO.findLinerByName("1").getName());
    }


}