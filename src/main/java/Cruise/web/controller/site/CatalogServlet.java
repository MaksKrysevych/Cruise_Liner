package Cruise.web.controller.site;

import Cruise.model.CruiseDAO;
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


@WebServlet(name = "CatalogServlet", value = Path.CATALOG_PATH)
public class CatalogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int currentPage;
        int recordsPerPage;
        String sorting;

        if (request.getParameter("currentPage") == null && request.getParameter("recordsPerPage") == null && request.getParameter("sorting") == null){
            currentPage = 1;
            recordsPerPage = 2;
            sorting = "standard";
        }
        else {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
            sorting = request.getParameter("sorting");
        }

        if (sorting.equals("standard")) {
            List<Cruise> cruises = CruiseDAO.getSomeCruises(currentPage, recordsPerPage);
            request.setAttribute("cruises", cruises);
        }
        if (sorting.equals("byDays")){
            List<Cruise> cruises = CruiseDAO.getSomeCruisesByDays(currentPage, recordsPerPage);
            request.setAttribute("cruises", cruises);
        }
        if (sorting.equals("byDate")){
            List<Cruise> cruises = CruiseDAO.getSomeCruisesByDate(currentPage, recordsPerPage);
            request.setAttribute("cruises", cruises);
        }

        int rows = CruiseDAO.getAllCruises().size();
        int numberOfPages = rows / recordsPerPage;

        if (rows % recordsPerPage > 0) {
            numberOfPages++;
        }

        request.setAttribute("sorting", sorting);
        request.setAttribute("noOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        RequestDispatcher  requestDispatcher = getServletContext().getRequestDispatcher("/catalog.jsp");
        requestDispatcher.forward(request, response);
    }
}
