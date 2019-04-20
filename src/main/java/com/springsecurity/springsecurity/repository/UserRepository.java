package com.springsecurity.springsecurity.repository;

import com.springsecurity.springsecurity.entity.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Integer> {
   User findByUsername(String username);
}
