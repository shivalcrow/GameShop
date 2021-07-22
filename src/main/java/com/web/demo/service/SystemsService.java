package com.web.demo.service;
/**
 * @author NguyenHuuSon
 */
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.web.demo.entity.Systems;

public interface SystemsService {

	Optional<Systems> findById(Integer id);

	List<Systems> findAll();

	<S extends Systems> S save(S entity);

	Systems findByDateLike(String date);

}
