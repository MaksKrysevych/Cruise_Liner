package Cruise.web.controller;

import Cruise.model.UserRequestDAO;
import Cruise.model.entity.User;
import Cruise.model.entity.UserRequest;
import Cruise.web.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RequestsServlet", value = Path.REQUESTS_PATH)
public class RequestsServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Object name = session.getAttribute("user");

        User user = (User) name;
        if (user.getRole().isUser()){
            List<UserRequest> userRequests = new ArrayList<>();
            UserRequest userRequest = UserRequestDAO.findUserRequestByLogin(user.getLogin());
            userRequests.add(userRequest);

            request.setAttribute("userRequests", userRequests);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/requests.jsp");
            requestDispatcher.forward(request, response);
        }
        if (user.getRole().isAdmin()){
            List<UserRequest> userRequests = UserRequestDAO.getAllUsersRequest();

            request.setAttribute("userRequests", userRequests);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/requests.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}