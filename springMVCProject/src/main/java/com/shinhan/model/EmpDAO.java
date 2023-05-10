package com.shinhan.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.util.OracleUtil;

import com.shinhan.vo.EmpVO;

//DAO(Data Access Object): DB업무 ..CRUD ..insert,select,update,delete
@Repository //@Component +DAO
public class EmpDAO {
	@Autowired
	@Qualifier("dataSourceOriginal")//이름이 같으면 주입
	DataSource ds;
	
	Connection conn;//오라클과 연결
	Statement st;//통로
	PreparedStatement pst;//?지원
	ResultSet rs;
	int resultCount;//insert,update,delete건수
	CallableStatement cst;
	
	//SP호출
	public EmpVO getSalary(int empid) {
		System.out.println("[DAO] salary");
		String sql = "{call sp_salary(?,?,?)}";
		
		EmpVO emp = new EmpVO();
		
		try {
			conn=ds.getConnection();
			cst = conn.prepareCall(sql);
			cst.setInt(1, empid);
			cst.registerOutParameter(2, Types.DOUBLE);
			cst.registerOutParameter(3, Types.VARCHAR);
			cst.execute();//resrulset있으면 true 없으면 펄스
			//executeQuery(), executeUpdate()
			
			emp.setSalary(cst.getDouble(2));
			emp.setFirst_name(cst.getString(3));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
		
	}
	
	public List<EmpVO> selectAll() {
		String sql = 
				"select EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,PHONE_NUMBER,"+
					 " HIRE_DATE,JOB_ID,SALARY,COMMISSION_PCT,MANAGER_ID,DEPARTMENT_ID"+
				" from employees order by 1"
				;
		List<EmpVO> emplist = new ArrayList<>();
		try {
			conn=ds.getConnection();
			st = conn.createStatement();
			rs=st.executeQuery(sql);
		
			if(st.execute(sql)) {
				rs=st.getResultSet();
			}
			
			while(rs.next()) {
				EmpVO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}
		
		
		return emplist;
	}

	//2특정 직원 조회
	public EmpVO selectById(int empid) {
		EmpVO emp = null;
		String sql = "select * from employees where employee_id="+empid;	
		try {
			conn=ds.getConnection();
			st = conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				 emp = makeEmp(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}
		
		
		
		return emp;
	}

	//3특정 부서의 직원을 조회
	public List<EmpVO> selectByDept(int deptid) {
		String sql = "select * from employees where department_id ="+deptid;
		List<EmpVO> emplist = new ArrayList<>();
		try {
			conn=ds.getConnection();
			st = conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				EmpVO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}
		
		return emplist;
	}
	
	//4특정 부서, jobid, salary이상의 직원 조회
	public List<EmpVO> selectByCondition(int deptid,String jobid, double salary) {
		String sql = "select * from "
				+ " employees "
				+ " where department_id = ? "
				+ " and job_id = ? "
				+ " and salary<=? ";
		List<EmpVO> emplist = new ArrayList<>();
		try {
			conn=ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptid);
			pst.setString(2, jobid);
			pst.setDouble(3, salary);
			rs=pst.executeQuery();
			while(rs.next()) {
				EmpVO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
		
		
		
		return emplist;
	}	
	//5자신의 속한 부서의 평균 급여보다 더 적은 급여를 받는 직원
	public List<EmpVO> selectLowSalary() {
		String sql = 
				"select employees.employee_id, employees.first_name, employees.salary, employees.department_id"+
				" from employees,(select department_id,avg(salary)avgsal"+
                				" from employees"+
                				" group by department_id) inemp"+
				" where employees.department_id = inemp.department_id"+
				" and employees.salary<inemp.avgsal";
		List<EmpVO> emplist = new ArrayList<>();
		try {
			conn=ds.getConnection();
			st = conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				EmpVO emp = makeEmp2(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}
		
		
		return emplist;
	}
	//6신규 직원 등록
		public int empInsert(EmpVO emp) {
			//시퀀스객체는 이미 DB에 올라가잇음
			String sql = 
					"insert into employees"+
					" values(seq_employee.nextval,?,?,?,?,?,?,?,?,?,?)";//모든 변수업데이트
		
			try {
				conn=ds.getConnection(); // 디비연결
				pst = conn.prepareStatement(sql);
				pst.setString(1, emp.getFirst_name());
				pst.setString(2, emp.getLast_name());
				pst.setString(3, emp.getEmail());
				pst.setString(4, emp.getPhone_number());
				pst.setDate(5, emp.getHire_date());
				pst.setString(6, emp.getJob_id());
				pst.setDouble(7, emp.getSalary());
				pst.setDouble(8, emp.getCommission_pct());
				pst.setInt(9, emp.getManager_id());
				pst.setInt(10, emp.getDepartment_id());
				resultCount = pst.executeUpdate();//DML 문장을 실행. 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				resultCount=-1;
				e.printStackTrace();
			}finally {
				
				OracleUtil.dbDisconnect(null, pst, conn);
			}
			return resultCount;
		}
		//7 직원 수정
				public int empUpdate(EmpVO emp) {
					//시퀀스객체는 이미 DB에 올라가잇음
					String sql = 
							 "update employees"+
							" set FIRST_NAME=?, LAST_NAME=?,EMAIL=?, DEPARTMENT_ID=?, JOB_ID=?,SALARY=?,"+
							" HIRE_DATE=?, MANAGER_ID=? "+
							" where EMPLOYEE_ID =?";//모든 변수업데이트
					try {
						conn=ds.getConnection();
						pst = conn.prepareStatement(sql);
					
						pst.setString(1, emp.getFirst_name());
						pst.setString(2, emp.getLast_name());
						pst.setString(3, emp.getEmail());
						pst.setInt(4, emp.getDepartment_id());
						pst.setString(5, emp.getJob_id());
						pst.setDouble(6, emp.getSalary());
						pst.setDate(7, emp.getHire_date());
						pst.setInt(8, emp.getManager_id());
						
						pst.setInt(9, emp.getEmployee_id());
						
						
						resultCount = pst.executeUpdate();//DML 문장을 실행. 영향을 받은 건수가 리턴
						//System.out.println("update결과");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						resultCount=-1;
						e.printStackTrace();
					}finally {
						
						OracleUtil.dbDisconnect(null, pst, conn);
					}
					System.out.println("update결과:"+resultCount);
					return resultCount;
				}
				//8 직원 1건 삭제
				public int empDelete(int empid) {
					//시퀀스객체는 이미 DB에 올라가잇음
					String sql = 
							 "delete from employees"+
							" where EMPLOYEE_ID =?"
							;//모든 변수업데이트
					try {
						conn=ds.getConnection();
						pst = conn.prepareStatement(sql);
						pst.setInt(1, empid);
						
						resultCount = pst.executeUpdate();//DML 문장을 실행. 영향을 받은 건수가 리턴
						//System.out.println("update결과");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						resultCount=-1;
						e.printStackTrace();
					}finally {
						OracleUtil.dbDisconnect(null, pst, conn);
					}
					System.out.println("delete결과:"+resultCount);
					return resultCount;
				}
	
	
	
	private EmpVO makeEmp(ResultSet rs) throws SQLException {
		EmpVO emp = new EmpVO();
		emp.setCommission_pct(rs.getDouble("Commission_pct"));
		emp.setDepartment_id(rs.getInt("Department_id"));
		emp.setEmail(rs.getString("Email"));
		emp.setEmployee_id(rs.getInt("Employee_id"));
		emp.setFirst_name(rs.getString("First_name"));
		emp.setHire_date(rs.getDate("Hire_date"));
		emp.setJob_id(rs.getString("Job_id"));
		emp.setLast_name(rs.getString("Last_name"));
		emp.setManager_id(rs.getInt("Manager_id"));
		emp.setPhone_number(rs.getString("Phone_number"));
		emp.setSalary(rs.getDouble("Salary"));
		
		return emp;
	}
	private EmpVO makeEmp2(ResultSet rs) throws SQLException {
		EmpVO emp = new EmpVO();
		emp.setDepartment_id(rs.getInt("Department_id"));
		emp.setEmployee_id(rs.getInt("Employee_id"));
		emp.setFirst_name(rs.getString("First_name"));
		emp.setSalary(rs.getDouble("Salary"));
		
		return emp;
	}
}
