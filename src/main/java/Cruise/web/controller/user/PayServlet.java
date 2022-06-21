package Cruise.web.controller.user;

import Cruise.model.UserDAO;
import Cruise.model.UserRequestDAO;
import Cruise.model.entity.User;
import Cruise.model.entity.UserRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PayServlet", value = "/pay")
public class PayServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String cruise = req.getParameter("cruise");
        int price = Integer.parseInt(req.getParameter("price"));
        int people = Integer.parseInt(req.getParameter("people"));

        int total = price * people;

        if (total > UserDAO.findUserByLogin(login).getAccount()){
            resp.sendRedirect("/Cruise_Liner/topup");
        }
        else {
            int updatedAccount = UserDAO.findUserByLogin(login).getAccount() - total;
            UserDAO.updateUserAccount(login, updatedAccount);
            UserRequestDAO.updateUserRequest(login, cruise, people, UserRequest.Status.PAID);
            resp.sendRedirect("/Cruise_Liner/requests");
        }
    }
}
