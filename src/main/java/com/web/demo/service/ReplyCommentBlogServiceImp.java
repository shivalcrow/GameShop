package com.web.demo.service;
/**
 * @author NguyenHuuSon
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.demo.entity.ReplyCommentBlog;
import com.web.demo.repository.ReplyCommentBlogRepository;

@Service
public class ReplyCommentBlogServiceImp implements ReplyCommentBlogService{
@Override
public <S extends ReplyCommentBlog> S save(S entity) {
		return reply.save(entity);
	}

@Autowired
ReplyCommentBlogRepository reply;


}
