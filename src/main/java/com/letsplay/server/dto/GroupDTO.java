package com.letsplay.server.dto;

import java.util.Date;
import java.util.List;

public class GroupDTO {
	
	private Long id;
	
	private List<PlayerDTO> players;
	
	private String name;
	
	private Date lastGame;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PlayerDTO> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerDTO> players) {
		this.players = players;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastGame() {
		return lastGame;
	}

	public void setLastGame(Date lastGame) {
		this.lastGame = lastGame;
	}
	
	
}
