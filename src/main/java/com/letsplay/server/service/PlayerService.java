package com.letsplay.server.service;

import java.util.List;

import com.letsplay.server.entity.Player;

public interface PlayerService {
	List<Player> findAll();	
	
	Player findById(Long PlayerId);
	
	Player create(Player Player);
	
	Player update(Player Player);
 
	void delete(Long PlayerId);
}
