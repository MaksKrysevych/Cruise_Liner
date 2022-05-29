package Cruise.web.controller;

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

        List<Cruise> cruises = CruiseDAO.getAllCruises();
        request.setAttribute("cruises", cruises);

        RequestDispatcher  requestDispatcher = getServletContext().getRequestDispatcher("/catalog.jsp");
        requestDispatcher.forward(request, response);
    }
}
