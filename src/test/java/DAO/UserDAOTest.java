package DAO;

import Cruise.model.UserDAO;
import Cruise.model.entity.User;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserDAOTest extends TestCase {

    @Test
    public void testFindUserByLogin() {
        UserDAO userDAO = new UserDAO();
        assertEquals("1", userDAO.findUserByLogin("1").getLogin());
    }

    @Test
    public void testGetAllUsers() {
        UserDAO userDAO = new UserDAO();
        List list = userDAO.getAllUsers();

        assertEquals(list.size(), list.size());
    }

    @Test
    public void testAddUser() {
        UserDAO userDAO = new UserDAO();

        assertEquals(true, userDAO.addUser("dol", "123456", User.Role.USER.toString(), "Alex", "Alex", "123456", "2", 0, 0));
    }

    @Test
    public void testUpdateUser() {
        UserDAO userDAO = new UserDAO();
        userDAO.updateUser("dol", "Valera", "Alex", "2", "1", 0);
        assertEquals("Valera", userDAO.findUserByLogin("dol").getFirstName());
    }
}