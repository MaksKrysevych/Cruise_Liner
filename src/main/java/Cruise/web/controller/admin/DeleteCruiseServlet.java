package Cruise.web.controller.admin;

import Cruise.model.CruiseDAO;
import Cruise.model.UserRequestDAO;
import Cruise.web.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteCruiseServlet", value = "/deleteCruise")
public class DeleteCruiseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cruise = req.getParameter("cruise");

        CruiseDAO.deleteCruise(cruise);
        resp.sendRedirect("/Cruise_Liner/cruises");
    }
}
