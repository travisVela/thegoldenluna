package com.thegoldenluna.thegoldenluna.repositories;

import com.thegoldenluna.thegoldenluna.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Long> {
}
