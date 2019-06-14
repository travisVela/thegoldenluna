package com.thegoldenluna.thegoldenluna.controllers;

import com.thegoldenluna.thegoldenluna.models.Category;
import com.thegoldenluna.thegoldenluna.models.Post;
import com.thegoldenluna.thegoldenluna.models.User;
import com.thegoldenluna.thegoldenluna.repositories.CategoryRepo;
import com.thegoldenluna.thegoldenluna.repositories.PostRepo;
import com.thegoldenluna.thegoldenluna.repositories.UserRepo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {

    private PostRepo postRepo;
    private UserRepo userRepo;
    private CategoryRepo categoryRepo;

    public PostController(PostRepo postRepo, UserRepo userRepo, CategoryRepo categoryRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }


    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("post", new Post());
        return "create";
    }
    @PostMapping("/create")
        public String create(@ModelAttribute Post post, @RequestParam(name = "checked") List<String> checked) {
            User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User DbUser = userRepo.findOne(sessionUser.getId());
            post.setUser(DbUser);

            Iterable<Category> cats = categoryRepo.findAll();
            List<Category> checkedCats = new ArrayList<>();

            for (Category category : cats) {
                String categoryTitle = category.getTitle();
                    if (checked.contains(categoryTitle)) {
                        checkedCats.add(category);
                }
            }

            post.setPost_categories(checkedCats);
            postRepo.save(post);
            return "redirect:/profile";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Post findOne(long id) {
        return postRepo.findOne(id);
    }

    @PostMapping("/save")
    public String save(Post post,
                       @RequestParam(name = "dateCreated") @DateTimeFormat(pattern = "yyyy-MM-dd")Date date,
                       @RequestParam(name = "timeCreated") @DateTimeFormat(pattern = "HH:mm:ss") Date time)
    {
        User postSessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User postDbUser = userRepo.findOne(postSessionUser.getId());
        post.setDateCreated(date);
        post.setTimeCreated(time);
        post.setUser(postDbUser);
        postRepo.save(post);
        return "redirect:/profile";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postRepo.findOne(id));
        postRepo.delete(id);
        return "redirect:/profile";
    }


}
