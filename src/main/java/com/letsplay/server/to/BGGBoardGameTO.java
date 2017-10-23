package com.letsplay.server.to;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BGGBoardGameTO implements Serializable {

	private static final long serialVersionUID = -2734252371617157439L;
	
	private Long gameId;

	private String name;
	
	private String image;
	
	private String thumbnail;
	
	private Integer minPlayers;
	
	private Integer maxPlayers;
	
	private Integer playingTime;
	
	private Boolean isExpansion;
	
	private Integer yearPublished;
	
	private Float bggRating;
	
	private Float averageRating;
	
	private Integer rank;
	
	private Integer numPlays;
	
	private Integer rating;
	
	private Boolean owned;
	
	private Boolean preOrdered;
	
	private Boolean forTrade;
	
	private Boolean previousOwned;
	
	private Boolean want;
	
	private Boolean wantToPlay;
	
	private Boolean wantToBuy;
	
	private Boolean wishList;
	
	private String userComment;
	
	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Integer getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(Integer minPlayers) {
		this.minPlayers = minPlayers;
	}

	public Integer getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(Integer maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public Integer getPlayingTime() {
		return playingTime;
	}

	public void setPlayingTime(Integer playingTime) {
		this.playingTime = playingTime;
	}

	public Boolean getIsExpansion() {
		return isExpansion;
	}

	public void setIsExpansion(Boolean isExpansion) {
		this.isExpansion = isExpansion;
	}

	public Integer getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(Integer yearPublished) {
		this.yearPublished = yearPublished;
	}

	public Float getBggRating() {
		return bggRating;
	}

	public void setBggRating(Float bggRating) {
		this.bggRating = bggRating;
	}

	public Float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getNumPlays() {
		return numPlays;
	}

	public void setNumPlays(Integer numPlays) {
		this.numPlays = numPlays;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Boolean getOwned() {
		return owned;
	}

	public void setOwned(Boolean owned) {
		this.owned = owned;
	}

	public Boolean getPreOrdered() {
		return preOrdered;
	}

	public void setPreOrdered(Boolean preOrdered) {
		this.preOrdered = preOrdered;
	}

	public Boolean getForTrade() {
		return forTrade;
	}

	public void setForTrade(Boolean forTrade) {
		this.forTrade = forTrade;
	}

	public Boolean getPreviousOwned() {
		return previousOwned;
	}

	public void setPreviousOwned(Boolean previousOwned) {
		this.previousOwned = previousOwned;
	}

	public Boolean getWant() {
		return want;
	}

	public void setWant(Boolean want) {
		this.want = want;
	}

	public Boolean getWantToPlay() {
		return wantToPlay;
	}

	public void setWantToPlay(Boolean wantToPlay) {
		this.wantToPlay = wantToPlay;
	}

	public Boolean getWantToBuy() {
		return wantToBuy;
	}

	public void setWantToBuy(Boolean wantToBuy) {
		this.wantToBuy = wantToBuy;
	}

	public Boolean getWishList() {
		return wishList;
	}

	public void setWishList(Boolean wishList) {
		this.wishList = wishList;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}
}
