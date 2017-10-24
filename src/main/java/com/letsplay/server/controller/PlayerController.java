package com.letsplay.server.controller;
import java.util.ArrayList;
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

import com.letsplay.server.dto.PlayerDTO;
import com.letsplay.server.entity.Player;
import com.letsplay.server.service.BoardGameService;
import com.letsplay.server.service.PlayerService;
import com.letsplay.server.util.CustomErrorType;
 
@RestController
@RequestMapping("/api")
public class PlayerController {
 
    public static final Logger logger = LoggerFactory.getLogger(PlayerController.class);
 
    @Autowired
    private PlayerService playerService; //Service which will do all data retrieval/manipulation work
    
    @Autowired
    private BoardGameService boardGameService;
    
    @Autowired
	private ModelMapper modelMapper;
 
    // -------------------Retrieve All Players---------------------------------------------
 
    @RequestMapping(value = "/player/", method = RequestMethod.GET)
    public ResponseEntity<List<PlayerDTO>> listAllPlayers() {
        List<Player> players = playerService.findAllPlayers();
        if (players.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<PlayerDTO>>(convertToDto(players), HttpStatus.OK);
    }
 
    // -------------------Retrieve Single Player------------------------------------------
 
    @RequestMapping(value = "/player/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPlayer(@PathVariable("id") long id) {
        logger.info("Fetching Player with id {}", id);
        Player player = playerService.findById(id);
        if (player == null) {
            logger.error("Player with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Player with id " + id 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PlayerDTO>(convertToDto(player), HttpStatus.OK);
    }
 
    // -------------------Create a Player-------------------------------------------
 
    @RequestMapping(value = "/player/", method = RequestMethod.POST)
    public ResponseEntity<?> createPlayer(@RequestBody Player player, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Player : {}", player);
 
        if (playerService.isPlayerExist(player)) {
            logger.error("Unable to create. A Player with name {} already exist", player.getLogin());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Player with login " + 
            player.getLogin() + " already exist."),HttpStatus.CONFLICT);
        }
        playerService.savePlayer(player);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/player/{id}").buildAndExpand(player.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/player/{id}/import", method = RequestMethod.POST)
    public ResponseEntity<?> importCollectionPlayer(@PathVariable("id") long id) {
        logger.info("Importing Collection of Player with id {}", id);
 
        Player currentPlayer = playerService.findById(id);
 
        if (currentPlayer == null) {
            logger.error("Unable to import collection of Player with id {}, player not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to import collection of Player with id " + id + ", player not found."),
                    HttpStatus.NOT_FOUND);
        }
 
        boardGameService.importBoardGameFromBGG(currentPlayer);
        return new ResponseEntity<PlayerDTO>(convertToDto(currentPlayer), HttpStatus.OK);
    }
 
    // ------------------- Update a Player ------------------------------------------------
 
    @RequestMapping(value = "/player/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePlayer(@PathVariable("id") long id, @RequestBody Player player) {
        logger.info("Updating Player with id {}", id);
 
        Player currentPlayer = playerService.findById(id);
 
        if (currentPlayer == null) {
            logger.error("Unable to update. Player with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to upate. Player with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
 
        currentPlayer.setLogin(player.getLogin());
 
        playerService.updatePlayer(currentPlayer);
        return new ResponseEntity<PlayerDTO>(convertToDto(currentPlayer), HttpStatus.OK);
    }
 
    // ------------------- Delete a Player-----------------------------------------
 
    @RequestMapping(value = "/player/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePlayer(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Player with id {}", id);
 
        Player player = playerService.findById(id);
        if (player == null) {
            logger.error("Unable to delete. Player with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Player with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        playerService.deletePlayerById(id);
        return new ResponseEntity<PlayerDTO>(HttpStatus.NO_CONTENT);
    }
 
    // ------------------- Delete All Players-----------------------------
 
    @RequestMapping(value = "/player/", method = RequestMethod.DELETE)
    public ResponseEntity<Player> deleteAllPlayers() {
        logger.info("Deleting All Players");
 
        playerService.deleteAllPlayers();
        return new ResponseEntity<Player>(HttpStatus.NO_CONTENT);
    }
    
    private PlayerDTO convertToDto(Player player) {
		return modelMapper.map(player, PlayerDTO.class);
	}

	private List<PlayerDTO> convertToDto(List<Player> listPlayer) {
		List<PlayerDTO> retVal = new ArrayList<>();
		for (Player player : listPlayer) {
			retVal.add(modelMapper.map(player, PlayerDTO.class));
		}

		return retVal;
	}
}