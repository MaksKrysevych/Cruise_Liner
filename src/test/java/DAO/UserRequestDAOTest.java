package DAO;

import Cruise.model.UserRequestDAO;
import Cruise.model.entity.UserRequest;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserRequestDAOTest extends TestCase {

    @Test
    public void testFindUserRequestByLogin() {
        UserRequestDAO userRequestDAO = new UserRequestDAO();

        assertEquals("user", userRequestDAO.findUserRequestByLogin("user").getLogin());
    }

    @Test
    public void testFindUserRequestByCruise(){
        List<UserRequest> userRequest = UserRequestDAO.findUserRequestByCruise("1");

        assertEquals(userRequest.size(), userRequest.size());
    }

    @Test
    public void testGetAllUsersRequest() {
        List list = UserRequestDAO.getAllUsersRequest();

        assertEquals(list.size(), list.size());
    }

    @Test
    public void testAddUserRequest() {
        UserRequestDAO.addUserRequest("1", "Ensenada Cruise", 500, Date.valueOf("2022-07-04"), 2, UserRequest.Status.AVAILABLE);
        assertEquals("1", UserRequestDAO.findUserRequestByLogin("1").getLogin());
    }

    @Test
    public void testUpdateUserRequest() {
        UserRequestDAO.updateUserRequest("1", "Ensenada Cruise", 2, UserRequest.Status.AVAILABLE);

        assertEquals("available", UserRequestDAO.findUserRequestByLogin("1").getStatus());
    }

    @Test
    public void testDeleteUserRequest() {
        assertEquals(true, UserRequestDAO.deleteUserRequest("1", "Ensenada Cruise"));
    }
}