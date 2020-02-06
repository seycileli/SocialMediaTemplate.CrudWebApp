package com.packagenamehere.servlet;

import com.projectname.dao.UserDao;
import com.projectname.entities.UserProfile;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class will process the login.jsp
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long SerialVersionUID = 1L;

    static UserProfile userProfile = new UserProfile();
    static UserDao userDao = new UserDao();

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            String userEmail = request.getParameter("email");
            String userPassword = request.getParameter("password");
            String action = request.getParameter("action");
            String nextURL = "/error.jsp";
            HttpSession session = request.getSession();
            session.setAttribute(userEmail, userPassword);

            if (action.equals("logout")) {
                session.invalidate();
                nextURL = "/index.jsp";

            } else {

                if (userDao.isUserValid(userEmail, userPassword)) {
                    UserProfile userProfile = userDao.getUserByEmail(userEmail);
                    session.setAttribute("User: ", userProfile);
                    nextURL = "/home.jsp"; //after logging in, user will be directed to home page
                } else {
                    nextURL = "/login.jsp"; //otherwise, User will be directed to log-in page
                }
            }
            getServletContext().getRequestDispatcher(nextURL).forward(request, response);
            //will redirect to what is indicated by what is assigned to "nextURL" String

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
