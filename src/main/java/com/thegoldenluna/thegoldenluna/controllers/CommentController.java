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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {

    private UserRepo userRepo;
    private PostRepo postRepo;
    private CommentRepo commentRepo;

    public CommentController(UserRepo userRepo, PostRepo postRepo, CommentRepo commentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

//    @GetMapping("/post/{id}")
//    public String showComments(@PathVariable long id, Model model){
//        Post post = postRepo.findOne(id);
//
//        List<Comment> postComments = new ArrayList<>();
//        for (Comment comment : commentRepo.findAll()) {
//            if (post.getId() == comment.getPost().getId()) {
//                postComments.add(comment);
//            }
//        }
//
//        model.addAttribute("comments", postComments);
//        return "posts/singlePost";
//    }

    @GetMapping("/leave")
    public String leaveComment(Model model) {
        model.addAttribute("comment", new Comment());
        return "leave";
    }
    @PostMapping("/leave")
    public String saveComment(@ModelAttribute Comment comment, @RequestParam(name = "postId") long id) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User DbUser = userRepo.findOne(sessionUser.getId());
        comment.setUser(DbUser);
        comment.setPost(postRepo.findOne(id));
        commentRepo.save(comment);
        return "redirect:/post/" + id;
    }


}
