package com.web.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.demo.entity.CommentGame;
import com.web.demo.entity.ReplyCommentGame;
import com.web.demo.entity.Users;
import com.web.demo.repository.CommentGameRepositoryPD;
import com.web.demo.repository.ReplyCommentGameRepoPD;

@Service
public class ReplyCommentGameServiceImpPD implements ReplyCommentGameServicePD {

	@Autowired
	private ReplyCommentGameRepoPD repCmtgameRepo;
	
	@Autowired
	private CommentGameRepositoryPD cmtgameRepo;
	
	@Override
	public List<ReplyCommentGame> getReplyCommentGame(int idGame) {
		return repCmtgameRepo.findReplyCommentGames(idGame);
	}

	@Override
	public ReplyCommentGame addReplyCommentGame(int idCommentGame, Users idUser, String repCmt) {
		CommentGame cmt = cmtgameRepo.getOne(idCommentGame);
		return repCmtgameRepo.saveAndFlush( new ReplyCommentGame(cmt, idUser, repCmt));
	}

}
