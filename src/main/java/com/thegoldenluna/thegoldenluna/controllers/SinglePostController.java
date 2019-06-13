package com.thegoldenluna.thegoldenluna.controllers;

import com.thegoldenluna.thegoldenluna.models.Comment;
import com.thegoldenluna.thegoldenluna.models.Post;
import com.thegoldenluna.thegoldenluna.models.User;
import com.thegoldenluna.thegoldenluna.repositories.CommentRepo;
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
public class SinglePostController {
    private UserRepo userRepo;
    private PostRepo postRepo;
    private CommentRepo commentRepo;

    public SinglePostController(UserRepo userRepo, PostRepo postRepo, CommentRepo commentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable long id, Model model) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User DbUser = userRepo.findOne(sessionUser.getId());
        Post post = postRepo.findOne(id);

        List<Comment> postComments = new ArrayList<>();
        for (Comment comment : commentRepo.findAll()) {
            if (post.getId() == comment.getPost().getId()) {
                postComments.add(comment);
            }
        }
        Iterable<Comment> commentsDec = commentRepo.findByDateCreated();
        model.addAttribute("user", DbUser);
        model.addAttribute("post", post);
        model.addAttribute("commentsDesc", commentsDec);
        model.addAttribute("comments", postComments);
        return "posts/singlePost";
    }
}
