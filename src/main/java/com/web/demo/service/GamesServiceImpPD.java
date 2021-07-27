package com.web.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.web.demo.entity.Games;
import com.web.demo.entity.Users;
import com.web.demo.repository.GamesRepositoryPD;
/*
 * @author PhatDat
 */
@Service
public class GamesServiceImpPD implements GamesServicePD {
	@Override
	public <S extends Games> S save(S entity) {
		return gamesRepository.save(entity);
	}

	@Override
	public Optional<Games> findById(Integer id) {
		return gamesRepository.findById(id);
	}

	@Autowired
	private GamesRepositoryPD gamesRepository;
	
	/*
	 * @author PhatDat
	 * get a game
	 */
	@Override
	public Games getGame(int i) {
		
		return gamesRepository.getOne(i);
	}
	
	/*
	 * @author PhatDat
	 * get list of all games
	 */
	@Override
	public List<Games> getGameList(){
		
		return gamesRepository.findAll();
	}

	
	/*
	 * @author PhatDat
	 * get list of games by filters
	 */
	@Override
	public List<Games> getGamesByFilter(String field, int i){
		return gamesRepository.findGamesByFilter(PageRequest.of(0, i, Sort.Direction.DESC, field));
	}
	
	/*
	 * @author PhatDat
	 * get list of all games with Pagination
	 */
	@Override
	public Page<Games> findAllPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.gamesRepository.findAll(pageable);
	}
	
	/*
	 * @author PhatDat
	 * get list of games by filters with Pagination
	 */
	@Override
	public Page<Games> findGamesByFilterPaginated(int pageNo, int pageSize, String field) {
		if(!field.equalsIgnoreCase("discount")) {
			Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, field);
			return this.gamesRepository.findGamesByFilterPaginated(pageable);
		}
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.gamesRepository.findGamesByDiscountPaginated(pageable);
	}
	
	/**
	 * @author PhatDat
	 * search result of games by term from user with Pagination
	 */
	@Override
	public Page<Games> listAllGamesPaginated(String keyword, int pageNo, int pageSize) {
        if (keyword != null) {
            return this.gamesRepository.search(keyword, PageRequest.of(pageNo - 1, pageSize));
        }
        return this.findAllPaginated(pageNo, pageSize);
    }
	
	/**
	 * @author PhatDat
	 * count number of searching result of games 
	 */
	@Override
	public int countSearchGames(String keyword) {
		if (keyword != null) {
			return this.gamesRepository.countSearch(keyword);
		}
		return 0;
    }
	
	/**
	 * @author PhatDat
	 * search result of games by term from user with Pagination
	 */
	@Override
	public List<Games> getRelatedGames(int id) {
        return gamesRepository.getRecommendGames(id);
    }
	
	/*
	 * @author PhatDat
	 * get list of all games with Pagination
	 */
	@Override
	public Page<Games> findAllPaginatedSorted(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.gamesRepository.findAllGameSorted(pageable);
	}

	@Override
	public Page<Games> findGamesByCategoryPaginated(int pageNo, int pageSize, int idCate) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.gamesRepository.findGamesByCateGoryPaginated(idCate, pageable);
	}

	@Override
	public List<Games> getListRecommendGames(int id1, int id2) {	
		return this.gamesRepository.findRecommendGames(id1, id2);
	}

	@Override
	public Integer getActiveGame(int idGame, int idUser) {
		return this.gamesRepository.findActiveGame(idGame, idUser);
	}

}
