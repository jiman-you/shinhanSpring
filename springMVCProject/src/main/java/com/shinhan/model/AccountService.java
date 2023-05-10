package com.shinhan.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)   
public class AccountService {
	
	@Autowired
	AccountDAOMybatis dao;
	
	//이체 서비스
	public void transfer() {
		dao.update1();//OK
		dao.update2();//NOK
	}
	
	public void update1() {
		dao.update1();
	}
	
	public void update2() {
		dao.update2();
	}
}
