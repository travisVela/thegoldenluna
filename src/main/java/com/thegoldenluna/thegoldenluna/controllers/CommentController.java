package com.thegoldenluna.thegoldenluna.controllers;

import com.thegoldenluna.thegoldenluna.models.Comment;
import com.thegoldenluna.thegoldenluna.models.Post;
import com.thegoldenluna.thegoldenluna.models.User;
import com.thegoldenluna.thegoldenluna.repositories.CommentRepo;
import com.thegoldenluna.thegoldenluna.repositories.PostRepo;
import com.thegoldenluna.thegoldenluna.repositories.UserRepo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    @GetMapping("/edit")
    @ResponseBody
    public Comment editComment(long id) {
        Comment comment = commentRepo.findOne(id);

        System.out.println("comment id: " + comment.getId());
        System.out.println("post id from get mapping " + comment.getPost().getId());
        System.out.println("date created: " + comment.getDateCreated().getClass().getName());
        return commentRepo.findOne(id);
    }

    @PostMapping("/save-edit")
    public String saveEditComment(Comment comment,
                                  @RequestParam(name = "postId") long postId,
                                  @RequestParam(name = "dateCreated") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                  @RequestParam(name = "timeCreated") @DateTimeFormat(pattern = "HH:mm:ss") Date time)
    {

        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User DbUser = userRepo.findOne(sessionUser.getId());
        comment.setDateCreated(date);
        comment.setTimeCreated(time);
        comment.setPost(postRepo.findOne(postId));
        System.out.println("post id: " + comment.getPost());
        System.out.println("date: " + date);
        comment.setUser(DbUser);

        commentRepo.save(comment);
        return "redirect:/post/" + postId;
    }

    @GetMapping("/remove/{id}")
    public String removeComment(@PathVariable long id, Model model) {

        Comment comment = commentRepo.findOne(id);
        long postId = comment.getPost().getId();
        System.out.println(postId);
        model.addAttribute("comment", comment);
        commentRepo.delete(id);
        return "redirect:/post/" + postId;
    }


}
