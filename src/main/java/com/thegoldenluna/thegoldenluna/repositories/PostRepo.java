package com.thegoldenluna.thegoldenluna.repositories;

import com.thegoldenluna.thegoldenluna.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends CrudRepository<Post, Long> {
    @Query("SELECT p FROM Post p ORDER BY date_created DESC")
    List<Post> findByDateCreated();
}
