package com.web.demo.service;
import java.util.List;

import com.web.demo.entity.CommentGame;
import com.web.demo.entity.ReplyCommentGame;
import com.web.demo.entity.Users;

public interface UserCommentGameServicePD {
	public List<CommentGame> getCommentGame(int idGame);
	public List<ReplyCommentGame> getUserReplyComment(int idCommentGame);
	public CommentGame addCommentGame(int idGame, Users idUser, String cmt);
}
