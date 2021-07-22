package com.web.demo.repository;
/**
 * @author Nguyen Phuong
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.demo.entity.Games;


public interface GameRepository extends JpaRepository<Games, Integer>{

}
