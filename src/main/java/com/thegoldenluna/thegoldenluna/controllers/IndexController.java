package com.thegoldenluna.thegoldenluna.controllers;

import com.thegoldenluna.thegoldenluna.repositories.CategoryRepo;
import com.thegoldenluna.thegoldenluna.repositories.PostRepo;
import com.thegoldenluna.thegoldenluna.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private UserRepo userRepo;
    private PostRepo postRepo;
    private CategoryRepo categoryRepo;

    public IndexController(UserRepo userRepo, PostRepo postRepo, CategoryRepo categoryRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/home")
    public String index(Model model) {

        model.addAttribute("posts", postRepo.findAll());
        model.addAttribute("categories", categoryRepo.findAll());
        return "index";
    }
}
