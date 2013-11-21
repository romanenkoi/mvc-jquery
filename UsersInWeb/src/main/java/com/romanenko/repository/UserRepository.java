package com.romanenko.repository;

import com.romanenko.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
	
	User findByUsername(String username);
}
