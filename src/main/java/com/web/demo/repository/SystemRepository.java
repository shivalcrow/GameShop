package com.web.demo.repository;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.demo.entity.Systems;

import java.time.LocalDate;
import java.util.Date;

import java.util.Optional;

@Repository
public interface SystemRepository extends JpaRepository<Systems, Integer>{
	@Query(value = "SELECT * FROM systems as s WHERE s.Date LIKE :date%", nativeQuery = true)
	Systems findByDateLike(@Param("date") String date);
}
