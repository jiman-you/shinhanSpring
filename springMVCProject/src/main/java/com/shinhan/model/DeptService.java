package com.shinhan.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.vo.DeptVO;

@Service
public class DeptService {
	@Autowired
	DeptDAOMybatis dao;

	// 모두조회
	public List<DeptVO> DeptSelectAll() {
		return dao.DeptSelectAll();
	}

	// 부서 추가
	public String deptInsert(DeptVO dept) {
		int result = dao.deptInsert(dept);
		return (result>0?"입력성공":"입력실패");
	}

	// 상세보기
	public DeptVO deptById(int deptid) {
		return dao.deptById(deptid);
	}

	// 수정
	public String deptUpdate(DeptVO dept) {
		int result =dao.deptUpdate(dept);
		return (result>0?"update성공":"update실패");		
	}
	

	// 8 직원 1건 삭제
	public String deptDelete(int deptid) {
		int result = dao.deptDelete(deptid);
		return (result>0?"delete성공":"delete실패");		
	}

}
