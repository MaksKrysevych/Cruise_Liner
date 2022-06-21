package Cruise.web.controller.admin;

import Cruise.model.LinerDAO;
import Cruise.model.entity.Liner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import Cruise.Constants;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UpdateLinerServlet", value = "/updateLiner")
public class UpdateLinerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String linerName = req.getParameter("liner");
        Liner liner = LinerDAO.findLinerByName(linerName);
        req.setAttribute("liner", liner);
        req.getRequestDispatcher("/updateLiner.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newLiner = req.getParameter("newLiner");
        String built = req.getParameter("built");
        int roomCount = Integer.parseInt(req.getParameter("roomCount"));
        int maxPeople = Integer.parseInt(req.getParameter("maxPeople"));
        String type = req.getParameter("type");
        int roomInner = Integer.parseInt(req.getParameter("roomInner"));
        int roomWithWindow = Integer.parseInt(req.getParameter("roomWithWindow"));
        int roomWithBalcony = Integer.parseInt(req.getParameter("roomWithBalcony"));
        int roomLuxury = Integer.parseInt(req.getParameter("roomLuxury"));
        String liner = req.getParameter("liner");

        LinerDAO.updateLiner(newLiner, built, roomCount, maxPeople, type.toUpperCase(), roomInner, roomWithWindow, roomWithBalcony, roomLuxury, liner);
        resp.sendRedirect("/Cruise_Liner/liners");
    }
}