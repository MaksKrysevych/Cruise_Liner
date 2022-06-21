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
        LinerDAO linerDAO = new LinerDAO();
        Liner liner = null;

        liner = linerDAO.findLinerByName("Neptune");

        assertEquals("Neptune", liner.getName());
    }

    @Test
    public void testGetAllLiners() {
        LinerDAO linerDAO = new LinerDAO();
        List<Liner> list = new ArrayList();

        list = linerDAO.getAllLiners();

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
        linerDAO.updateLiner("1", "2022", 200, 350, "standard",100,100,100,100, "1");

        assertEquals("1", linerDAO.findLinerByName("1").getName());
    }


}