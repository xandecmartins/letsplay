package com.letsplay.server.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letsplay.server.dto.BoardGameDTO;
import com.letsplay.server.entity.BoardGame;
import com.letsplay.server.service.BoardGameService;
import com.letsplay.server.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class BoardGameController {

	public static final Logger logger = LoggerFactory.getLogger(BoardGameController.class);

	@Autowired
	private BoardGameService boardGameService; // Service which will do all data retrieval/manipulation work

	@Autowired
	private ModelMapper modelMapper;

	// -------------------Retrieve All
	// BoardGames---------------------------------------------

	@RequestMapping(value = "/board_game/", method = RequestMethod.GET)
	public ResponseEntity<List<BoardGameDTO>> listAllBoardGames(@RequestParam("group") Optional<Long> idGroup) {
		List<BoardGame> boardGames = null;

		if (idGroup.isPresent()) {
			boardGames = boardGameService.findByGroup(idGroup.get());
		} else {
			boardGames = boardGameService.findAllBoardGames();
		}

		if (boardGames.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<BoardGameDTO>>(convertToDto(boardGames), HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// BoardGame------------------------------------------

	@RequestMapping(value = "/board_game/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBoardGame(@PathVariable("id") long id) {
		logger.info("Fetching BoardGame with id {}", id);
		BoardGame boardGame = boardGameService.findById(id);
		if (boardGame == null) {
			logger.error("BoardGame with id {} not found.", id);
			return new ResponseEntity<>(new CustomErrorType("BoardGame with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BoardGameDTO>(convertToDto(boardGame), HttpStatus.OK);
	}

	// ------------------- Update a BoardGame
	// ------------------------------------------------

	@RequestMapping(value = "/board_game/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBoardGame(@PathVariable("id") long id, @RequestBody BoardGame boardGame) {
		logger.info("Updating BoardGame with id {}", id);

		BoardGame currentBoardGame = boardGameService.findById(id);

		if (currentBoardGame == null) {
			logger.error("Unable to update. BoardGame with id {} not found.", id);
			return new ResponseEntity<>(new CustomErrorType("Unable to upate. BoardGame with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentBoardGame.setName(boardGame.getName());

		boardGameService.updateBoardGame(currentBoardGame);
		return new ResponseEntity<BoardGameDTO>(convertToDto(currentBoardGame), HttpStatus.OK);
	}

	// ------------------- Delete a
	// BoardGame-----------------------------------------

	@RequestMapping(value = "/board_game/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBoardGame(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting BoardGame with id {}", id);

		BoardGame boardGame = boardGameService.findById(id);
		if (boardGame == null) {
			logger.error("Unable to delete. BoardGame with id {} not found.", id);
			return new ResponseEntity<>(
					new CustomErrorType("Unable to delete. BoardGame with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		boardGameService.deleteBoardGameById(id);
		return new ResponseEntity<BoardGameDTO>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All BoardGames-----------------------------

	@RequestMapping(value = "/board_game/", method = RequestMethod.DELETE)
	public ResponseEntity<BoardGameDTO> deleteAllBoardGames() {
		logger.info("Deleting All BoardGames");

		boardGameService.deleteAllBoardGames();
		return new ResponseEntity<BoardGameDTO>(HttpStatus.NO_CONTENT);
	}

	private BoardGameDTO convertToDto(BoardGame boardGame) {
		return modelMapper.map(boardGame, BoardGameDTO.class);
	}

	private List<BoardGameDTO> convertToDto(List<BoardGame> listBoardGame) {
		List<BoardGameDTO> retVal = new ArrayList<>();
		for (BoardGame boardGame : listBoardGame) {
			retVal.add(modelMapper.map(boardGame, BoardGameDTO.class));
		}

		return retVal;
	}
}