package com.thegoldenluna.thegoldenluna.repositories;

import com.thegoldenluna.thegoldenluna.models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
}
