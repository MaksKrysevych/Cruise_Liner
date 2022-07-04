package Cruise.web.controller.admin;

import Cruise.model.CruiseDAO;
import Cruise.model.UserRequestDAO;
import Cruise.model.entity.Cruise;
import Cruise.web.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CruisesServlet", value = Path.CRUISES)
public class CruisesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage;
        int recordsPerPage;

        if (req.getParameter("currentPage") == null && req.getParameter("recordsPerPage") == null){
            currentPage = 1;
            recordsPerPage = 2;
        }
        else {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
            recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
        }
        int rows = UserRequestDAO.getAllUsersRequest().size();
        int numberOfPages = rows / recordsPerPage;

        if (rows % recordsPerPage > 0) {
            numberOfPages++;
        }

        req.setAttribute("noOfPages", numberOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);

        List<Cruise> cruises = CruiseDAO.getSomeCruises(currentPage, recordsPerPage);

        req.setAttribute("cruises", cruises);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/cruises.jsp");
        requestDispatcher.forward(req, resp);
    }
}
