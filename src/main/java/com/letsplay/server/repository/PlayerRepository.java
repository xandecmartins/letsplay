package com.letsplay.server.repository;

import org.springframework.data.repository.CrudRepository;

import com.letsplay.server.entity.Player;

public interface PlayerRepository extends CrudRepository<Player, Long>{

}
