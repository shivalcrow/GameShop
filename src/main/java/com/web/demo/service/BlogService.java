package com.web.demo.service;
/**
 * @author NguyenHuuSon
 */
import java.util.List;
import java.util.Optional;

import com.web.demo.entity.Blog;

public interface BlogService {

	void delete(Blog entity);

	Optional<Blog> findById(Integer id);

	List<Blog> findAll();

	<S extends Blog> S save(S entity);

}
