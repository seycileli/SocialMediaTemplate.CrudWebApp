package com.casestudyproject.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table
@NamedQuery(name = "UserProfile.findAll", query = "SELECT db FROM UserProfile db")
public class UserProfile implements Serializable {
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true, updatable = false)
    private int userid;

    @Basic
    @Column
    private String firstName;

    @Basic
    @Column
    private String lastName;

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

    public UserProfile(int userid, String firstName, String lastName,
                       String username, String bio, String password, Date joinDate) {
        super();
        this.userid = userid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.bio = bio;
        this.password = password;
        this.joinDate = joinDate;

    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userid=" + userid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", bio='" + bio + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", joinDate=" + joinDate +
                '}';
    }

    @OneToMany//(mappedBy = "UserProfile")
    private List<UserPost> userPost;

    public UserPost addUserPost(UserPost userPost) {
        getUserPost().add(userPost);
        userPost.setPostid(this.userid); // ?? I need to fix this

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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}
