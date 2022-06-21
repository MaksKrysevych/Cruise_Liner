package Cruise.web.controller.user;

import Cruise.model.UserDAO;
import Cruise.model.entity.User;
import Cruise.web.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Statement;

@WebServlet(name = "EditProfileServlet", value = Path.EDIT_PROFILE_PATH)
public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("user");
        req.setAttribute("user", user);
        req.getRequestDispatcher("/editProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("user");

        String first_name = req.getParameter("firstname");
        String last_name = req.getParameter("lastname");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phone");

        UserDAO.updateUser(user.getLogin(), first_name, last_name, email, phoneNumber, 0);

        resp.sendRedirect("/Cruise_Liner/profile");
    }
}
