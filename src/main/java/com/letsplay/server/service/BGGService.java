package com.letsplay.server.service;

import java.util.List;

import com.letsplay.server.entity.BoardGame;

public interface BGGService {

	List<BoardGame> importCollectionFromUser(String userbgg);
}
