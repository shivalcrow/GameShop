package com.web.demo.service;
/**
 * @author NguyenHuuSon
 */
import com.web.demo.entity.ReplyCommentBlog;

public interface ReplyCommentBlogService {

	<S extends ReplyCommentBlog> S save(S entity);

}
