package com.tweetapp.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.user.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{

}
