package com.packagehere.servlet;

import com.projectname.entities.UserPost;
import com.projectname.entities.UserProfile;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/Post")
public class PostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PostServlet() {
        super();
    }

    protected void doPost(HttpServletResponse response, HttpServletRequest request) {

        try {

            String postMessage = request.getParameter("PostMessage");
            String nextURL = "/error.jsp";
            HttpSession httpSession = request.getSession();

            if (httpSession.getAttribute("user") == null) {
                nextURL = "/login.jsp";
                httpSession.invalidate();
            } else {
                UserProfile userProfile = (UserProfile) httpSession.getAttribute("user");
                //will get User out of Session
                UserPost userPost = new UserPost();
//                userPost.setUserProfile(userProfile); //?? We want the userPost to be assigned to userProfile
                Date date = Calendar.getInstance().getTime(); //current date
                userPost.setPostDate(date);
                userPost.setPostMessage(postMessage);
//                UserPost.insert(userPost); //
                nextURL = "/Feed"; //will post to Feed servlet, and feed section
            }

            getServletContext().getRequestDispatcher(nextURL).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
