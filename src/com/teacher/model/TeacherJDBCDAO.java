package com.teacher.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Part;

import com.student.model.StudentJDBCDAO;
import com.student.model.StudentVO;

public class TeacherJDBCDAO implements TeacherDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA102_G4";
	String passwd = "123456";

	public static final String INSERT = "INSERT INTO teacher (t_num, t_name, t_gender, t_id, t_email,"
			+ "t_r_address, t_address, t_job, t_password, t_birthday, t_status, t_photo)"
			+ "VALUES ('T'||LPAD(to_char(SEQ_TEACHER.NEXTVAL), 3, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String UPDATE = "UPDATE teacher SET t_name = ?, t_gender = ?, t_id = ?,"
			+ "t_email = ?, t_r_address = ?, t_address = ?, t_job = ?, t_password = ?, t_birthday = ?,"
			+ "t_status = ?, t_photo = ? WHERE t_num = ?";

	public static final String DELETE = "DELETE FROM teacher WHERE t_num = ?";

	public static final String GET_ALL = "SELECT * FROM teacher order by t_num";

	public static final String GET_BY_T_NUM = "SELECT * FROM teacher WHERE t_num = ?";
	
	public static final String GET_BY_T_ID = "SELECT * FROM teacher WHERE t_id = ?";

	@Override
	public void insert(TeacherVO teacherVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, teacherVO.getT_name());
			pstmt.setString(2, teacherVO.getT_gender());
			pstmt.setString(3, teacherVO.getT_id());
			pstmt.setString(4, teacherVO.getT_email());
			pstmt.setString(5, teacherVO.getT_r_address());
			pstmt.setString(6, teacherVO.getT_address());
			pstmt.setString(7, teacherVO.getT_job());
			pstmt.setString(8, teacherVO.getT_password());
			pstmt.setDate(9, teacherVO.getT_birthday());
			pstmt.setInt(10, teacherVO.getT_status());
			pstmt.setBytes(11, teacherVO.getT_photo());

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
	public void update(TeacherVO teacherVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, teacherVO.getT_name());
			pstmt.setString(2, teacherVO.getT_gender());
			pstmt.setString(3, teacherVO.getT_id());
			pstmt.setString(4, teacherVO.getT_email());
			pstmt.setString(5, teacherVO.getT_r_address());
			pstmt.setString(6, teacherVO.getT_address());
			pstmt.setString(7, teacherVO.getT_job());
			pstmt.setString(8, teacherVO.getT_password());
			pstmt.setDate(9, teacherVO.getT_birthday());
			pstmt.setInt(10, teacherVO.getT_status());
			pstmt.setBytes(11, teacherVO.getT_photo());
			pstmt.setString(12, teacherVO.getT_num());

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
	public void delete(String t_num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, t_num);

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
	public List<TeacherVO> getAll() {
		List<TeacherVO> list = new ArrayList<TeacherVO>();
		TeacherVO teacherVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				teacherVO = new TeacherVO();
				teacherVO.setT_num(rs.getString("t_num"));
				teacherVO.setT_name(rs.getString("t_name"));
				teacherVO.setT_gender(rs.getString("t_gender"));
				teacherVO.setT_id(rs.getString("t_id"));
				teacherVO.setT_email(rs.getString("t_email"));
				teacherVO.setT_r_address(rs.getString("t_r_address"));
				teacherVO.setT_address(rs.getString("t_address"));
				teacherVO.setT_job(rs.getString("t_job"));
				teacherVO.setT_password(rs.getString("t_password"));
				teacherVO.setT_birthday(rs.getDate("t_birthday"));
				teacherVO.setT_status(rs.getInt("t_status"));
				list.add(teacherVO);
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
	public TeacherVO findByT_Num(String t_num) {
		Set<TeacherVO> set = new LinkedHashSet<TeacherVO>();
		TeacherVO teacherVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_T_NUM);
			pstmt.setString(1, t_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				teacherVO = new TeacherVO();
				teacherVO.setT_num(rs.getString("t_num"));
				teacherVO.setT_name(rs.getString("t_name"));
				teacherVO.setT_gender(rs.getString("t_gender"));
				teacherVO.setT_id(rs.getString("t_id"));
				teacherVO.setT_email(rs.getString("t_email"));
				teacherVO.setT_r_address(rs.getString("t_r_address"));
				teacherVO.setT_address(rs.getString("t_address"));
				teacherVO.setT_job(rs.getString("t_job"));
				teacherVO.setT_password(rs.getString("t_password"));
				teacherVO.setT_birthday(rs.getDate("t_birthday"));
				teacherVO.setT_status(rs.getInt("t_status"));
				teacherVO.setT_photo(rs.getBytes("t_photo"));
				
				set.add(teacherVO);
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
		return teacherVO;
	}
	
	@Override
	public TeacherVO findByT_Id(String t_id) {
		Set<TeacherVO> set = new LinkedHashSet<TeacherVO>();
		TeacherVO teacherVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_T_ID);
			pstmt.setString(1, t_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				teacherVO = new TeacherVO();
				teacherVO.setT_num(rs.getString("t_num"));
				teacherVO.setT_name(rs.getString("t_name"));
				teacherVO.setT_gender(rs.getString("t_gender"));
				teacherVO.setT_id(rs.getString("t_id"));
				teacherVO.setT_email(rs.getString("t_email"));
				teacherVO.setT_r_address(rs.getString("t_r_address"));
				teacherVO.setT_address(rs.getString("t_address"));
				teacherVO.setT_job(rs.getString("t_job"));
				teacherVO.setT_password(rs.getString("t_password"));
				teacherVO.setT_birthday(rs.getDate("t_birthday"));
				teacherVO.setT_status(rs.getInt("t_status"));
				teacherVO.setT_photo(rs.getBytes("t_photo"));
				
				set.add(teacherVO);
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
		return teacherVO;
	}
	
	



	public static void main(String[] args) {

		TeacherJDBCDAO dao = new TeacherJDBCDAO();

		// 新增
		TeacherVO vo1 = new TeacherVO();
		vo1.setT_name("吳神");
		vo1.setT_gender("男性");
		vo1.setT_id("S123456789");
		vo1.setT_email("10@gmail.com");
		vo1.setT_r_address("桃園市中壢區中大路300號");
		vo1.setT_address("桃園市中壢區中大路300號");
		vo1.setT_job("老師");
		vo1.setT_password("teacher_10");
		vo1.setT_birthday(Date.valueOf("2001-01-01"));
		vo1.setT_status(1);
		dao.insert(vo1);
		System.out.println("新增成功");

		// 修改
		TeacherVO vo2 = new TeacherVO();
		vo2.setT_num("T011");
		vo2.setT_name("吳神");
		vo2.setT_gender("男性");
		vo2.setT_id("S123456789");
		vo2.setT_email("teacher_10@gmail.com");
		vo2.setT_r_address("桃園市中壢區中大路300號");
		vo2.setT_address("桃園市中壢區中大路300號");
		vo2.setT_job("老師");
		vo2.setT_password("teacher_10");
		vo2.setT_birthday(Date.valueOf("2001-01-01"));
		vo2.setT_status(1);
		dao.update(vo2);
		System.out.println("修改成功");

		// 查詢全部
		List<TeacherVO> list1 = dao.getAll();
		for (TeacherVO vo3 : list1) {
			System.out.println("教師編號 : " + vo3.getT_num());
			System.out.println("教師名字 : " + vo3.getT_name());
			System.out.println("性別 : " + vo3.getT_gender());
			System.out.println("身分證字號 : " + vo3.getT_id());
			System.out.println("E-mail : " + vo3.getT_email());
			System.out.println("戶籍地址 : " + vo3.getT_r_address());
			System.out.println("通訊地址 : " + vo3.getT_address());
			System.out.println("職稱 : " + vo3.getT_job());
			System.out.println("密碼 : " + vo3.getT_password());
			System.out.println("生日 : " + vo3.getT_birthday());
			System.out.println("教師狀態 : " + vo3.getT_status());
			System.out.println("======================");
		}

		// 以教師編號查詢
		TeacherVO vo4 = dao.findByT_Num("T011");
		try {
			System.out.println("教師編號 : " + vo4.getT_num());
			System.out.println("教師名字 : " + vo4.getT_name());
			System.out.println("性別 : " + vo4.getT_gender());
			System.out.println("身分證字號 : " + vo4.getT_id());
			System.out.println("E-mail : " + vo4.getT_email());
			System.out.println("戶籍地址 : " + vo4.getT_r_address());
			System.out.println("通訊地址 : " + vo4.getT_address());
			System.out.println("職稱 : " + vo4.getT_job());
			System.out.println("密碼 : " + vo4.getT_password());
			System.out.println("生日 : " + vo4.getT_birthday());
			System.out.println("教師狀態 : " + vo4.getT_status());
			System.out.println("======================");
		} catch (NullPointerException ne) {
			System.out.println("查無此資料");
		}
		
//		// 刪除
//		dao.delete("T011");
//		System.out.println("刪除成功");

	}

}