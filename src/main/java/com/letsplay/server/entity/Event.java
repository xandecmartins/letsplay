package com.letsplay.server.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Event {

	@Id
	@GeneratedValue
	private Long id;
	
	private Date date;
	
	private String location;
	
	private String observations;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "event_boardgame", 
		joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "boardgame_id", referencedColumnName = "id"))
	private List<BoardGame> selectedGames;
	
	@ManyToOne
	private Group group;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "event_player", 
		joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "player_id", referencedColumnName = "id"))
	private List<Player> confirmedPlayers;

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

	public List<BoardGame> getSelectedGames() {
		return selectedGames;
	}

	public void setSelectedGames(List<BoardGame> selectedGames) {
		this.selectedGames = selectedGames;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<Player> getConfirmedPlayers() {
		return confirmedPlayers;
	}

	public void setConfirmedPlayers(List<Player> confirmedPlayers) {
		this.confirmedPlayers = confirmedPlayers;
	}
	
}
