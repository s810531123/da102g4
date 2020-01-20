package com.paymentproject.model;

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


public class PMP_DAO implements PMPDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA102_G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO PAYMENT_PROJECT (PML_NUM,PML_ITEM,PML_MONEY,PF_STATUS) VALUES (PML_NUM_seq.NEXTVAL,?,?,?)";
	private static final String GET_ALL_STMT2 = "SELECT PML_NUM,PML_ITEM,PML_MONEY,PF_STATUS FROM PAYMENT_PROJECT where PF_STATUS=1 order by PML_NUM";
	private static final String GET_ALL_STMT = "SELECT PML_NUM,PML_ITEM,PML_MONEY,PF_STATUS FROM PAYMENT_PROJECT order by PML_NUM";
	private static final String GET_ONE_STMT = "SELECT PML_NUM,PML_ITEM,PML_MONEY,PF_STATUS FROM PAYMENT_PROJECT where PML_NUM = ?";
	private static final String DELETE = "DELETE FROM PAYMENT_PROJECT where PML_NUM = ?";
	private static final String DELETE2 = "UPDATE PAYMENT_PROJECT set PF_STATUS=0 where PML_NUM= ?";
	private static final String UPDATE = "UPDATE PAYMENT_PROJECT set PML_ITEM=?,PML_MONEY=?,PF_STATUS=? where PML_NUM= ?";
	
	@Override
	public void insert(PMP_VO pmpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);			
			pstmt.setString(1, pmpVO.getPml_item());		
			pstmt.setInt(2, pmpVO.getPml_money());	
			pstmt.setInt(3, pmpVO.getPf_status());		
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
	public void update(PMP_VO pmpVO) {
	
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(4, pmpVO.getPml_num());
			pstmt.setString(1, pmpVO.getPml_item());
			pstmt.setInt(2, pmpVO.getPml_money());
			pstmt.setInt(3, pmpVO.getPf_status());
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
	public void delete(Integer pml_num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE2);

			pstmt.setInt(1,pml_num);

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
	public PMP_VO findByPrimaryKey(Integer pml_num) {
		PMP_VO pmpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1,pml_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {			
				pmpVO = new PMP_VO();				
				pmpVO.setPml_item(rs.getString("PML_ITEM"));
				pmpVO.setPml_money(rs.getInt("PML_MONEY"));
				pmpVO.setPf_status(rs.getInt("PF_STATUS"));		
				
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
		return pmpVO;
	}

	@Override
	public List<PMP_VO> getAll() {

		List<PMP_VO> list = new ArrayList<PMP_VO>();
		PMP_VO pmpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				pmpVO = new PMP_VO();
				pmpVO.setPml_num(rs.getInt("PML_NUM"));
				pmpVO.setPml_item(rs.getString("PML_ITEM"));
				pmpVO.setPml_money(rs.getInt("PML_MONEY"));
				pmpVO.setPf_status(rs.getInt("PF_STATUS"));		
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
	public Set<PMP_VO> getEmpsByDeptno(Integer pf_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllpmpnum() {
		List<String> list = new ArrayList<String>();		

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
							
				list.add(rs.getString("pml_item"));				
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

}
