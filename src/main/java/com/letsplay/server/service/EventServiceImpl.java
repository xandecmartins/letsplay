package com.letsplay.server.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsplay.server.entity.Event;
import com.letsplay.server.repository.EventRepository;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService{
 
    @Autowired
    private EventRepository eventRepository;
    private SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
 
    public Event findById(Long id) {
        return eventRepository.findOne(id);
    }
 
    public void saveEvent(Event event) {
    	sdfInput.setTimeZone(TimeZone.getTimeZone("UTC-3"));
		try {
			Date date = sdfInput.parse(event.getTime());
			date.setHours(date.getHours()-3);
			event.setTime(sdfInput.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	eventRepository.save(event);
    }
 
    public void updateEvent(Event event){
        saveEvent(event);
    }
 
    public void deleteEventById(Long id){
        eventRepository.delete(id);
    }
 
    public void deleteAllEvents(){
        eventRepository.deleteAll();
    }
 
    public List<Event> findAllEvents(){
        return eventRepository.findAll();
    }
}