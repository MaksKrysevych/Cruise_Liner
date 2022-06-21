package Cruise.web.controller.admin;

import Cruise.model.UserRequestDAO;
import Cruise.model.entity.UserRequest;
import Cruise.web.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateUserRequestServlet", value = Path.UPDATE_USER_REQUEST)
public class UpdateUserRequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String cruise = req.getParameter("cruise");
        int people = Integer.parseInt(req.getParameter("people"));
        String status = req.getParameter("status");

        UserRequestDAO.updateUserRequest(login, cruise, people, UserRequest.Status.valueOf(status.toUpperCase()));
        resp.sendRedirect("/Cruise_Liner/requests");
    }
}
