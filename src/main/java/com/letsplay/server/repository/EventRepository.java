package com.letsplay.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letsplay.server.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

	
}
