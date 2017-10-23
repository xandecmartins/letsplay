package com.letsplay.server.controller;
import java.util.List;

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

import com.letsplay.server.entity.Group;
import com.letsplay.server.service.GroupService;
import com.letsplay.server.util.CustomErrorType;
 
@RestController
@RequestMapping("/api")
public class GroupController {
 
    public static final Logger logger = LoggerFactory.getLogger(GroupController.class);
 
    @Autowired
    private GroupService groupService; //Service which will do all data retrieval/manipulation work
 
    // -------------------Retrieve All Groups---------------------------------------------
 
    @RequestMapping(value = "/group/", method = RequestMethod.GET)
    public ResponseEntity<List<Group>> listAllGroups() {
        List<Group> groups = groupService.findAllGroups();
        if (groups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);
    }
 
    // -------------------Retrieve Single Group------------------------------------------
 
    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getGroup(@PathVariable("id") long id) {
        logger.info("Fetching Group with id {}", id);
        Group group = groupService.findById(id);
        if (group == null) {
            logger.error("Group with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Group with id " + id 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Group>(group, HttpStatus.OK);
    }
 
    // -------------------Create a Group-------------------------------------------
 
    @RequestMapping(value = "/group/", method = RequestMethod.POST)
    public ResponseEntity<?> createGroup(@RequestBody Group group, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Group : {}", group);
 
        if (groupService.isGroupExist(group)) {
            logger.error("Unable to create. A Group with name {} already exist", group.getName());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Group with login " + 
            group.getName() + " already exist."),HttpStatus.CONFLICT);
        }
        groupService.saveGroup(group);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/group/{id}").buildAndExpand(group.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
 
    // ------------------- Update a Group ------------------------------------------------
 
    @RequestMapping(value = "/group/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateGroup(@PathVariable("id") long id, @RequestBody Group group) {
        logger.info("Updating Group with id {}", id);
 
        Group currentGroup = groupService.findById(id);
 
        if (currentGroup == null) {
            logger.error("Unable to update. Group with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to upate. Group with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
 
        currentGroup.setName(group.getName());
 
        groupService.updateGroup(currentGroup);
        return new ResponseEntity<Group>(currentGroup, HttpStatus.OK);
    }
 
    // ------------------- Delete a Group-----------------------------------------
 
    @RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGroup(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Group with id {}", id);
 
        Group group = groupService.findById(id);
        if (group == null) {
            logger.error("Unable to delete. Group with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Group with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        groupService.deleteGroupById(id);
        return new ResponseEntity<Group>(HttpStatus.NO_CONTENT);
    }
 
    // ------------------- Delete All Groups-----------------------------
 
    @RequestMapping(value = "/group/", method = RequestMethod.DELETE)
    public ResponseEntity<Group> deleteAllGroups() {
        logger.info("Deleting All Groups");
 
        groupService.deleteAllGroups();
        return new ResponseEntity<Group>(HttpStatus.NO_CONTENT);
    }
}