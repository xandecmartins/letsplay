package com.letsplay.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letsplay.server.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{

	Player findByLogin(String login);
	
}
