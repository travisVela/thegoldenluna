package com.thegoldenluna.thegoldenluna.repositories;

import com.thegoldenluna.thegoldenluna.models.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c ORDER BY time_created DESC")
    List<Comment> findByDateCreated();
}