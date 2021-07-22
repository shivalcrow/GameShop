package com.web.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.web.demo.entity.Games;
/*
 * @author PhatDat
 */

public interface GamesServicePD {
	public Games getGame(int i);
	
	public List<Games> getGameList();
	
	public List<Games> getGamesByFilter(String field, int i);
	
	public Page<Games> findAllPaginated(int pageNo, int pageSize);
	
	public Page<Games> findGamesByFilterPaginated(int pageNo, int pageSize, String field);
	
	public Page<Games> listAllGamesPaginated(String keyword, int pageNo, int pageSize);
	
	public List<Games> getRelatedGames(int id);
	
	public Page<Games> findAllPaginatedSorted(int pageNo, int pageSize);
	
	public Page<Games> findGamesByCategoryPaginated(int pageNo, int pageSize, int idCate);

	public int countSearchGames(String keyword);
}
