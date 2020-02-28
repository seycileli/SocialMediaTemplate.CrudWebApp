package com.casestudyproject.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table
@Entity
@NamedQuery(name = "UserPost.findAll", query = "SELECT db FROM UserPost db")
public class UserPost implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true, updatable = false)
    private int postid;

    @Basic
    @Column
    private String postMessage;

    @Basic
    @Column
    @Temporal(TemporalType.DATE)
    private Date postDate;

    @MapsId("postid")
    @ManyToOne
    @JoinColumn//(name = "userid")
    private UserProfile userProfile;

    public UserPost(int postid, String postMessage, Date postDate) {
        super();
        this.postid = postid;
        this.postMessage = postMessage;
        this.postDate = postDate;
    }

    public UserPost() {
        super();
    }

    @Override
    public String toString() {
        return "UserPost{" +
                "postid=" + postid +
                ", postMessage='" + postMessage + '\'' +
                ", postDate=" + postDate +
                ", userProfile=" + userProfile +
                '}';
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(String postMessage) {
        this.postMessage = postMessage;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
