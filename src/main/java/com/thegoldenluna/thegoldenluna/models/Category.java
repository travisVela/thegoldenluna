package com.thegoldenluna.thegoldenluna.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id @GeneratedValue
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @JsonIgnore
    @ManyToMany(mappedBy = "post_categories")
    private List<Post> posts;


    public Category(){}

    public Category(String title, List<Post> posts) {
        this.title = title;
        this.posts = posts;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}

