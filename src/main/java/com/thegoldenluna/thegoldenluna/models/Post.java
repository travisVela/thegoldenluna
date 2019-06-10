package com.thegoldenluna.thegoldenluna.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id @GeneratedValue
    private long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 1000)
    private String body;

//    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(nullable = false)
//    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
//    private Date dateCreated;

    @Column(name = "featuredImg_url", nullable = false)
    private String featuredImgURL;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    List<Comment> comments;

    public Post(){}

    public Post(String title, String body, String featuredImgURL, User user, List<Comment> comments) {
        this.title = title;
        this.body = body;
//        this.dateCreated = dateCreated;
        this.featuredImgURL = featuredImgURL;
        this.user = user;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

//    public Date getDateCreated() {
//        return dateCreated;
//    }

//    public void setDateCreated(Date dateCreated) {
//        this.dateCreated = dateCreated;
//    }

    public String getFeaturedImgURL() {

        return (this.featuredImgURL == null) ? "https://cdn.filestackcontent.com/PaxNxVe2Tkie1TnX8Wcw" : featuredImgURL;
    }

    public void setFeaturedImgURL(String featuredImgURL) {
        this.featuredImgURL = featuredImgURL;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", featuredImgURL='" + featuredImgURL + '\'' +
                ", user=" + user +
                '}';
    }
}
