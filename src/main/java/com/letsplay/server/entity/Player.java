package com.letsplay.server.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Player {

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	@Column(nullable = false)
	private String login;
	
	private String loginBgg;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "player_group", 
		joinColumns = @JoinColumn(name = "player_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
	private List<Group> groups;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "player_boardgame", 
		joinColumns = @JoinColumn(name = "player_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "boardgame_id", referencedColumnName = "id"))
	private List<BoardGame> collection;
	
	@ManyToMany(mappedBy="confirmedPlayers")
	private List<Event> events;
	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLoginBgg() {
		return loginBgg;
	}

	public void setLoginBgg(String loginBgg) {
		this.loginBgg = loginBgg;
	}

	public List<BoardGame> getCollection() {
		return collection;
	}

	public void setCollection(List<BoardGame> collection) {
		this.collection = collection;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		Player other = (Player) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

}
