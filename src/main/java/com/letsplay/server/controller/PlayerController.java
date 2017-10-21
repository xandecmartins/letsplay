package com.letsplay.server.controller;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letsplay.server.entity.Player;
import com.letsplay.server.service.PlayerService;
 
 
@RestController
@RequestMapping("/api")
public class PlayerController {
 
	@Autowired
	private PlayerService playerService;
 
	@RequestMapping(value = "/players/", method = RequestMethod.GET)
	public List<Player> getAllPlayers() {
 
		return playerService.findAll();
	}
 
	@RequestMapping(value = "/player/{playerId}", method = RequestMethod.GET)
	public Player getPlayer(@PathVariable("playerId") Long playerId) {
		return playerService.findById(playerId);
	}
 
	@RequestMapping(value = "/players/", method = RequestMethod.POST)
	public Player createPlayer(@RequestBody Player player) {
		return playerService.create(player);
	}
 
	@RequestMapping(value = "/players/", method = RequestMethod.PUT)
	public Player updatePlayer(@RequestBody Player player) {
		return playerService.update(player);
	}
	
	@RequestMapping(value = "/players/{playerId}", method = RequestMethod.DELETE)
	public void deletePlayer(@PathVariable("playerId") Long playerId) {
		playerService.delete(playerId);
	}
 
}