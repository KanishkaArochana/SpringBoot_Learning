package com.databaseConfig.Repositories;

import com.databaseConfig.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<User, Integer> {

    // JpaRepository<User, Integer> provides CRUD methods automatically
    // <User> = Entity type
    // <Integer> = Primary key type
}
