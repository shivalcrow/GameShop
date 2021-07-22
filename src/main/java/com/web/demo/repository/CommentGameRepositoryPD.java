package com.web.demo.repository;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.web.demo.entity.CommentGame;
import com.web.demo.entity.ReplyCommentGame;

/*
 * 
 * @author PhatDat
 */
@Repository
public interface CommentGameRepositoryPD extends JpaRepository<CommentGame,Integer>{
	/*
	 * @author PhatDat
	 * 
	 */
	@Query(value = "SELECT * FROM comment_game WHERE Id_game = ?1",
			countQuery = "SELECT count(*) FROM comment_game",
			nativeQuery = true)
	List<CommentGame> findCommentGames(int idGame);
}
