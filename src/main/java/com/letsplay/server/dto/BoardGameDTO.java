package com.letsplay.server.dto;

public class BoardGameDTO {
	
	private Long id;

	private String name;
	
	private String thumbnail;
	
	private Integer minPlayers;
	
	private Integer maxPlayers;
	
	private Integer playingTime;
	
	private Boolean isExpansion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
