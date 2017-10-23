package com.letsplay.server.service;

import java.util.List;

import com.letsplay.server.entity.Event;

public interface EventService {
	Event findById(Long id);

	void saveEvent(Event event);

	void updateEvent(Event event);

	void deleteEventById(Long id);

	void deleteAllEvents();

	List<Event> findAllEvents();
}
