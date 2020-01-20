package com.pmf_schedule.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.paymentproject.model.PMP_VO;


public class ScheduleDAO implements ScheduleDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA102_G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO SCHEDULED (SDU_NUM,PML_NUM,PML_ITEM,PML_TIME) VALUES (SDU_NUM_seq.NEXTVAL,?,?,?)";
	private static final String GET_ALL_STMT2 = "SELECT SDU_NUM,PML_NUM,PML_ITEM,PML_TIME FROM SCHEDULED order by SDU_NUM";
	private static final String GET_ALL_STMT = "SELECT PML_NUM,PML_ITEM,PML_MONEY,PF_STATUS FROM PAYMENT_PROJECT order by PML_NUM";
	private static final String GET_ONE_STMT = "SELECT PML_NUM,PML_ITEM,PML_MONEY,PF_STATUS FROM PAYMENT_PROJECT where PML_NUM = ?";
	private static final String DELETE = "DELETE FROM SCHEDULED where PML_TIME = ? and PML_NUM=? and PML_ITEM=?";
	private static final String DELETE2 = "UPDATE PAYMENT_PROJECT set PF_STATUS=0 where PML_NUM= ?";
	private static final String UPDATE = "UPDATE PAYMENT_PROJECT set PML_ITEM=?,PML_MONEY=?,PF_STATUS=? where PML_NUM= ?";
	@Override
	public void insert(ScheduleVO pmpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);			
			pstmt.setString(1,pmpVO.getPml_num());		
			pstmt.setString(2,pmpVO.getPml_item());	
			pstmt.setString(3,pmpVO.getPml_time());		
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	
		
	}
	@Override
	public void update(ScheduleVO pmpVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(String time ,String pml_item,String pml_num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,time);
			
			pstmt.setString(2,pml_num);
			
			pstmt.setString(3,pml_item);

			pstmt.executeUpdate();

			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());			
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	
		
	}
	@Override
	public ScheduleVO findByPrimaryKey(Integer pml_num) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ScheduleVO> getAll() {
		List<ScheduleVO> list = new ArrayList<ScheduleVO>();
		ScheduleVO pmpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				pmpVO = new ScheduleVO();
				
				pmpVO.setSdu_num(rs.getString("SDU_NUM"));
				pmpVO.setPml_num(rs.getString("PML_NUM"));
				pmpVO.setPml_item(rs.getString("PML_ITEM"));
				pmpVO.setPml_time(rs.getString("PML_TIME"));
				
				list.add(pmpVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;	
	}
	@Override
	public Set<ScheduleVO> getEmpsByDeptno(Integer pf_num) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String> getAllpmpnum() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
