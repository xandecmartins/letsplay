package com.letsplay.server.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class BoardGame {

	@Id
	private Long id;

	private String name;

	private String thumbnail;

	private Integer minPlayers;

	private Integer maxPlayers;

	private Integer playingTime;

	private Boolean isExpansion;

	@ManyToMany(mappedBy = "collection")
	private List<Player> players;

	@ManyToMany(mappedBy = "selectedGames")
	private List<Event> events;

	public BoardGame() {
	}

	public BoardGame(Long id) {
		this.id = id;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGameId() {
		return id;
	}

	public void setGameId(Long gameId) {
		this.id = gameId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Integer getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(Integer minPlayers) {
		this.minPlayers = minPlayers;
	}

	public Integer getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(Integer maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public Integer getPlayingTime() {
		return playingTime;
	}

	public void setPlayingTime(Integer playingTime) {
		this.playingTime = playingTime;
	}

	public Boolean getIsExpansion() {
		return isExpansion;
	}

	public void setIsExpansion(Boolean isExpansion) {
		this.isExpansion = isExpansion;
	}
}
