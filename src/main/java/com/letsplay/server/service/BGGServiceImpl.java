package com.letsplay.server.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import com.letsplay.server.AppProperties;
import com.letsplay.server.entity.BoardGame;
import com.letsplay.server.to.BGGBoardGameTO;

@Service
public class BGGServiceImpl implements BGGService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AppProperties appProperties;
	
	@Override
	public List<BoardGame> importCollectionFromUser(String userbgg) {
		ResponseEntity<List<BGGBoardGameTO>> bggResponse =
		        restTemplate.exchange(appProperties.getUrlBGG()+userbgg,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<BGGBoardGameTO>>() {
		            });
		List<BGGBoardGameTO> collection = bggResponse.getBody();
		
		List<BoardGame> retVal = new LinkedList<>();
		
		for (BGGBoardGameTO bggBoardGameTO : collection) {
			BoardGame game = new BoardGame();
			game.setId(bggBoardGameTO.getGameId());
			game.setName(bggBoardGameTO.getName());
			retVal.add(game);
		}
		
		return retVal;
	}

	
	
}
