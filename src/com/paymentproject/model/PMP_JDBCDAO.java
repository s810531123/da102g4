package com.paymentproject.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PMP_JDBCDAO implements PMPDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA102_G4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO PAYMENT_PROJECT (PML_NUM,PML_ITEM,PML_MONEY,PF_STATUS) VALUES (PML_NUM_seq.NEXTVAL,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT PML_NUM,PML_ITEM,PML_MONEY,PF_STATUS FROM PAYMENT_PROJECT order by PML_NUM";
	private static final String GET_ONE_STMT = "SELECT PML_NUM,PML_ITEM,PML_MONEY,PF_STATUS FROM PAYMENT_PROJECT where PML_NUM = ?";
	private static final String DELETE = "DELETE FROM PAYMENT_PROJECT where PML_NUM = ?";
	private static final String UPDATE = "UPDATE PAYMENT_PROJECT set PML_ITEM=?,PML_MONEY=?,PF_STATUS=? where PML_NUM= ?";

	@Override
	public void insert(PMP_VO pmpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, pmpVO.getPml_item());
			pstmt.setInt(2, pmpVO.getPml_money());
			pstmt.setInt(3, pmpVO.getPf_status());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(PMP_VO pmpVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(4, pmpVO.getPml_num());
			pstmt.setString(1, pmpVO.getPml_item());
			pstmt.setInt(2, pmpVO.getPml_money());
			pstmt.setInt(3, pmpVO.getPf_status());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer pml_num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, pml_num);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public PMP_VO findByPrimaryKey(Integer pml_num) {
		PMP_VO pmpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, pml_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				pmpVO = new PMP_VO();
				pmpVO.setPml_item(rs.getString("PML_ITEM"));
				pmpVO.setPml_money(rs.getInt("PML_MONEY"));
				pmpVO.setPf_status(rs.getInt("PF_STATUS"));

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return list;
	}

	@Override
	public Set<PMP_VO> getEmpsByDeptno(Integer pf_num) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {

		PMP_JDBCDAO dao = new PMP_JDBCDAO();

		PMP_VO pmpVO1 = new PMP_VO();
		pmpVO1.setPml_item("清潔費1");
		pmpVO1.setPml_money(3433);
		pmpVO1.setPf_status(1);
		dao.insert(pmpVO1);

//		PMP_VO pmpVO2 = new PMP_VO();
//		pmpVO2.setPml_item("清潔費");
//		pmpVO2.setPml_money(444);
//		pmpVO2.setPf_status(1);
//		pmpVO2.setPml_num(1001);
//		dao.update(pmpVO2);

		// dao.delete(1001);

//		PMP_VO ascVO3 = dao.findByPrimaryKey(1002);		
//		System.out.print(ascVO3.getPml_item()+ ",");
//		System.out.print(ascVO3.getPf_status()+ ",");
//		System.out.print(ascVO3.getPml_money()+ ",");
//		System.out.print(ascVO3.getPml_num()+ ",");
//		System.out.println("---------------------");

//
//		List<PMP_VO> list = dao.getAll();
//		for (PMP_VO aEmp : list) {
//			System.out.print(aEmp.getPml_item()+ ",");
//			System.out.print(aEmp.getPf_status()+ ",");
//			System.out.print(aEmp.getPml_money() + ",");
//			System.out.print(aEmp.getPml_num() + ",");
//		}
	}

	@Override
	public List<String> getAllpmpnum() {
		// TODO Auto-generated method stub
		return null;
	}
}
