package Cruise.web.controller.admin;

import Cruise.model.LinerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteLinerServlet", value = "/deleteLiner")
public class DeleteLinerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String liner = req.getParameter("liner");

        LinerDAO.deleteLiner(liner);
        resp.sendRedirect("/Cruise_Liner/liners");
    }
}
