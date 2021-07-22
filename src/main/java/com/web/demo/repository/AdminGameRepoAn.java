package com.web.demo.repository;
/**
 * @author An Nguyen
 */
import java.util.List;

/**
 * @author An Nguyen
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.demo.entity.Category;
import com.web.demo.entity.Games;

public interface AdminGameRepoAn extends JpaRepository<Games, Integer>{
	@Query(value = "SELECT * FROM games ORDER BY games.Count_sell DESC LIMIT 5", nativeQuery = true)
	List<Games> findAllTop();
	List<Games> findBycategories(Category cate);
}
