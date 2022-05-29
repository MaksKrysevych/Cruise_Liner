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

/**
 * Signup page controller
 * Retrieves credentials from request and validate them.
 * If error pass them back to JSP view. Otherwise adding user to Db, creating session and redirecting him to main page
 */
@WebServlet(name = "SignupServlet", value = Path.SIGNUP_PATH)
public class SignupServlet extends HttpServlet {

    public static final String SIGNUP_VIEW_PATH = "/signup.jsp";

    public static final String USER_ATTRIBUTE = "user";
    public static final String ERROR_ATTRIBUTE = "error";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String PHONE_NUMBER_ATTRIBUTE = "phone";
    public static final String EMAIL_PARAMETER = "email";
    public static final String PASSWORD_PARAMETER = "password";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (RequestUtils.getSessionAttribute(request, USER_ATTRIBUTE, User.class) != null) {
            // if we somehow opened /signup page while being already logged in, we just do redirect to main page (/Cruise_Liner)
            response.sendRedirect(Path.HOME_PATH);
        } else {
            request.getRequestDispatcher(SIGNUP_VIEW_PATH).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (RequestUtils.getSessionAttribute(request, USER_ATTRIBUTE, User.class) != null) {
            // if we somehow opened /signup page while being already logged in, we just do redirect to main page (/Cruise_Liner)
            response.sendRedirect(Path.HOME_PATH);
            return;
        }

        // continue to perform signup
        // retrieving parameters from signup form
        String login = request.getParameter("login");
        String password = request.getParameter(PASSWORD_PARAMETER);
        String name = request.getParameter(NAME_ATTRIBUTE);
        String surname = request.getParameter("surname");
        String phoneNumber = request.getParameter(PHONE_NUMBER_ATTRIBUTE);
        String email = request.getParameter(EMAIL_PARAMETER);

        // filling map with parameters which will be passed to the view
        HashMap<String, String> viewAttributes = new HashMap<>();
        viewAttributes.put(EMAIL_PARAMETER, email);
        viewAttributes.put(NAME_ATTRIBUTE, name);
        viewAttributes.put(PHONE_NUMBER_ATTRIBUTE, phoneNumber);

        // lets create user in DB and make him logged in
        boolean userAdded = false;
        try {
            userAdded = UserDAO.addUser(login, password, User.Role.USER.toString(), name, surname, phoneNumber, email, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!userAdded) {
            viewAttributes.put(ERROR_ATTRIBUTE, Constants.OTHER_ERROR);
            passErrorToView(request, response, viewAttributes);
        } else {
            User user = UserDAO.findUserByLogin(login);
            // lets put him into session and redirect to main page (/Cruise_Liner)
            HttpSession session = request.getSession(true);
            session.setAttribute(USER_ATTRIBUTE, user);
            response.sendRedirect(Path.HOME_PATH);
        }
    }

    private void passErrorToView(HttpServletRequest request, HttpServletResponse response, Map<String, String> viewAttributes) throws ServletException, IOException {
        for(Map.Entry<String, String> entry : viewAttributes.entrySet())
            request.setAttribute(entry.getKey(), entry.getValue());
        request.getRequestDispatcher(SIGNUP_VIEW_PATH).forward(request, response);
    }

}
