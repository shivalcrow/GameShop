package com.web.demo.service;
import java.util.List;

/**
 * @author NguyenHuuSon
 */
import com.web.demo.entity.Bill;
import com.web.demo.entity.BillDetail;
import com.web.demo.entity.Games;

public interface BillDetailServiceSon {

	<S extends BillDetail> S save(S entity);

	<S extends BillDetail> BillDetail addbilldetail(Bill bill, Games game);

	List<BillDetail> findByBill(Bill bill);

	

}
