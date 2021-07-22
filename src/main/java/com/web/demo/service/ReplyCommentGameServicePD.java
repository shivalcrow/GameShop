package com.web.demo.service;

import java.util.List;

import com.web.demo.entity.ReplyCommentGame;
import com.web.demo.entity.Users;

public interface ReplyCommentGameServicePD {
	public ReplyCommentGame addReplyCommentGame(int idCommentGame, Users idUser, String repCmt);
	public List<ReplyCommentGame> getReplyCommentGame(int idGame);
}
