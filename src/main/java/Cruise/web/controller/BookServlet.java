package Cruise.web.controller;

import Cruise.model.UserRequestDAO;
import Cruise.model.entity.UserRequest;
import Cruise.web.Path;

import Cruise.Constants;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "BookServlet", value = Path.BOOK_PATH)
public class BookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/book.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String cruise = request.getParameter("cruise");
        String people = request.getParameter("people");
        HashMap<String, String> viewAttributes = new HashMap<>();
        viewAttributes.put("login", login);


        boolean userRequest = UserRequestDAO.addUserRequest(login, cruise, Date.valueOf("2022-12-02"), Integer.parseInt(people), UserRequest.Status.CREATED);
        if (!userRequest){
            viewAttributes.put("error", Constants.OTHER_ERROR);
            passErrorToView(request, response, viewAttributes);
        }
        else {
            response.sendRedirect(Path.CATALOG_PATH);
        }

    }
    private void passErrorToView(HttpServletRequest request, HttpServletResponse response, Map<String, String> viewAttributes) throws ServletException, IOException {
        for(Map.Entry<String, String> entry : viewAttributes.entrySet())
            request.setAttribute(entry.getKey(), entry.getValue());
        request.getRequestDispatcher("/book.jsp").forward(request, response);
    }
}
