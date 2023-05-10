package com.shinhan.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.vo.DeptVO;

@Repository
public class DeptDAOMybatis {
	
	Logger logger = LoggerFactory.getLogger(DeptDAOMybatis.class);
	
	@Autowired
	SqlSession sqlSession;
	String namespace = "com.shinhan.dept.";
	
	//모두조회
	public List<DeptVO> DeptSelectAll() {
		List<DeptVO> deptlist = sqlSession.selectList(namespace+"selectAll");
		logger.info("mybatis---deptSelectAll:"+deptlist.size());
		return deptlist;
	}
	
	//부서 추가
	public int deptInsert(DeptVO dept) {
		logger.info("mybatis---deptInsert:"+dept);
		return sqlSession.insert(namespace+"insert",dept);
	}
	
	//상세보기
	public DeptVO deptById(int deptid) {
		logger.info("mybatis---deptselectbyid:"+deptid);
		return sqlSession.selectOne(namespace+"selectById",deptid);
	}
	
	//수정
	public int deptUpdate(DeptVO dept) {
		logger.info("mybatis---deptupdate:"+dept);
		return sqlSession.update(namespace+"update",dept);
	}
	
	//삭제
	//8 직원 1건 삭제
	public int deptDelete(int deptid) {
		logger.info("mybatis---deptdelete:"+deptid);
		return sqlSession.delete(namespace+"delete",deptid);
	}
	
}
