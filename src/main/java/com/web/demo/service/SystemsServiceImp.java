package com.web.demo.service;
/**
 * @author NguyenHuuSon
 */
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.demo.entity.Systems;
import com.web.demo.repository.SystemRepository;

@Service
public class SystemsServiceImp implements SystemsService{
	@Override
	public Systems findByDateLike(String date) {
		return system.findByDateLike(date);
	}

	@Override
	public <S extends Systems> S save(S entity) {
		return system.save(entity);
	}

	@Override
	public List<Systems> findAll() {
		return system.findAll();
	}

	@Override
	public Optional<Systems> findById(Integer id) {
		return system.findById(id);
	}

	@Autowired
	SystemRepository system;
	

}
