package com.web.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.demo.entity.CommentGame;
import com.web.demo.entity.ReplyCommentGame;
import com.web.demo.entity.Users;
import com.web.demo.repository.CommentGameRepositoryPD;
import com.web.demo.repository.DiscountRepositoryPD;
import com.web.demo.repository.GamesRepositoryPD;
import com.web.demo.repository.ReplyCommentGameRepoPD;
import com.web.demo.repository.UsersRepositorySon;

/*
 * @author PhatDat
 */
@Service
public class UserCommentGameServiceImpPD implements UserCommentGameServicePD{
	@Autowired
	CommentGameRepositoryPD cmtGameRepo;
	@Autowired
	ReplyCommentGameRepoPD repCmtGameRepo;
	@Autowired
	private GamesRepositoryPD gamesRepository;
	@Autowired
	UsersRepositorySon usersRepository;
	
	@Override
	public List<CommentGame> getCommentGame(int idGame) {
		return cmtGameRepo.findCommentGames(idGame);
	}

	@Override
	public List<ReplyCommentGame> getUserReplyComment(int idCommentGame) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public CommentGame addCommentGame(int idGame, Users idUser, String cmt) {
		return cmtGameRepo.saveAndFlush(
				new CommentGame(gamesRepository.getById(idGame),
						idUser, cmt));
	}
	
}
