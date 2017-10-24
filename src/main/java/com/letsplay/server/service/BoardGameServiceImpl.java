package com.letsplay.server.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsplay.server.entity.BoardGame;
import com.letsplay.server.entity.Player;
import com.letsplay.server.repository.BoardGameRepository;

@Service("boardGameService")
@Transactional
public class BoardGameServiceImpl implements BoardGameService {

	@Autowired
	private BoardGameRepository boardGameRepository;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private BGGService bggService;

	public BoardGame findById(Long id) {
		return boardGameRepository.findOne(id);
	}

	public BoardGame findByName(String name) {
		return boardGameRepository.findByNameOrderByName(name);
	}

	public List<BoardGame> findByGroup(Long idGroup) {
		return boardGameRepository.findByGroup(idGroup);
	}

	public void importBoardGameFromBGG(Player player) {
		List<BoardGame> collection = bggService.importCollectionFromUser(player.getLoginBgg());
		player.setCollection(collection);
		playerService.updatePlayer(player);
	}

	public void saveBoardGame(BoardGame boardGame) {
		boardGameRepository.save(boardGame);
	}

	public void updateBoardGame(BoardGame boardGame) {
		saveBoardGame(boardGame);
	}

	public void deleteBoardGameById(Long id) {
		boardGameRepository.delete(id);
	}

	public void deleteAllBoardGames() {
		boardGameRepository.deleteAll();
	}

	public List<BoardGame> findAllBoardGames() {
		return boardGameRepository.findAll();
	}

	public boolean isBoardGameExist(BoardGame boardGame) {
		return findByName(boardGame.getName()) != null;
	}

}