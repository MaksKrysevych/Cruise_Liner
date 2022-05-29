package DAO;

import Cruise.model.UserRequestDAO;
import Cruise.model.entity.UserRequest;
import junit.framework.TestCase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserRequestDAOTest extends TestCase {

    public void testFindUserRequestByLogin() {
        UserRequestDAO userRequestDAO = new UserRequestDAO();

        assertEquals("immortants", userRequestDAO.findUserRequestByLogin("immortants").getLogin());
    }

    public void testFindUserRequestByCruise(){
        UserRequestDAO userRequestDAO = new UserRequestDAO();

        assertEquals("2", userRequestDAO.findUserRequestByCruise("2").getCruiseName());
    }

    public void testGetAllUsersRequest() {
        UserRequestDAO userRequestDAO = new UserRequestDAO();
        List list = new ArrayList<>();

        list = userRequestDAO.getAllUsersRequest();

        assertEquals(1, list.size());
    }

    public void testAddUserRequest() {
        UserRequestDAO userRequestDAO = new UserRequestDAO();

        assertEquals(true, userRequestDAO.addUserRequest("immortants", "2", Date.valueOf("2022-12-02"), 2, UserRequest.Status.CREATED));
    }

    public void testUpdateUserRequest() {
        UserRequestDAO userRequestDAO = new UserRequestDAO();
        userRequestDAO.updateUserRequest("immortants", "1", 2, UserRequest.Status.CREATED);

        assertEquals("1", userRequestDAO.findUserRequestByCruise("1").getCruiseName());
    }
}