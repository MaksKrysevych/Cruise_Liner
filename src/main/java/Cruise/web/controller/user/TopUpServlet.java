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
@WebServlet(name = "TopUpServlet", value = Path.TOPUP_PATH)
public class TopUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/topup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("user");

        User newUser = UserDAO.findUserByLogin(user.getLogin());

        int account;

        if (req.getParameter("account") == null){
            account = 0;
        }
        else {
            account = Integer.parseInt(req.getParameter("account"));
        }

        account = account + newUser.getAccount();
        UserDAO.updateUserAccount(user.getLogin(), account);

        resp.sendRedirect("/Cruise_Liner/profile");
    }
}
