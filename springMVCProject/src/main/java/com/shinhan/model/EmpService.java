package com.shinhan.model;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.vo.EmpVO;

//service되는 업무로직 담당
@Service
public class EmpService {
	@Autowired //Spring이 타입이 같으면 자동주입
	EmpDAOMybatis empDao;
	
	public List<EmpVO> selectAll() {
		//
		return empDao.selectAll();
	}
	//특정 직원 조회
	public EmpVO selectById(int empid) {
		return empDao.selectById(empid);
	}
	//특정 부서의 직원을 조회
	public List<EmpVO> selectByDept(int deptid) {
			return empDao.selectByDept(deptid);
		}
	//특정 부서, jobid, salary이상의 직원 조회
	public List<EmpVO> selectByCondition(Integer[] deptid,String jobid, Double salary, Date hiredate) {
		return empDao.selectByCondition(deptid, jobid, salary,hiredate);
	}
//	//자신의 속한 부서의 평균 급여보다 더 적은 급여를 받는 직원
//	public List<EmpVO> selectLowSalary() {
//		List<EmpVO> emplist = empDao.selectLowSalary();
//		System.out.println("[EMPService] 실행 건수"+emplist.size());
//		
//		return emplist;
//	}
	//신규 직원 등록
	public String empInsert(EmpVO emp) {
			int result = empDao.empInsert(emp);
			return (result>0?"입력성공":"입력실패");
		}
	//7 직원 수정
	public String empUpdate(EmpVO emp) {
		int result = empDao.empUpdate(emp);
		return result>0?"update성공":"update실패";		
				}
	//8 직원 1건 삭제
	public String empDelete(int empid) {
		int result = empDao.empDelete(empid);
		return empid>0?"delete성공":"delete실패";
	}
//	//SP호출
//	public EmpVO getSalary(int empid) {
//		return empDao.getSalary(empid);
//	}
}

