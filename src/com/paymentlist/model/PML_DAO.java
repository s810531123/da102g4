package com.paymentlist.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class PML_DAO implements PMLDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA102_G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO PAYMENT_FORM_LIST (DETAL_NUM,PF_NUM,PML_NUM) VALUES (DETAL_NUM_seq.NEXTVAL,?,?)";
	private static final String GET_ALL_STMT = "SELECT DETAL_NUM,PF_NUM,PML_NUM FROM PAYMENT_FORM_LIST order by DETAL_NUM";
	private static final String GET_ONE_STMT = "SELECT DETAL_NUM,PF_NUM,PML_NUM FROM PAYMENT_FORM_LIST where empno = ?";
	private static final String DELETE = "DELETE FROM PAYMENT_FORM_LIST where DETAL_NUM = ?";
	private static final String UPDATE = "UPDATE PAYMENT_FORM_LIST set PF_NUM=?,PML_NUM=? where DETAL_NUM = ?";

	@Override
	public void insert(PML_VO pmlVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);			
			pstmt.setInt(1, pmlVO.getPf_num());
			pstmt.setInt(2, pmlVO.getPml_num());
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
	public void update(PML_VO pmlVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1,pmlVO.getPf_num());
			pstmt.setInt(2,pmlVO.getPml_num());
			pstmt.setInt(3,pmlVO.getDetal_num());
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
	public void delete(Integer detal_num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1,detal_num);

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
	public PML_VO findByPrimaryKey(Integer detal_num) {

		PML_VO pmlVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1,detal_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {			
				pmlVO = new PML_VO();
				pmlVO.setDetal_num(rs.getInt("DETAL_NUM"));
				pmlVO.setPf_num(rs.getInt("PF_NUM"));
				pmlVO.setPml_num(rs.getInt("PML_NUM"));				
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());			
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
		return pmlVO;
	}

	@Override
	public List<PML_VO> getAll() {

		List<PML_VO> list = new ArrayList<PML_VO>();
		PML_VO pmlVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				pmlVO = new PML_VO();
				pmlVO.setDetal_num(rs.getInt("DETAL_NUM"));
				pmlVO.setPf_num(rs.getInt("PF_NUM"));
				pmlVO.setPml_num(rs.getInt("PML_NUM"));	
				list.add(pmlVO); // Store the row in the list
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
	public Set<PML_VO> getEmpsByDeptno(Integer pf_num) {
		// TODO Auto-generated method stub
		return null;
	}

}
