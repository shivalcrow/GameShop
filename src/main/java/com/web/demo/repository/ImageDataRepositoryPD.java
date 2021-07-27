package com.web.demo.repository;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.web.demo.entity.Games;
import com.web.demo.entity.ImageData;

/*
 * 
 * @author PhatDat
 */
@Repository
public interface ImageDataRepositoryPD extends JpaRepository<ImageData, Integer>{
	/*
	 * @author PhatDat
	 * method get list of games by id_game without Pagination
	 */
	@Query(value = "SELECT * FROM image_data where Id_game = ?1",
			countQuery = "SELECT count(*) FROM image_data",
			nativeQuery = true)
	List<ImageData> findImageGame(int id);
	
	/*
	 * @author PhatDat
	 * method get list of games by id_game without Pagination
	 */
	@Query(value = "SELECT * FROM image_data WHERE Id_game IN"
			+ " (SELECT Id_game FROM game_category WHERE NOT Id_game = ?1 AND Id_category IN"
			+ " (SELECT Id_category FROM game_category WHERE Id_game = ?2))",
			countQuery = "SELECT count(*) FROM image_data",
			nativeQuery = true)
	List<ImageData> findRecommendGame(int id1, int id2);
}
