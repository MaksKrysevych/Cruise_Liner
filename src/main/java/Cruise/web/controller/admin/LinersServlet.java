package Cruise.web.controller.admin;

import Cruise.model.LinerDAO;
import Cruise.model.UserRequestDAO;
import Cruise.model.entity.Liner;
import Cruise.web.Path;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LinersServlet", value = Path.LINERS)
public class LinersServlet extends HttpServlet {
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

        List<Liner> liners = LinerDAO.getSomeLiners(currentPage, recordsPerPage);

        req.setAttribute("liners", liners);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/liners.jsp");
        requestDispatcher.forward(req, resp);
    }
}
