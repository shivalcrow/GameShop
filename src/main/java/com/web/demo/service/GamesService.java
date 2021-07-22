package com.web.demo.service;

import java.util.List;
import java.util.Optional;
/**
 * @author Nguyen Phuong
 */


import com.web.demo.entity.Games;



public interface GamesService {

	

	<S extends Games> S save(S entity);

	Optional<Games> findById(Integer id);


	List<Games> findAll();

	

	

}
