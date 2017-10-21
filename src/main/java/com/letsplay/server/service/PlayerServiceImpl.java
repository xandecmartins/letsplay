package com.letsplay.server.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsplay.server.entity.Player;
import com.letsplay.server.repository.PlayerRepository;

@Service("playerService")
@Transactional
public class PlayerServiceImpl implements PlayerService{
 
    @Autowired
    private PlayerRepository playerRepository;
 
    public Player findById(Long id) {
        return playerRepository.findOne(id);
    }
 
    public Player findByLogin(String login) {
        return playerRepository.findByLogin(login);
    }
 
    public void savePlayer(Player player) {
        playerRepository.save(player);
    }
 
    public void updatePlayer(Player player){
        savePlayer(player);
    }
 
    public void deletePlayerById(Long id){
        playerRepository.delete(id);
    }
 
    public void deleteAllPlayers(){
        playerRepository.deleteAll();
    }
 
    public List<Player> findAllPlayers(){
        return playerRepository.findAll();
    }
 
    public boolean isPlayerExist(Player player) {
        return findByLogin(player.getLogin()) != null;
    }
 
}