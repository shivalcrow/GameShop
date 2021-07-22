package com.web.demo.repository;
/**
 * @author AnNguyen
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.demo.entity.ImageData;
@Repository
public interface ImageRepoAn extends JpaRepository<ImageData, Integer>{

}
