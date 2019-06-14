package com.thegoldenluna.thegoldenluna.controllers;

import com.thegoldenluna.thegoldenluna.models.Category;
import com.thegoldenluna.thegoldenluna.models.Post;
import com.thegoldenluna.thegoldenluna.repositories.CategoryRepo;
import com.thegoldenluna.thegoldenluna.repositories.PostRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryViewController {
    private PostRepo postRepo;
    private CategoryRepo categoryRepo;

    public CategoryViewController(PostRepo postRepo, CategoryRepo categoryRepo) {
        this.postRepo = postRepo;
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/posts/category/{id}")
    public String categoryPosts(@PathVariable long id,  Model model) {
        Iterable<Post> posts = postRepo.findAll();
        Category category = categoryRepo.findOne(id);
        List<Post> categoryPosts = new ArrayList<>();

        for (Post post : posts) {
            if (post.getPost_categories().contains(category)) {
                categoryPosts.add(post);
            }
        }
        model.addAttribute("posts", categoryPosts);
        return "/posts/category";
    }

}
