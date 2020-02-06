package com.package.servlet;

import com.projectname.dao.UserDao;
import com.projectname.dao.UserProfileDao;
import com.projectname.entities.UserPost;
import com.projectname.entities.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/UserProfile")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProfileServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession httpSession = request.getSession();
            String nextURL = "/error.jsp";
            int userId = 0;
            String action = "";
            UserProfile userProfile = null; //userProfile has not been initialized error if not null
            UserProfile loggedInUserProfile = null; //not initialized error if not null

            if (httpSession.getAttribute("User") == null) {
                nextURL = "/login.jsp";
                httpSession.invalidate();
                response.sendRedirect(request.getContextPath() + nextURL);
                return; //this will prevent an error
            }

            userId = Integer.parseInt(request.getParameter("userId"));
            action = request.getParameter("action");

            if (request.getParameter("action").equals("updateProfile")) {
                int uid = Integer.parseInt(request.getParameter("userId"));
                String email = request.getParameter("email");
                String bio = request.getParameter("bio");
                UserProfile updateProfile = UserDao.getUserByEmail(email);
                updateProfile.setBio(bio);
                updateProfile.setEmail(email);
                UserDao.updateUserProfile(updateProfile);
            }

            userProfile = UserDao.getUserByEmail(userProfile.getEmail());
            loggedInUserProfile = (UserProfile) httpSession.getAttribute("userId");

            if (userProfile.getUserId() == loggedInUserProfile.getUserId()) {
                //logged in user will be able to edit profile
                httpSession.setAttribute("editProfile", true);
            } else {
                //displaying profile as read only, will not be able to edit
                httpSession.setAttribute("editProfile", false);
            }

            /**
             * User Profile info here, with what is displayed
             */

            int imageSize = 150;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String joinDate = simpleDateFormat.format(userProfile.getJoinDate());
            request.setAttribute("userId", userProfile.getUserId());
//            request.setAttribute("userImage",
//            userProfile.getUserImg); <-- find way for user to upload profile image first, then img will be here
//            request.setAttribute("username", userProfile.getUsername()); do not want this info on user profile page
            request.setAttribute("userEmail", userProfile.getEmail());
            nextURL = "/profile.jsp";

            getServletContext().getRequestDispatcher(nextURL)
                    .forward(request, response);

            //will redirect to next page as indicated in the above nextURL

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
