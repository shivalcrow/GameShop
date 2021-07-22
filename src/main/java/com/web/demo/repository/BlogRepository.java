package com.web.demo.repository;
/**
 * @author NguyenHuuSon
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.demo.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>{

}
