package com.web.demo.repository;
/**
 * @author AnNguyen
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.demo.entity.Bill;

@Repository
public interface AdminBillRepoAn extends JpaRepository<Bill, Integer> {
	@Query(value="SELECT *, COUNT(*) FROM bill GROUP BY Id_users ORDER BY COUNT(*) DESC LIMIT 5", nativeQuery = true)
	List<Bill> findAllTop();
	@Query(value="SELECT COUNT(*) FROM bill as b WHERE b.Date LIKE :date%", nativeQuery = true)
	long findCount(@Param("date") String date);
	@Query(value="SELECT SUM(Total_price) FROM bill as b WHERE b.Date LIKE :date%", nativeQuery = true)
	String findTotalPrice(@Param("date") String date);
}
