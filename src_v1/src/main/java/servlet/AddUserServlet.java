//insert package here

import com.projectname.dao.UserDao;
import com.projectname.entities.UserProfile;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/AddUser")
public class AddUserServlet extends HttpServlet {
        private final static long serialVersionUID = 1L;

    public AddUserServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpSession httpSession = request.getSession();

            //User doesn't have to be logged in
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String bio = request.getParameter("bio");
            String nextURL = "/error.jsp";

            //this is to check if user exist by username
            UserProfile userProfile = UserDao.getUserByEmail(username);

            if(userProfile == null) {
                //if userProfile does not exist

                userProfile = new UserProfile();
                userProfile.setUsername(username);
                userProfile.setPassword(password);
                Date joinDate = Calendar.getInstance().getTime();
                userProfile.setJoinDate(joinDate);
                userProfile.setBio(bio);
                UserDao.insertUser(userProfile); //creating new user and inserting into DB

                nextURL = "/home.jsp"; //after creating new user, we will direct them to home page

            } else {

                String message = "< come back and type welcome message here -";
                request.setAttribute("message", message); //welcome message here
                nextURL = "/login.jsp";

            }

            httpSession.setAttribute("userProfile", userProfile);
            getServletContext().getRequestDispatcher(nextURL)
                    .forward(request, response);

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
