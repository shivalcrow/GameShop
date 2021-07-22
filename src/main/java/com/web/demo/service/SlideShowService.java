package com.web.demo.service;

import java.util.List;
import java.util.Optional;
/**
 * @author Nguyen Phuong
 */

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.demo.entity.SlideShow;

public interface SlideShowService {

	

	<S extends SlideShow> S save(S entity);

	Optional<SlideShow> findById(Integer id);

	void deleteById(Integer id);

	List<SlideShow> findAll();

	

	

}
