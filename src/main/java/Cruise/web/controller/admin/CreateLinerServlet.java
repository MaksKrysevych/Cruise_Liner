package Cruise.web.controller.admin;

import Cruise.model.LinerDAO;
import Cruise.web.Path;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateLinerServlet", value = Path.CREATE_LINER)
public class CreateLinerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/createLiner.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String built = req.getParameter("built");
        int roomCount = Integer.parseInt(req.getParameter("roomCount"));
        int max_people = Integer.parseInt(req.getParameter("max_people"));
        String type = req.getParameter("type");
        int roomInner = Integer.parseInt(req.getParameter("roomInner"));
        int roomWithWindow = Integer.parseInt(req.getParameter("roomWithWindow"));
        int roomWithBalcony = Integer.parseInt(req.getParameter("roomWithBalcony"));
        int roomLuxury = Integer.parseInt(req.getParameter("roomLuxury"));

        LinerDAO.addLiner(name, built, roomCount, max_people, type, roomInner, roomWithWindow, roomWithBalcony, roomLuxury);

        resp.sendRedirect("/Cruise_Liner/cruises");
    }
}
