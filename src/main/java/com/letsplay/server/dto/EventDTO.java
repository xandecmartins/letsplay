package com.letsplay.server.dto;

import java.util.Date;
import java.util.List;

public class EventDTO {
	private Long id;
	
	private Date date;
	
	private String location;
	
	private String observations;

	private List<BoardGameDTO> selectedGames;
	
	private GroupDTO group;
	
	private List<PlayerDTO> confirmedPlayers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public List<BoardGameDTO> getSelectedGames() {
		return selectedGames;
	}

	public void setSelectedGames(List<BoardGameDTO> selectedGames) {
		this.selectedGames = selectedGames;
	}

	public GroupDTO getGroup() {
		return group;
	}

	public void setGroup(GroupDTO group) {
		this.group = group;
	}

	public List<PlayerDTO> getConfirmedPlayers() {
		return confirmedPlayers;
	}

	public void setConfirmedPlayers(List<PlayerDTO> confirmedPlayers) {
		this.confirmedPlayers = confirmedPlayers;
	}
}
