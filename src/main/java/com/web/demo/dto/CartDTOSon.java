package com.web.demo.dto;

import com.web.demo.entity.Games;

public class CartDTOSon {
public Games getGames() {
		return games;
	}

	public void setGames(Games games) {
		this.games = games;
	}

private Games games;

public CartDTOSon(Games games) {
	super();
	this.games = games;
}
public CartDTOSon() {
	
}
}
