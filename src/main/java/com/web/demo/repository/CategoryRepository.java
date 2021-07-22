package com.web.demo.repository;
/**
 * 
 * 
 * @author đạt hà
 * 
 * 
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.demo.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
