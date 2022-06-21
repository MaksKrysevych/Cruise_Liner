package Cruise.web.controller.site;

import Cruise.model.CruiseDAO;
import Cruise.model.entity.Cruise;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DetailsServlet", value = "/details")
public class DetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cruiseName = req.getParameter("cruise");

        Cruise cruise = CruiseDAO.findCruiseByName(cruiseName);
        req.setAttribute("cruise", cruise);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/details.jsp");
        requestDispatcher.forward(req, resp);
    }
}
