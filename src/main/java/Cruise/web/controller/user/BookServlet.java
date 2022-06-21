package Cruise.web.controller.user;

import Cruise.model.CruiseDAO;
import Cruise.model.LinerDAO;
import Cruise.model.UserRequestDAO;
import Cruise.model.entity.User;
import Cruise.model.entity.UserRequest;
import Cruise.web.Path;
import Cruise.Constants;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "BookServlet", value = Path.BOOK_PATH)
public class BookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("user");
        req.setAttribute("cruise", req.getAttribute("cruise"));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/book.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String cruise = request.getParameter("cruise");
        int people = Integer.parseInt(request.getParameter("people"));
        String room = request.getParameter("room");
        int price = 0;

        HashMap<String, String> viewAttributes = new HashMap<>();
        viewAttributes.put("login", login);

        java.util.Date dateNow = new java.util.Date();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(dateNow);

        if (room.equals("inner")){
            price = LinerDAO.findLinerByName(CruiseDAO.findCruiseByName(cruise).getLiner()).getRoomInner() * people;
        }
        else if (room.equals("with window")){
            price = LinerDAO.findLinerByName(CruiseDAO.findCruiseByName(cruise).getLiner()).getRoomWithWindow() * people;
        }
        else if (room.equals("with balcony")){

            price = LinerDAO.findLinerByName(CruiseDAO.findCruiseByName(cruise).getLiner()).getRoomWithBalcony() * people;
        }
        else if (room.equals("luxury")){
            price = LinerDAO.findLinerByName(CruiseDAO.findCruiseByName(cruise).getLiner()).getRoomLuxury() * people;
        }
        
        boolean userRequest = UserRequestDAO.addUserRequest(login, cruise, price,java.sql.Date.valueOf(date), people, UserRequest.Status.CREATED);
        if (!userRequest){
            viewAttributes.put("error", Constants.REQUEST_EXISTS);
            passErrorToView(request, response, viewAttributes);
            return;
        }
        else {
            response.sendRedirect("/Cruise_Liner/catalog");
        }

    }
    private void passErrorToView(HttpServletRequest request, HttpServletResponse response, Map<String, String> viewAttributes) throws ServletException, IOException {
        for(Map.Entry<String, String> entry : viewAttributes.entrySet())
            request.setAttribute(entry.getKey(), entry.getValue());
        request.getRequestDispatcher("/book.jsp").forward(request, response);
    }
}
