package com.shinhan.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.vo.DeptVO;

@Service
public class DeptService {
	@Autowired
	DeptDAOMybatis dao;

	// �����ȸ
	public List<DeptVO> DeptSelectAll() {
		return dao.DeptSelectAll();
	}

	// �μ� �߰�
	public String deptInsert(DeptVO dept) {
		int result = dao.deptInsert(dept);
		return (result>0?"�Է¼���":"�Է½���");
	}

	// �󼼺���
	public DeptVO deptById(int deptid) {
		return dao.deptById(deptid);
	}

	// ����
	public String deptUpdate(DeptVO dept) {
		int result =dao.deptUpdate(dept);
		return (result>0?"update����":"update����");		
	}
	

	// 8 ���� 1�� ����
	public String deptDelete(int deptid) {
		int result = dao.deptDelete(deptid);
		return (result>0?"delete����":"delete����");		
	}

}
