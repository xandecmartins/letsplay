package com.letsplay.server.dto;

import java.util.List;

public class PlayerDTO {

	private String login;
	
	private String loginBgg;
	
	private List<BoardGameDTO> collection;
	
	private Long id;

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

	public List<BoardGameDTO> getCollection() {
		return collection;
	}

	public void setCollection(List<BoardGameDTO> collection) {
		this.collection = collection;
	}
	
}
