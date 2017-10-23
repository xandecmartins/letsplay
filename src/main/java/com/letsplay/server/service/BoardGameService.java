package com.letsplay.server.service;

import java.util.List;

import com.letsplay.server.entity.BoardGame;
import com.letsplay.server.entity.Player;

public interface BoardGameService {
	BoardGame findById(Long id);

	BoardGame findByName(String name);
	
	List<BoardGame> findByGroup(Long idGroup);
	
	void importBoardGameFromBGG(Player player);

	void saveBoardGame(BoardGame boardGame);

	void updateBoardGame(BoardGame boardGame);

	void deleteBoardGameById(Long id);

	void deleteAllBoardGames();

	List<BoardGame> findAllBoardGames();

	boolean isBoardGameExist(BoardGame boardGame);
}
