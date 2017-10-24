package com.letsplay.server.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class EventDTO {
	private SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	private SimpleDateFormat sdfOutput = new SimpleDateFormat("HH:mm");
	private SimpleDateFormat sdfOutputDate = new SimpleDateFormat("dd/MM/yyyy");
	private Long id;

	private String date;

	private String time;

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

	public String getDate() {
		return date;
	}

	public void setDate(Date date) {
		sdfOutputDate.setTimeZone(TimeZone.getTimeZone("UTC-3"));
		this.date = sdfOutputDate.format(date);
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) throws ParseException {
		if (time != null) {
			sdfInput.setTimeZone(TimeZone.getTimeZone("UTC-3"));
			Date date = sdfInput.parse(time);
			sdfOutput.setTimeZone(TimeZone.getTimeZone("UTC-3"));
			this.time = sdfOutput.format(date);
		}
	}
}
