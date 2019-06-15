package com.thegoldenluna.thegoldenluna.controllers;

import com.thegoldenluna.thegoldenluna.models.Category;
import com.thegoldenluna.thegoldenluna.models.Post;
import com.thegoldenluna.thegoldenluna.models.User;
import com.thegoldenluna.thegoldenluna.repositories.CategoryRepo;
import com.thegoldenluna.thegoldenluna.repositories.PostRepo;
import com.thegoldenluna.thegoldenluna.repositories.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryViewController {
    private UserRepo userRepo;
    private PostRepo postRepo;
    private CategoryRepo categoryRepo;

    public CategoryViewController(PostRepo postRepo, CategoryRepo categoryRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/posts/category/{id}")
    public String categoryPosts(@PathVariable long id,  Model model) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User DbUser = userRepo.findOne(sessionUser.getId());

        Iterable<Post> posts = postRepo.findAll();
        Category category = categoryRepo.findOne(id);
        List<Post> categoryPosts = new ArrayList<>();

        for (Post post : posts) {
            if (post.getPost_categories().contains(category)) {
                categoryPosts.add(post);
            }
        }

        List<Category> otherCats = new ArrayList<>();
        for (Category cat : categoryRepo.findAll()) {
            if (cat.getId() != category.getId()) {
                otherCats.add(cat);
            }
        }
        model.addAttribute("posts", categoryPosts);
        model.addAttribute("user", DbUser);
        model.addAttribute("otherCats", otherCats);
        return "/posts/category";
    }

}
