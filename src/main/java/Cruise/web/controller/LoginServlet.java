package Cruise.web.controller;

import Cruise.Constants;
import Cruise.model.UserDAO;
import Cruise.model.entity.User;
import Cruise.web.Path;
import Cruise.web.utils.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static Cruise.web.Path.LOGIN_PATH;


/**
 * Login page controller
 * Retrieves credentials from request and validate them.
 * If error pass them back to JSP view. Otherwise creating session for user and redirecting him to main page
 */
@WebServlet(name = "LoginServlet", value = LOGIN_PATH)
public class LoginServlet extends HttpServlet {

    public static final String LOGIN_VIEW_PATH = "/login.jsp";

    public static final String USER_ATTRIBUTE = "user";
    public static final String ERROR_ATTRIBUTE = "error";
    public static final String LOGIN_PARAMETER = "login";
    public static final String PASSWORD_PARAMETER = "password";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (RequestUtils.getSessionAttribute(request, USER_ATTRIBUTE, User.class) != null) {
            // if we somehow opened /login page while being already logged in, we just do redirect page (/Cruise_Liner)
            response.sendRedirect(Path.HOME_PATH);
        } else {
            request.getRequestDispatcher(LOGIN_VIEW_PATH).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter(LOGIN_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER)  ;

        // filling map with parameters which will be passed to the view in case of error
        Map<String, String> viewAttributes = new HashMap<>();
        viewAttributes.put(LOGIN_PARAMETER, login);

        // all request parameters are valid
        // trying to identify and authenticate user
        User user = UserDAO.findUserByLogin(login);
        if(user == null) {
            viewAttributes.put(ERROR_ATTRIBUTE, Constants.USER_NOT_FOUND);
            passErrorToView(request, response, viewAttributes);
            return;
        }
        if(!user.getPassword().equals(password)){
            viewAttributes.put(ERROR_ATTRIBUTE, Constants.WRONG_PASSWORD);
            passErrorToView(request, response, viewAttributes);
            return;
        }

        // if we are here then login information was correct and user was identified
        // lets put him into session and redirect to main page (/Cruise_Liner)
        HttpSession session = request.getSession(true);
        session.setAttribute(USER_ATTRIBUTE, user);
        session.setMaxInactiveInterval(86400); // 1 day
        response.sendRedirect(Path.HOME_PATH);
    }

    private void passErrorToView(HttpServletRequest request, HttpServletResponse response, Map<String, String> viewAttributes) throws ServletException, IOException {
        for(Map.Entry<String, String> entry : viewAttributes.entrySet())
            request.setAttribute(entry.getKey(), entry.getValue());
        request.getRequestDispatcher(LOGIN_VIEW_PATH).forward(request, response);
    }
}