package com.thegoldenluna.thegoldenluna.repositories;

import com.thegoldenluna.thegoldenluna.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
