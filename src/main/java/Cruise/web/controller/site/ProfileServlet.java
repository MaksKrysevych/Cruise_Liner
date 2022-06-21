package Cruise.web.controller.site;

import Cruise.model.UserDAO;
import Cruise.model.entity.User;
import Cruise.web.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ProfileServlet", value = Path.PROFILE_PATH)
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");

        User newUser = UserDAO.findUserByLogin(user.getLogin());

        request.setAttribute("user", newUser);
        RequestDispatcher  requestDispatcher = getServletContext().getRequestDispatcher("/profile.jsp");
        requestDispatcher.forward(request, response);
    }
}
