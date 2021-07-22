package com.web.demo.repository;
/**
 * @author Nguyen Phuong
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.demo.entity.SlideShow;

public interface SlideShowRepository extends JpaRepository<SlideShow, Integer>{

}
