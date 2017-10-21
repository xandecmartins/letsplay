package com.letsplay.server.service;

import java.util.List;

import com.letsplay.server.entity.Player;

public interface PlayerService {
	Player findById(Long id);

	Player findByLogin(String login);

	void savePlayer(Player player);

	void updatePlayer(Player player);

	void deletePlayerById(Long id);

	void deleteAllPlayers();

	List<Player> findAllPlayers();

	boolean isPlayerExist(Player player);
}
