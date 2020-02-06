//insert package name here

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@NamedQuery(name = "UserPost.findAll", query = "SELECT c FROM UserPost c")
public class UserPost implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue
    @Column
    private int postId;

    @Basic
    @Column
    private String postMessage;

    @Basic
    @Column
    @Temporal(TemporalType.DATE)
    private Date postDate;

    @MapsId("postId")
    @ManyToOne
    @JoinColumn //(name = "userId") << should be but it is showing error, come back and fix
    private UserProfile userProfile;

    public UserPost() {
        super();
    }

    public UserPost(int postId, String postMessage, Date postDate) {
        super();
        this.postId = postId;
        this.postMessage = postMessage;
        this.postDate = postDate;
    }

    @Override
    public String toString() {
        return "UserPost{" +
                "postId = " + postId +
                ", postMessage =' " + postMessage + '\'' +
                ", postDate = " + postDate +
                ", userProfile = " + userProfile +
                '}';
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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
}
