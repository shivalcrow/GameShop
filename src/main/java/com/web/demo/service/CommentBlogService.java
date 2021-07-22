package com.web.demo.service;
/**
 * @author NguyenHuuSon
 */
import java.util.List;
import java.util.Optional;

import com.web.demo.entity.CommentBlog;

public interface CommentBlogService {

	Optional<CommentBlog> findById(Integer id);

	List<CommentBlog> findAll();

	<S extends CommentBlog> S save(S entity);

	void delete(CommentBlog entity);

}
