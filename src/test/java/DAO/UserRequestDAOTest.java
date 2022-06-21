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

        assertEquals("1234", userRequestDAO.findUserRequestByLogin("1234").getLogin());
    }

    public void testFindUserRequestByCruise(){
        List<UserRequest> userRequest = UserRequestDAO.findUserRequestByCruise("1");

        assertEquals(1, userRequest.size());
    }

    public void testGetAllUsersRequest() {
        UserRequestDAO userRequestDAO = new UserRequestDAO();
        List list = new ArrayList<>();

        list = userRequestDAO.getAllUsersRequest();

        assertEquals(1, list.size());
    }

    public void testAddUserRequest() {
        UserRequestDAO userRequestDAO = new UserRequestDAO();

        assertEquals(true, userRequestDAO.addUserRequest("immortants", "2", 0,Date.valueOf("2022-12-02"), 2, UserRequest.Status.CREATED));
    }

    public void testUpdateUserRequest() {
        UserRequestDAO.updateUserRequest("immortants", "1", 2, UserRequest.Status.valueOf("Available"));
        List<UserRequest> userRequest = UserRequestDAO.findUserRequestByCruise("1");

        assertEquals("1", userRequest.size());
    }

    public void testDeleteUserRequest() {
        assertEquals("true", UserRequestDAO.deleteUserRequest("1234", "2"));
    }
}