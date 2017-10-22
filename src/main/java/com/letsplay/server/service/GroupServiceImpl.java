package com.letsplay.server.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsplay.server.entity.Group;
import com.letsplay.server.repository.GroupRepository;

@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService{
 
    @Autowired
    private GroupRepository groupRepository;
 
    public Group findById(Long id) {
        return groupRepository.findOne(id);
    }
 
    public Group findByName(String name) {
        return groupRepository.findByName(name);
    }
 
    public void saveGroup(Group group) {
        groupRepository.save(group);
    }
 
    public void updateGroup(Group group){
        saveGroup(group);
    }
 
    public void deleteGroupById(Long id){
        groupRepository.delete(id);
    }
 
    public void deleteAllGroups(){
        groupRepository.deleteAll();
    }
 
    public List<Group> findAllGroups(){
        return groupRepository.findAll();
    }
 
    public boolean isGroupExist(Group group) {
        return findByName(group.getName()) != null;
    }
 
}