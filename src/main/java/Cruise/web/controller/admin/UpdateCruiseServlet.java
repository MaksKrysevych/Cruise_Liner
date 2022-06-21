package Cruise.web.controller.admin;

import Cruise.model.CruiseDAO;
import Cruise.model.LinerDAO;
import Cruise.model.entity.Cruise;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import Cruise.Constants;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UpdateCruiseServlet", value = "/updateCruise")
public class UpdateCruiseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cruiseName = req.getParameter("cruise");
        Cruise cruise = CruiseDAO.findCruiseByName(cruiseName);
        req.setAttribute("cruise", cruise);
        req.getRequestDispatcher("/updateCruise.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newCruise = req.getParameter("newCruise");
        String cruise = req.getParameter("cruise");
        String regions = req.getParameter("regions");
        String liner = req.getParameter("liner");
        Date startDay = Date.valueOf(req.getParameter("startDay"));
        Date finishDay = Date.valueOf(req.getParameter("finishDay"));
        String fromPort = req.getParameter("fromPort");
        String toPort = req.getParameter("toPort");
        int days = Integer.parseInt(req.getParameter("days"));
        String description = req.getParameter("description");
        HashMap<String, String> viewAttributes = new HashMap<>();


        if (LinerDAO.findLinerByName(liner) == null) {
            viewAttributes.put("error", Constants.REQUEST_EXISTS);
            passErrorToView(req, resp, viewAttributes);
            return;
        }
        else if(CruiseDAO.updateCruise(newCruise, regions, liner, startDay, finishDay, fromPort, toPort, days, description, cruise) != false) {
            resp.sendRedirect("/Cruise_Liner/cruises");
        }
        else {
            viewAttributes.put("error", Constants.CRUISE_EXIST);
            passErrorToView(req, resp, viewAttributes);
            return;
        }
    }

    private void passErrorToView(HttpServletRequest request, HttpServletResponse response, Map<String, String> viewAttributes) throws ServletException, IOException {
        for(Map.Entry<String, String> entry : viewAttributes.entrySet())
            request.setAttribute(entry.getKey(), entry.getValue());
        request.getRequestDispatcher("/updateCruise.jsp").forward(request, response);
    }
}
