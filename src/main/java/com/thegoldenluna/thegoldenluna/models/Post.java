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

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateCreated;

    @CreationTimestamp
    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private java.util.Date timeCreated;

    @Column(name = "featuredImg_url", nullable = false)
    private String featuredImgURL;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments;

//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "post")
//    private List<Category> categories;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "post_categories",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> post_categories;

    public Post(){}

    public Post(String title, String body, Date dateCreated, Date timeCreated ,String featuredImgURL, User user, List<Comment> comments,  List<Category> post_categories) {
        this.title = title;
        this.body = body;
        this.dateCreated = dateCreated;
        this.timeCreated = timeCreated;
        this.featuredImgURL = featuredImgURL;
        this.user = user;
        this.comments = comments;
//        this.categories = categories;
        this.post_categories = post_categories;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

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

//    public List<Category> getCategories() {
//        return post_categories;
//    }
//
//    public void setCategories(List<Category> categories) {
//        this.post_categories = categories;
//    }


    public List<Category> getPost_categories() {
        return post_categories;
    }

    public void setPost_categories(List<Category> post_categories) {
        this.post_categories = post_categories;
    }

}
