package com.letsplay.server.service;

import java.util.List;

import com.letsplay.server.entity.Group;

public interface GroupService {
	Group findById(Long id);

	Group findByName(String name);

	void saveGroup(Group group);

	void updateGroup(Group group);

	void deleteGroupById(Long id);

	void deleteAllGroups();

	List<Group> findAllGroups();

	boolean isGroupExist(Group group);
}
