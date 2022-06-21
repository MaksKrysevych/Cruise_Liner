package Cruise.web.controller.site;

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
        request.setAttribute("user", user);
        if (user.getRole().isUser()){
            List<UserRequest> userRequests = new ArrayList<>();
            while (true){
                List<UserRequest> allUserRequests = UserRequestDAO.getAllUsersRequest();
                for (UserRequest userRequest: allUserRequests) {
                    if (userRequest.getLogin().equals(user.getLogin()))
                        userRequests.add(userRequest);
                }
                break;
            }

            request.setAttribute("userRequests", userRequests);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/requests.jsp");
            requestDispatcher.forward(request, response);
        }
        if (user.getRole().isAdmin()){
            int currentPage;
            int recordsPerPage;

            if (request.getParameter("currentPage") == null && request.getParameter("recordsPerPage") == null){
                currentPage = 1;
                recordsPerPage = 2;
            }
            else {
                currentPage = Integer.parseInt(request.getParameter("currentPage"));
                recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
            }

            List<UserRequest> userRequests = UserRequestDAO.getSomeUsersRequest(currentPage, recordsPerPage);

            request.setAttribute("userRequests", userRequests);

            int rows = UserRequestDAO.getAllUsersRequest().size();
            int numberOfPages = rows / recordsPerPage;

            if (numberOfPages % recordsPerPage > 0) {
                numberOfPages++;
            }

            request.setAttribute("noOfPages", numberOfPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("recordsPerPage", recordsPerPage);

            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/requests.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}