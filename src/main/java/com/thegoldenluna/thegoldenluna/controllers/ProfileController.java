package com.thegoldenluna.thegoldenluna.controllers;

import com.thegoldenluna.thegoldenluna.models.Post;
import com.thegoldenluna.thegoldenluna.models.User;
import com.thegoldenluna.thegoldenluna.repositories.PostRepo;
import com.thegoldenluna.thegoldenluna.repositories.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {
    private UserRepo userRepo;
    private PostRepo postRepo;

    public ProfileController(UserRepo userRepo, PostRepo postRepo) {

        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userRepo.findOne(sessionUser.getId());

        // find all session user posts
        List<Post> userPosts = new ArrayList<>();
        for (Post post : postRepo.findAll()) {
            if (post.getUser().getId() == userDB.getId()) {
                userPosts.add(post);
            }
        }

        model.addAttribute("user", userDB);
        model.addAttribute("posts", userPosts);
        return "users/profile";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Post findOne(long id) {
        return postRepo.findOne(id);
    }
}