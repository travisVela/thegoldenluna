package com.thegoldenluna.thegoldenluna.controllers;

import com.thegoldenluna.thegoldenluna.models.User;
import com.thegoldenluna.thegoldenluna.repositories.CategoryRepo;
import com.thegoldenluna.thegoldenluna.repositories.CommentRepo;
import com.thegoldenluna.thegoldenluna.repositories.PostRepo;
import com.thegoldenluna.thegoldenluna.repositories.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

@SuppressWarnings("duplicates")
@RestController
@RequestMapping("/api/v1")
public class ApiController {
    private UserRepo userRepo;
    private PostRepo postsRepo;
    private CategoryRepo categoryRepo;
    private CommentRepo commentRepo;

    public ApiController(UserRepo userRepo, PostRepo postsRepo, CategoryRepo categoryRepo, CommentRepo commentRepo) {
        this.userRepo = userRepo;
        this.postsRepo = postsRepo;
        this.categoryRepo = categoryRepo;
        this.commentRepo = commentRepo;
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<Boolean> checkIfUsernameExists(@PathVariable String username) {
        boolean userExistInDb = false;
        Iterable<User> allUsers = userRepo.findAll();
        Iterator<User> totalUsers = allUsers.iterator();
        while(totalUsers.hasNext()) {
            if (totalUsers.next().getUsername().equals(username)) {
                userExistInDb = true;
            }
        }
        return ResponseEntity.ok(userExistInDb);
    }

    @GetMapping("/email/{email:.+}")
    public ResponseEntity<Boolean> checkIfEmailExists(@PathVariable String email) {
        boolean emailExistsInDb = false;
        Iterable<User> allUsers = userRepo.findAll();
        for (User user : allUsers) {
            if (user.getEmail().equals(email)) {
                emailExistsInDb = true;
            }
        }
        return ResponseEntity.ok(emailExistsInDb);
    }
}
