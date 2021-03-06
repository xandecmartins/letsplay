package com.letsplay.server.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BoardGroup")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groups_seq_gen")
	@SequenceGenerator(name = "groups_seq_gen", sequenceName = "groups_id_seq")
	private Long id;
	
	@ManyToMany(mappedBy = "groups")
	private List<Player> players;
	
	@OneToMany
	private List<Event> events;
	
	private String name;
	
	private Date lastGame;
	
	public Group(Long id) {
		this.id = id;
	}
	public Group() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
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
	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
