package com.letsplay.server.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsplay.server.entity.Player;
import com.letsplay.server.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService{

	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Override
	public List<Player> findAll() {
		List<Player> players = new ArrayList<Player>();
		Iterator<Player> iterator = playerRepository.findAll().iterator();
		while (iterator.hasNext()) {
			players.add(iterator.next());
		}
 
		return players;
	}
 
	@Override
	public Player findById(Long playerId) {
		return playerRepository.findOne(playerId);
	}
 
	@Override
	public Player create(Player player) {
		return playerRepository.save(player);
	}
 
	@Override
	public Player update(Player player) {
		return playerRepository.save(player);
	}
 
	@Override
	public void delete(Long playerId) {
		playerRepository.delete(playerId);
	}
}
