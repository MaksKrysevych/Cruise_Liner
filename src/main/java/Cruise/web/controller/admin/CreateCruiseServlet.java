package Cruise.web.controller.admin;

import Cruise.model.CruiseDAO;
import Cruise.web.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "CreateCruiseServlet", value = Path.CREATE_CRUISE)
public class CreateCruiseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/createCruise.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String regions = req.getParameter("regions");
        String liner = req.getParameter("liner");
        Date startDay = Date.valueOf(req.getParameter("startDay"));
        Date finishDay = Date.valueOf(req.getParameter("finishDay"));
        String fromPort = req.getParameter("fromPort");
        String toPort = req.getParameter("toPort");
        int days = Integer.parseInt(req.getParameter("days"));
        String description = req.getParameter("description");

        CruiseDAO.addCruise(name, regions, liner, startDay, finishDay, fromPort, toPort, days, description);

        resp.sendRedirect("/Cruise_Liner/cruises");
    }
}
