package com.course.model;

import java.util.*;
import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Course;
import java.io.*;
import java.io.IOException;
import java.sql.*;

public class CourseDAO implements CourseDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA102_G4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO COURSE VALUES (SEQ_COURSE.NEXTVAL, ?, ?,?,?,?,?,0,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM COURSE ORDER BY COU_NUM DESC";
	private static final String GET_ONE_STMT = "SELECT * FROM COURSE WHERE COU_NUM = ?";

	private static final String DELETE_DEPT = "DELETE FROM COURSE WHERE COU_NUM = ?";

	private static final String UPDATE_STATUS = "UPDATE COURSE SET COU_STATUS = ? WHERE COU_NUM = ?";
	private static final String UPDATE = "UPDATE COURSE SET T_NUM=?, COU_NAME=? ,COU_INTROD=?,COU_COST=?,COU_MIN=?,COU_MAX=?,COU_SDATE=?,COU_EDATE=?,COU_PIC=? WHERE COU_NUM = ?";
	private static final String UPDATE_WITHOUT_PIC = "UPDATE COURSE SET T_NUM=?, COU_NAME=? ,COU_INTROD=?,COU_COST=?,COU_MIN=?,COU_MAX=?,COU_SDATE=?,COU_EDATE=? WHERE COU_NUM = ?";

	@Override
	public void insert(CourseVO courseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, courseVO.getT_num());
			pstmt.setString(2, courseVO.getCou_name());
			pstmt.setString(3, courseVO.getCou_introd());
			pstmt.setInt(4, courseVO.getCou_cost());
			pstmt.setInt(5, courseVO.getCou_min());
			pstmt.setInt(6, courseVO.getCou_max());
			pstmt.setDate(7, courseVO.getCou_sdate());
			pstmt.setDate(8, courseVO.getCou_edate());
			pstmt.setBytes(9, courseVO.getCou_pic());
			
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
	public void updateStatus(CourseVO courseVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STATUS);
			
			pstmt.setInt(1, courseVO.getCou_status());
			pstmt.setInt(2, courseVO.getCou_num());

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
	public void update(CourseVO courseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(10, courseVO.getCou_num());
			pstmt.setString(1, courseVO.getT_num());
			pstmt.setString(2, courseVO.getCou_name());
			pstmt.setString(3, courseVO.getCou_introd());
			pstmt.setInt(4, courseVO.getCou_cost());
			pstmt.setInt(5, courseVO.getCou_min());
			pstmt.setInt(6, courseVO.getCou_max());
			pstmt.setDate(7, courseVO.getCou_sdate());
			pstmt.setDate(8, courseVO.getCou_edate());
			pstmt.setBytes(9, courseVO.getCou_pic());

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
	public void updateWithOutPic(CourseVO courseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_WITHOUT_PIC);
			
			pstmt.setInt(9, courseVO.getCou_num());
			pstmt.setString(1, courseVO.getT_num());
			pstmt.setString(2, courseVO.getCou_name());
			pstmt.setString(3, courseVO.getCou_introd());
			pstmt.setInt(4, courseVO.getCou_cost());
			pstmt.setInt(5, courseVO.getCou_min());
			pstmt.setInt(6, courseVO.getCou_max());
			pstmt.setDate(7, courseVO.getCou_sdate());
			pstmt.setDate(8, courseVO.getCou_edate());
			

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
	public void delete(Integer cou_num) {


		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1嚙踝蕭�頨急謍梱�� pstm.executeUpdate()���蕭嚙�
			con.setAutoCommit(false);


			pstmt = con.prepareStatement(DELETE_DEPT);
			pstmt.setInt(1, cou_num);
			pstmt.executeUpdate();

			// 2嚙踝蕭�頨急謍梱�� pstm.executeUpdate()���蕭嚙�
			con.commit();
			con.setAutoCommit(true);
				

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
	public CourseVO findByPrimaryKey(Integer cou_num) {

		CourseVO courseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, cou_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
		
				courseVO = new CourseVO();
				courseVO.setCou_num(rs.getInt("cou_num"));
				courseVO.setCou_name(rs.getString("cou_name"));
				courseVO.setCou_introd(rs.getString("cou_introd"));
				courseVO.setT_num(rs.getString("t_num"));
				courseVO.setCou_min(rs.getInt("cou_min"));
				courseVO.setCou_max(rs.getInt("cou_max"));
				courseVO.setCou_cost(rs.getInt("cou_cost"));
				courseVO.setCou_status(rs.getInt("cou_status"));
				courseVO.setCou_sdate(rs.getDate("cou_sdate"));
				courseVO.setCou_edate(rs.getDate("cou_edate"));
				courseVO.setCou_pic(rs.getBytes("cou_pic"));

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
		return courseVO;
	}

	@Override
	public List<CourseVO> getAll() {
		List<CourseVO> list = new ArrayList<CourseVO>();
		CourseVO courseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				courseVO = new CourseVO();
				courseVO.setCou_num(rs.getInt("cou_num"));
				courseVO.setCou_name(rs.getString("cou_name"));
				courseVO.setCou_introd(rs.getString("cou_introd"));
				courseVO.setT_num(rs.getString("t_num"));
				courseVO.setCou_min(rs.getInt("cou_min"));
				courseVO.setCou_max(rs.getInt("cou_max"));
				courseVO.setCou_cost(rs.getInt("cou_cost"));
				courseVO.setCou_status(rs.getInt("cou_status"));
				courseVO.setCou_sdate(rs.getDate("cou_sdate"));
				courseVO.setCou_edate(rs.getDate("cou_edate"));
				courseVO.setCou_pic(rs.getBytes("cou_pic"));

				list.add(courseVO); // Store the row in the list
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
		return list;
	}
	
	
	@Override
	public List<CourseVO> getAll(Map<String, String[]> map) {
		List<CourseVO> list = new ArrayList<CourseVO>();
		CourseVO courseVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			String finalSQL = "select * from course "
		          + jdbcUtil_CompositeQuery_Course.get_WhereCondition(map)
		          + "order by cou_num desc";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				courseVO = new CourseVO();
				courseVO.setCou_num(rs.getInt("cou_num"));
				courseVO.setCou_name(rs.getString("cou_name"));
				courseVO.setT_num(rs.getString("t_num"));
				courseVO.setCou_introd(rs.getString("cou_introd"));
				courseVO.setCou_pic(rs.getBytes("cou_pic"));
				courseVO.setCou_min(rs.getInt("cou_min"));
				courseVO.setCou_max(rs.getInt("cou_max"));
				courseVO.setCou_status(rs.getInt("cou_status"));
				courseVO.setCou_cost(rs.getInt("cou_cost"));
				courseVO.setCou_sdate(rs.getDate("cou_sdate"));
				courseVO.setCou_edate(rs.getDate("cou_edate"));
				list.add(courseVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return list;
	}
	
	

	public static void main(String[] args) {
		
		
//		Integer in = null;
//		System.out.println(in);
//
		CourseDAO dao = new CourseDAO();
		CourseService couSvc = new CourseService();
		
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("cou_name", new String[] { "J" });
		map.put("cou_status", new String[] { "0" });
		map.put("t_num", new String[] { "T" });
		
		couSvc.getAll(map);
		
////
////			// 嚙踐��蕭
//		CourseVO courseVO = new CourseVO();
//		courseVO.setCou_name("大大吳");
//		courseVO.setCou_introd("很棒棒");
//		courseVO.setCou_cost(3000);
//		courseVO.setCou_min(5);
//		courseVO.setCou_max(10);
//		courseVO.setT_num("T002");
////		courseVO.setCou_status(0);
//		courseVO.setCou_sdate(java.sql.Date.valueOf("2019-08-14"));
//		courseVO.setCou_edate(java.sql.Date.valueOf("2019-08-20"));
//		try {
//			courseVO.setCou_pic(getPictureByteArray("D:/PIC/images.jpg"));
//		} catch (IOException io) {
//			io.printStackTrace();
//		}
////
//		dao.insert(courseVO);
//
//			// �����
//			CourseVO courseVO = new CourseVO();
//			courseVO.setCou_num(1010);
//			courseVO.setCou_name("大吳");
//			courseVO.setCou_introd("棒");
//			courseVO.setCou_cost(30000);
//			courseVO.setCou_min(2);
//			courseVO.setCou_max(10);
//			courseVO.setT_num("T002");
//			courseVO.setCou_status(0);
//			courseVO.setCou_sdate(java.sql.Date.valueOf("2019-08-14"));
//			courseVO.setCou_edate(java.sql.Date.valueOf("2019-08-15"));
//			try {
//				courseVO.setCou_pic(getPictureByteArray("D:/PIC/images.jpg"));
//			} catch (IOException io) {
//				io.printStackTrace();
//			}
//			
//			dao.update(courseVO);
//
//			// 嚙踝�蕭謒�
//			dao.delete(1011);
//
		// 嚙踐�K
//			CourseVO courseVO = dao.findByPrimaryKey(1010);
//			System.out.print(courseVO.getCou_num() + ",");
//			System.out.print(courseVO.getCou_name() + ",");
//			System.out.print(courseVO.getCou_introd() + ",");
//			System.out.print(courseVO.getT_num() + ",");
//			System.out.print(courseVO.getCou_cost() + ",");
//			System.out.print(courseVO.getCou_min() + ",");
//			System.out.print(courseVO.getCou_max() + ",");
//			System.out.print(courseVO.getCou_status() + ",");
//			System.out.print(courseVO.getCou_sdate() + ",");
//			System.out.print(courseVO.getCou_edate() + ",");
//			System.out.print(courseVO.getCou_pic() + ",");

//		System.out.println("---------------------");

		// 嚙踐�蕭�嚙踝��
//			List<CourseVO> list = dao.getAll();
//			for (CourseVO aCourse : list) {
//				System.out.print(aCourse.getCou_num() + ",");
//				System.out.print(aCourse.getCou_name() + ",");
//				System.out.print(aCourse.getCou_introd() + ",");
//				System.out.print(aCourse.getT_num() + ",");
//				System.out.print(aCourse.getCou_cost() + ",");
//				System.out.print(aCourse.getCou_min() + ",");
//				System.out.print(aCourse.getCou_max() + ",");
//				System.out.print(aCourse.getCou_status() + ",");
//				System.out.print(aCourse.getCou_sdate() + ",");
//				System.out.print(aCourse.getCou_edate() + ",");
//				System.out.print(aCourse.getCou_pic() + ",");
//				System.out.println();
//			}

	}

	// 嚙踝蕭謘橘蕭嚙�
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

	// Handle with byte array data
	public static void readPicture(byte[] bytes) throws IOException {
		FileOutputStream fos = new FileOutputStream("Output/2.png");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}



	

}
