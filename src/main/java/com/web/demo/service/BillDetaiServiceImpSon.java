package com.web.demo.service;
import java.util.List;

/**
 * @author NguyenHuuSon
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.demo.entity.Bill;
import com.web.demo.entity.BillDetail;
import com.web.demo.entity.Games;
import com.web.demo.repository.BillDetailRepositorySon;

@Service
public class BillDetaiServiceImpSon implements BillDetailServiceSon{
@Override
public List<BillDetail> findByBill(Bill bill) {
		return billdetail.findByBill(bill);
	}


@Override
public <S extends BillDetail> S save(S entity) {
		return billdetail.save(entity);
	}


@Override
public <S extends BillDetail> BillDetail addbilldetail(Bill bill,Games game) {
	BillDetail billde=new BillDetail();
	billde.setBill(bill);
	billde.setGames(game);
	billde.setPrice((long)game.getPriceFix());
	 
	return  billdetail.save(billde);
	
}
@Autowired
BillDetailRepositorySon billdetail;


}
