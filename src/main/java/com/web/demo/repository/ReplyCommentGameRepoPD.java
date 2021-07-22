package com.web.demo.repository;
import java.util.List;

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
public interface ReplyCommentGameRepoPD extends JpaRepository<ReplyCommentGame,Integer>{
	/*
	 * @author PhatDat
	 * 
	 */
	@Query(value = "SELECT * FROM reply_comment_game INNER JOIN comment_game "
			+ "ON reply_comment_game.Id_comment_game = comment_game.Id_comment_game "
			+ "WHERE comment_game.Id_game = ?1",
			nativeQuery = true)
	List<ReplyCommentGame> findReplyCommentGames(int idGame);
}
