package com.paymentlist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class PML_JDBCDAO implements PMLDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA102_G4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO PAYMENT_FORM_LIST (DETAL_NUM,PF_NUM,PML_NUM) VALUES (DETAL_NUM_seq.NEXTVAL,?,?)";
	private static final String GET_ALL_STMT = "SELECT DETAL_NUM,PF_NUM,PML_NUM FROM PAYMENT_FORM_LIST order by DETAL_NUM";
	private static final String GET_ONE_STMT = "SELECT DETAL_NUM,PF_NUM,PML_NUM FROM PAYMENT_FORM_LIST where DETAL_NUM = ?";
	private static final String DELETE = "DELETE FROM PAYMENT_FORM_LIST where DETAL_NUM = ?";
	private static final String UPDATE = "UPDATE PAYMENT_FORM_LIST set PF_NUM=?,PML_NUM=? where DETAL_NUM = ?";
	@Override
	public void insert(PML_VO pmlVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);		
			pstmt.setInt(1, pmlVO.getPf_num());
			pstmt.setInt(2, pmlVO.getPml_num());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1,pmlVO.getPf_num());
			pstmt.setInt(2,pmlVO.getPml_num());
			pstmt.setInt(3,pmlVO.getDetal_num());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1,detal_num);

			pstmt.executeUpdate();

			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1,detal_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {			
				pmlVO = new PML_VO();
				pmlVO.setDetal_num(rs.getInt("DETAL_NUM"));
				pmlVO.setPf_num(rs.getInt("PF_NUM"));
				pmlVO.setPml_num(rs.getInt("PML_NUM"));				
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());			
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
		return list;
	}
	@Override
	public Set<PML_VO> getEmpsByDeptno(Integer pf_num) {
		// TODO Auto-generated method stub
		return null;
	}







	public static void main(String[] args) {

		PML_DAO dao = new PML_DAO();

	    
		PML_VO pmlVO1 = new PML_VO();	
		pmlVO1.setPf_num(1594);
		pmlVO1.setPml_num(1031);
		dao.insert(pmlVO1);

	
	
//		PML_VO pmlVO2 = new PML_VO();
//		pmlVO2.setDetal_num(1);
//		pmlVO2.setPf_num(1004);
//		pmlVO2.setPml_num(1003);
//		dao.update(pmlVO2);

//  	dao.delete(1006);
/*有參照 無法刪除修改*/

//		PML_VO ascVO3 = dao.findByPrimaryKey(1);		
//		System.out.print(ascVO3.getDetal_num()+ ",");
//		System.out.print(ascVO3.getPf_num()+ ",");
//		System.out.print(ascVO3.getPml_num()+ ",");
//		System.out.println("---------------------");


//		List<PML_VO> list = dao.getAll();
//		for (PML_VO aEmp : list) {
//			System.out.print(aEmp.getDetal_num()+ ",");
//			System.out.print(aEmp.getPf_num() + ",");
//			System.out.print(aEmp.getPml_num() + ",");		
//		}
	}
	}
	

	

