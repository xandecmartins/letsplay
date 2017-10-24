package com.letsplay.server.controller;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.letsplay.server.dto.EventDTO;
import com.letsplay.server.entity.Event;
import com.letsplay.server.service.EventService;
import com.letsplay.server.util.CustomErrorType;
 
@RestController
@RequestMapping("/api")
public class EventController {
 
    public static final Logger logger = LoggerFactory.getLogger(EventController.class);
 
    @Autowired
    private EventService eventService; //Service which will do all data retrieval/manipulation work
    
    
 
    @Autowired
	private ModelMapper modelMapper;
    // -------------------Retrieve All Events---------------------------------------------
 
    @RequestMapping(value = "/event/", method = RequestMethod.GET)
    public ResponseEntity<List<EventDTO>> listAllEvents() {
        List<Event> events = eventService.findAllEvents();
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<EventDTO>>(convertToDto(events), HttpStatus.OK);
    }
 
    // -------------------Retrieve Single Event------------------------------------------
 
    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEvent(@PathVariable("id") long id) {
        logger.info("Fetching Event with id {}", id);
        Event event = eventService.findById(id);
        if (event == null) {
            logger.error("Event with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Event with id " + id 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<EventDTO>(convertToDto(event), HttpStatus.OK);
    }
 
    // -------------------Create a Event-------------------------------------------
 
    @RequestMapping(value = "/event/", method = RequestMethod.POST)
    public ResponseEntity<?> createEvent(@RequestBody Event event, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Event : {}", event);
        
        eventService.saveEvent(event);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/event/{id}").buildAndExpand(event.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
 
    // ------------------- Update a Event ------------------------------------------------
 
    @RequestMapping(value = "/event/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEvent(@PathVariable("id") long id, @RequestBody Event event) {
        logger.info("Updating Event with id {}", id);
 
        Event currentEvent = eventService.findById(id);
 
        if (currentEvent == null) {
            logger.error("Unable to update. Event with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to upate. Event with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
 
        currentEvent.setDate(event.getDate());
        
 
        eventService.updateEvent(currentEvent);
        return new ResponseEntity<EventDTO>(convertToDto(currentEvent), HttpStatus.OK);
    }
 
    // ------------------- Delete a Event-----------------------------------------
 
    @RequestMapping(value = "/event/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEvent(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Event with id {}", id);
 
        Event event = eventService.findById(id);
        if (event == null) {
            logger.error("Unable to delete. Event with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Event with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        eventService.deleteEventById(id);
        return new ResponseEntity<EventDTO>(HttpStatus.NO_CONTENT);
    }
 
    // ------------------- Delete All Events-----------------------------
 
    @RequestMapping(value = "/event/", method = RequestMethod.DELETE)
    public ResponseEntity<EventDTO> deleteAllEvents() {
        logger.info("Deleting All Events");
 
        eventService.deleteAllEvents();
        return new ResponseEntity<EventDTO>(HttpStatus.NO_CONTENT);
    }
    
    private EventDTO convertToDto(Event event) {
		return modelMapper.map(event, EventDTO.class);
	}

	private List<EventDTO> convertToDto(List<Event> listEvent) {
		List<EventDTO> retVal = new ArrayList<>();
		for (Event event : listEvent) {
			retVal.add(modelMapper.map(event, EventDTO.class));
		}

		return retVal;
	}
}