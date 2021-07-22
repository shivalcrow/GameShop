package com.web.demo.service;
/**
 * @author Nguyen Phuong
 */
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.demo.entity.Games;
import com.web.demo.repository.GameRepository;

@Service
public class GamesServiceImp implements GamesService{
@Autowired
GameRepository gamerepository;
	
	@Override
	public <S extends Games> S save(S entity) {
		
		return gamerepository.save(entity);
	}

	@Override
	public Optional<Games> findById(Integer id) {
		// TODO Auto-generated method stub
		return gamerepository.findById(id);
	}
	@Override
	public List<Games> findAll() {
		// TODO Auto-generated method stub
		return gamerepository.findAll();
	}

}
