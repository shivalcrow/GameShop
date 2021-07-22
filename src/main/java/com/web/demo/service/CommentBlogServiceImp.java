package com.web.demo.service;
/**
 * @author NguyenHuuSon
 */
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.demo.entity.CommentBlog;
import com.web.demo.repository.BlogCommentDatHa;

@Service
public class CommentBlogServiceImp implements CommentBlogService{
@Autowired
BlogCommentDatHa commentblog;

@Override
public <S extends CommentBlog> S save(S entity) {
	return commentblog.save(entity);
}

@Override
public List<CommentBlog> findAll() {
	return commentblog.findAll();
}

@Override
public Optional<CommentBlog> findById(Integer id) {
	return commentblog.findById(id);
}

@Override
public void delete(CommentBlog entity) {
	commentblog.delete(entity);
}


}
