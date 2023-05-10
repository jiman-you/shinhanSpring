package com.shinhan.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.util.OracleUtil;

import com.shinhan.vo.EmpVO;

//DAO(Data Access Object): DB업무 ..CRUD ..insert,select,update,delete
@Repository // @Component +DAO
public class EmpDAOMybatis {

	@Autowired //타입이 같으면 자동으로 injection
	SqlSession sqlSession;
	static final String NAMESPACE= "co.kr.firstzone.emp.";
	static final Logger LOG = LoggerFactory.getLogger(EmpDAOMybatis.class);
	
	//전부조회
	public List<EmpVO> selectAll() {
		List<EmpVO> emplist = sqlSession.selectList(NAMESPACE+"selectAll");
		LOG.info(emplist.toString());
		return emplist;
	}

	// 2특정 직원 조회
	public EmpVO selectById(int empid) {
		EmpVO emp = sqlSession.selectOne(NAMESPACE+"selectById",empid);
		LOG.info(emp.toString());
		return emp;
	}

	// 3특정 부서의 직원을 조회
	public List<EmpVO> selectByDept(int deptid) {
		List<EmpVO> emplist = sqlSession.selectList(NAMESPACE+"selectByDept",deptid);
		LOG.info(emplist.toString());
		return emplist;
	}

	
	// 4특정 부서, jobid, salary이상의 직원 조회
	public List<EmpVO> selectByCondition(Integer[] deptid, String jobid, Double salary, Date hiredate) {
		//1.map이용
		Map<String, Object> mapData = new HashMap<String, Object>();
		//부서가 선택되지 않거나 전체인 경우
		if(deptid.length==0||deptid[0]==0) {
			deptid=null;
			}
		mapData.put("deptid", deptid);
		mapData.put("jobid", jobid);
		mapData.put("salary", salary);
		mapData.put("hiredate", hiredate);
		
		//2.vo이용
//		List<EmpVO> emplistResult=new ArrayList<EmpVO>();
//		
//		for(Integer dept:deptid) {
//			EmpVO emp = new EmpVO();
//			emp.setDepartment_id(dept);
//			emp.setJob_id(jobid);
//			emp.setSalary(salary);
//			emp.setHire_date(hiredate);
//			
//			List<EmpVO> emplist = sqlSession.selectList(NAMESPACE+"selectByCondition2",emp);
//			LOG.info(emplist.toString());
//			emplist.forEach(aa->emplistResult.add(aa));
//			
//		}
		List<EmpVO> emplist = sqlSession.selectList(NAMESPACE+"selectByCondition3",mapData);
		return emplist;
		//return emplistResult;
	}

	// 6신규 직원 등록
	public int empInsert(EmpVO emp) {
		//인서트에 영향을 받은 건 수가 들어옴
		int resultCount =  sqlSession.insert(NAMESPACE+"empInsert",emp);
		LOG.info(resultCount+"건 입력");
		return resultCount;
	}

	// 7 직원 수정
	public int empUpdate(EmpVO emp) {
		//인서트에 영향을 받은 건 수가 들어옴
		int resultCount =  sqlSession.insert(NAMESPACE+"empUpdate",emp);
		LOG.info(resultCount+"건 수정");
		return resultCount;
	}

	// 8 직원 1건 삭제
	public int empDelete(int empid) {
		//인서트에 영향을 받은 건 수가 들어옴
		int resultCount =  sqlSession.insert(NAMESPACE+"empDelete",empid);
		LOG.info(resultCount+"건 삭제");
		return resultCount;
	
	}
}
