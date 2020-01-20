package com.guardian.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.student.model.StudentJDBCDAO;
import com.student.model.StudentVO;

public class GuardianJDBCDAO implements GuardianDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA102_G4";
	String passwd = "123456";
	
	private static final String INSERT = "INSERT INTO guardian VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE guardian set gd_name = ?, gd_gender = ?, gd_email = ?, gd_address = ?,"
			+ " gd_rel = ?, gd_phone = ?, gd_birthday = ? where gd_id = ?";

	private static final String DELETE = "DELETE FROM guardian WHERE gd_id = ?";

	private static final String GET_ALL = "SELECT * FROM guardian";

	private static final String GET_ByGd_Id = "SELECT * FROM guardian WHERE gd_id = ?";

	@Override
	public void insert(GuardianVO guardianVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, guardianVO.getGd_id());
			pstmt.setString(2, guardianVO.getGd_name());
			pstmt.setString(3, guardianVO.getGd_gender());
			pstmt.setString(4, guardianVO.getGd_email());
			pstmt.setString(5, guardianVO.getGd_address());
			pstmt.setString(6, guardianVO.getGd_rel());
			pstmt.setString(7, guardianVO.getGd_phone());
			pstmt.setDate(8, guardianVO.getGd_birthday());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void update(GuardianVO guardianVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, guardianVO.getGd_name());
			pstmt.setString(2, guardianVO.getGd_gender());
			pstmt.setString(3, guardianVO.getGd_email());
			pstmt.setString(4, guardianVO.getGd_address());
			pstmt.setString(5, guardianVO.getGd_rel());
			pstmt.setString(6, guardianVO.getGd_phone());
			pstmt.setDate(7, guardianVO.getGd_birthday());
			pstmt.setString(8, guardianVO.getGd_id());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String gd_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, gd_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public List<GuardianVO> getAll() {
		List<GuardianVO> list = new ArrayList<GuardianVO>();
		GuardianVO guardianVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				guardianVO = new GuardianVO();
				guardianVO.setGd_id(rs.getString("gd_id"));
				guardianVO.setGd_name(rs.getString("gd_name"));
				guardianVO.setGd_gender(rs.getString("gd_gender"));
				guardianVO.setGd_email(rs.getString("gd_email"));
				guardianVO.setGd_address(rs.getString("gd_address"));
				guardianVO.setGd_rel(rs.getString("gd_rel"));
				guardianVO.setGd_phone(rs.getString("gd_phone"));
				guardianVO.setGd_birthday(rs.getDate("gd_birthday"));
				list.add(guardianVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public GuardianVO getGuardianByGd_Id(String gd_id) {
		Set<GuardianVO> set = new LinkedHashSet<GuardianVO>();
		GuardianVO guardianVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ByGd_Id);
			pstmt.setString(1, gd_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				guardianVO = new GuardianVO();
				guardianVO.setGd_id(rs.getString("gd_id"));
				guardianVO.setGd_name(rs.getString("gd_name"));
				guardianVO.setGd_gender(rs.getString("gd_gender"));
				guardianVO.setGd_email(rs.getString("gd_email"));
				guardianVO.setGd_address(rs.getString("gd_address"));
				guardianVO.setGd_rel(rs.getString("gd_rel"));
				guardianVO.setGd_phone(rs.getString("gd_phone"));
				guardianVO.setGd_birthday(rs.getDate("gd_birthday"));
				set.add(guardianVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return guardianVO;
	}
	
	public static void main(String[] args) {
		
		GuardianJDBCDAO dao = new GuardianJDBCDAO();

//		// 新增
//		GuardianVO vo1 = new GuardianVO();
//		vo1.setGd_id("Z123456784");
//		vo1.setGd_name("美心的媽媽");
//		vo1.setGd_gender("女");
//		vo1.setGd_email("kkk@gmail.com");
//		vo1.setGd_address("Kouhshoung");
//		vo1.setGd_rel("母女");
//		vo1.setGd_phone("912345680");
//		vo1.setGd_birthday(Date.valueOf("1993-01-01"));
//		dao.insert(vo1);
//		System.out.println("新增成功");

		// 修改
		GuardianVO vo2 = new GuardianVO();
		vo2.setGd_name("美心的媽媽");
		vo2.setGd_gender("女");
		vo2.setGd_email("lll@yahoo.com.tw");
		vo2.setGd_address("Kouhshoung");
		vo2.setGd_rel("母女");
		vo2.setGd_phone("912345680");
		vo2.setGd_birthday(Date.valueOf("1993-01-01"));
		vo2.setGd_id("Z123456784");
		dao.update(vo2);
		System.out.println("修改成功");

		// 查詢全部
		List<GuardianVO> list = dao.getAll();
		for (GuardianVO vo3 : list) {
			System.out.println("監護人身份證字號 : " + vo3.getGd_id());
			System.out.println("監護人姓名 : " + vo3.getGd_name());
			System.out.println("監護人性別 : " + vo3.getGd_gender());
			System.out.println("監護人E-mail : " + vo3.getGd_email());
			System.out.println("監護人地址 : " + vo3.getGd_address());
			System.out.println("監護人關係 : " + vo3.getGd_rel());
			System.out.println("監護人電話 : " + vo3.getGd_phone());
			System.out.println("監護人生日 : " + vo3.getGd_birthday());
			System.out.println("======================");
		}

		// 以姓名查詢
		GuardianVO vo4 = dao.getGuardianByGd_Id("S123456785");
		try {
			System.out.println("學號 : " + vo4.getGd_id());
			System.out.println("班級代號 : " + vo4.getGd_name());
			System.out.println("監護人身分證字號 : " + vo4.getGd_gender());
			System.out.println("學生姓名 : " + vo4.getGd_email());
			System.out.println("學生性別 : " + vo4.getGd_address());
			System.out.println("學生身分證字號 : " + vo4.getGd_rel());
			System.out.println("戶籍地址 : " + vo4.getGd_phone());
			System.out.println("通訊地址 : " + vo4.getGd_birthday());
			System.out.println("======================");
		} catch(NullPointerException ne) {
			System.out.println("查無此資料");
		}
		
//		// 刪除
//		dao.delete("Z123456784");
//		System.out.println("刪除成功");
	}
}
