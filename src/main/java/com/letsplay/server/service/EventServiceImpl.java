package com.letsplay.server.service;

import java.util.List;

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
    
 
    public Event findById(Long id) {
        return eventRepository.findOne(id);
    }
 
    public void saveEvent(Event event) {
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