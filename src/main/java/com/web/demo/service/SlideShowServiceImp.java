package com.web.demo.service;

import java.util.List;
import java.util.Optional;
/**
 * @author Nguyen Phuong
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.web.demo.entity.SlideShow;
import com.web.demo.repository.SlideShowRepository;
@Service
public class SlideShowServiceImp implements SlideShowService{



@Override
public List<SlideShow> findAll() {
		return slideshowRepository.findAll();
	}

@Override
public void deleteById(Integer id) {
		slideshowRepository.deleteById(id);
	}

@Override
public Optional<SlideShow> findById(Integer id) {
		return slideshowRepository.findById(id);
	}

@Override
public <S extends SlideShow> S save(S entity) {
		return slideshowRepository.save(entity);
	}



@Autowired
SlideShowRepository slideshowRepository;

}
