package com.letsplay.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letsplay.server.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{

	Group findByName(String name);
}
