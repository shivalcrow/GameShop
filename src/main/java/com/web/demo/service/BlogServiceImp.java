package com.web.demo.service;
/**
 * @author NguyenHuuSon
 */
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.demo.entity.Blog;
import com.web.demo.repository.BlogRepository;

@Service
public class BlogServiceImp implements BlogService{
@Override
public <S extends Blog> S save(S entity) {
		return blogrepository.save(entity);
	}

	@Override
	public List<Blog> findAll() {
		return blogrepository.findAll();
	}

	@Override
	public Optional<Blog> findById(Integer id) {
		return blogrepository.findById(id);
	}

	@Override
	public void delete(Blog entity) {
		blogrepository.delete(entity);
	}

@Autowired
BlogRepository blogrepository;

}
