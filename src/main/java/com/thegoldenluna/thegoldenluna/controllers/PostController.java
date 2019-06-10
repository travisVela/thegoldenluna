package com.thegoldenluna.thegoldenluna.controllers;

import com.thegoldenluna.thegoldenluna.models.Post;
import com.thegoldenluna.thegoldenluna.models.User;
import com.thegoldenluna.thegoldenluna.repositories.PostRepo;
import com.thegoldenluna.thegoldenluna.repositories.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PostController {

    private PostRepo postRepo;
    private UserRepo userRepo;

    public PostController(PostRepo postRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }


    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("post", new Post());
        return "create";
    }
    @PostMapping("/create")
        public String create(@ModelAttribute Post post) {
            User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User DbUser = userRepo.findOne(sessionUser.getId());
            post.setUser(DbUser);
            postRepo.save(post);
            return "redirect:/profile";
    }

    @PostMapping("/save")
    public String save(Post post) {
        User postSessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User postDbUser = userRepo.findOne(postSessionUser.getId());
        post.setUser(postDbUser);
        postRepo.save(post);
        return "redirect:/profile";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable long id) {
        postRepo.delete(id);
        return "redirect:/profile";
    }

}
