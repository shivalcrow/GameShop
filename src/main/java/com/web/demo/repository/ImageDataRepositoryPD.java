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
	@Query(value = "SELECT * FROM image_data where NOT Id_game = ?1 ORDER BY Id_game",
			countQuery = "SELECT count(*) FROM image_data",
			nativeQuery = true)
	List<ImageData> findRecommendGame(int id);
}
