package DAO;

import Cruise.model.UserDAO;
import Cruise.model.entity.User;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class UserDAOTest extends TestCase {

    public void testFindUserByLogin() {
        UserDAO userDAO = new UserDAO();
        assertEquals("1", userDAO.findUserByLogin("1").getLogin());
    }

    public void testGetAllUsers() {
        UserDAO userDAO = new UserDAO();
        List list = userDAO.getAllUsers();

        assertEquals(1, list.size());
    }

    public void testAddUser() {
        UserDAO userDAO = new UserDAO();

        assertEquals(true, userDAO.addUser("dol", "123456", User.Role.USER.toString(), "Alex", "Alex", "123456", "2", 0, 0));
    }

    public void testUpdateUser() {
        UserDAO userDAO = new UserDAO();
        userDAO.updateUser("dol", "Valera", "Alex", "", "1", 0);
        assertEquals("Valera", userDAO.findUserByLogin("2").getFirstName());
    }
}