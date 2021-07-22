package com.web.demo.service;
/**
 * @author NguyenHuuSon
 */
import java.util.List;
import java.util.Optional;

import com.web.demo.entity.Games;
import com.web.demo.entity.ImageData;

public interface GameImageServiceSon {

	List<ImageData> findAll();

	Optional<ImageData> findById(Integer id);

	<S extends ImageData> S save(S entity);

	List<ImageData> findByGames(Games games);

}
