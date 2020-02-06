//insert package name here

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table
@NamedQuery(name = "UserProfile.findAll", query = "SELECT c FROM UserProfile c")
public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue
    @Column(nullable = false, updatable = false)
    private int userId;

    @Basic
    @Column
    private String fName;

    @Basic
    @Column
    private String lName;

    @Basic
    @Column
    private String username;

    @Basic
    @Column
    private String bio;

    @Basic
    @Column
    private String email;

    @Basic
    @Column
    private String password;

    @Basic
    @Column
    @Temporal(TemporalType.DATE)
    private Date joinDate;

    public UserProfile() {
        super();
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId=" + userId +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", username='" + username + '\'' +
                ", bio='" + bio + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", joinDate=" + joinDate +
                ", userPost=" + userPost +
                '}';
    }

    public UserProfile(int userId, String fName, String lName,
                       String username, String bio, Date joinDate,
                       String email, String password) {
        super();
        this.userId = userId;
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.bio = bio;
        this.joinDate = joinDate;
        this.email = email;
        this.password = password;
    }

    @OneToMany(mappedBy = "userProfile")
    private List<UserPost> userPost;

    public UserPost addUserPost(UserPost userPost) {
        getUserPost().add(userPost);
        userPost.setPostId(userId); // ?? I need to fix this

        /**
         * Setting/ assigning post to user id
         */

        return userPost;
    }

    public UserPost removeUserPost(UserPost userPost) {
        getUserPost().remove(userPost);
        userPost.setPostMessage(null);
        return userPost;
    }

    public List<UserPost> getUserPost() {
        return userPost;
    }

    public void setUserPost(List<UserPost> userPost) {
        this.userPost = userPost;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
