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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.vo.DeptVO;

@Repository
public class DeptDAO {
	@Autowired
	@Qualifier("dataSourceOriginal")//�̸��� ������ ����
	DataSource ds;
	
	Connection conn;//����Ŭ�� ����
	Statement st;//���
	PreparedStatement pst;//?����
	ResultSet rs;
	int resultCount;//insert,update,delete�Ǽ�
	CallableStatement cst;
	
	//�����ȸ
	public List<DeptVO> DeptSelectAll() {
		String sql="select * from departments";
		List<DeptVO> deptlist = new ArrayList<DeptVO>();
		try {
			conn=ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				DeptVO dept = makeDept(rs);
				deptlist.add(dept);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return deptlist;
	}

	private DeptVO makeDept(ResultSet rs2) throws SQLException {
		DeptVO dept = new DeptVO();
		dept.setDepartment_id(rs.getInt("Department_id"));
		dept.setDepartment_name(rs.getString("Department_name"));
		dept.setManager_id(rs.getInt("Manager_id"));
		dept.setLocation_id(rs.getInt("Location_id"));
		
		
		return dept;
	}
	
	//�μ� �߰�
	public int deptInsert(DeptVO dept) {
		String sql = "insert into departments"+
					" values(departments_seq.nextval,?,?,?)";
		
		try {
			conn = ds.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1, dept.getDepartment_name());
			pst.setInt(2, dept.getManager_id());
			pst.setInt(3, dept.getLocation_id());
			
			resultCount = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCount = -1;
		}
		
		return resultCount;
	}
	
	//�󼼺���
	public DeptVO deptById(int deptid) {
		String sql = "select * from departments where department_id =?";
		DeptVO dept = null;
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptid);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				dept = makeDept(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return dept;
	}
	
	//����
	public int deptUpdate(DeptVO dept) {
		String sql = "update departments"+
					" set department_name=?,manager_id=?,location_id=?"+
					" where department_id = ?";
		
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, dept.getDepartment_name());
			pst.setInt(2, dept.getManager_id());
			pst.setInt(3, dept.getLocation_id());
			pst.setInt(4, dept.getDepartment_id());
			
			resultCount = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultCount;
	}
	
	//����
	//8 ���� 1�� ����
	public int deptDelete(int deptid) {
		String sql = "delete from departments where department_id = ?";
		
		try {
			conn=ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptid);
			
			resultCount = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			resultCount = -1;
			e.printStackTrace();
		}
				
				
		return resultCount;
	}
	
}
