package com.jrmds.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.jrmds.domain.User;

public interface UserRepository extends GraphRepository<User> {
	User findByUsername(String username);
}
