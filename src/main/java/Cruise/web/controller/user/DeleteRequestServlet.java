package Cruise.web.controller.user;

import Cruise.model.UserRequestDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteRequest", value = "/deleteRequest")
public class DeleteRequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String cruise = req.getParameter("cruise");

        UserRequestDAO.deleteUserRequest(login, cruise);
        resp.sendRedirect("/Cruise_Liner/requests");
    }
}
