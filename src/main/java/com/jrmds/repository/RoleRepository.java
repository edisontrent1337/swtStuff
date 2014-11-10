package com.jrmds.repository;

import com.jrmds.domain.Role;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface RoleRepository extends GraphRepository<Role> {

}