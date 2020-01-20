package com.student.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StudentJNDIDAO implements StudentDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA102_G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO student (st_num, cs_num, gd_id,"
			+ "st_name, st_gender, st_id, st_r_address, st_address, st_password, st_birthday,"
			+ "st_status, st_photo) VALUES ('S'||LPAD(to_char(SEQ_STUDENT.NEXTVAL), 3, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE student SET cs_num=?, gd_id=?,"
			+ "st_name=?, st_gender=?, st_id=?, st_r_address=?, st_address=?, st_password=?,"
			+ "st_birthday=?, st_status=?, st_photo = ? where st_num=?";

	private static final String DELETE = "DELETE FROM student WHERE st_num = ?";

	private static final String GET_ALL = "SELECT * FROM student";

	private static final String GET_BySt_Name = "SELECT * FROM student WHERE st_name = ?";

	private static final String GET_BySt_Num = "SELECT * FROM student WHERE st_num = ?";

	private static final String GET_ByCs_Num = "SELECT * FROM student WHERE cs_num = ?";
	
	private static final String GET_BySt_Id = "SELECT * FROM student WHERE st_id = ?";

	private static final String GET_BySt_Name_Num = "SELECT * FROM student " + "WHERE st_name = ? AND cs_num = ?";

	private static final String GET_BySt_ALLNum = "SELECT st_num FROM student ";
	
	@Override
	public void insert(StudentVO studentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, studentVO.getCs_num());
			pstmt.setString(2, studentVO.getGd_id());
			pstmt.setString(3, studentVO.getSt_name());
			pstmt.setString(4, studentVO.getSt_gender());
			pstmt.setString(5, studentVO.getSt_id());
			pstmt.setString(6, studentVO.getSt_r_address());
			pstmt.setString(7, studentVO.getSt_address());
			pstmt.setString(8, studentVO.getSt_password());
			pstmt.setDate(9, studentVO.getSt_birthday());
			pstmt.setInt(10, studentVO.getSt_status());
			pstmt.setBytes(11, studentVO.getSt_photo());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void update(StudentVO studentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, studentVO.getCs_num());
			pstmt.setString(2, studentVO.getGd_id());
			pstmt.setString(3, studentVO.getSt_name());
			pstmt.setString(4, studentVO.getSt_gender());
			pstmt.setString(5, studentVO.getSt_id());
			pstmt.setString(6, studentVO.getSt_r_address());
			pstmt.setString(7, studentVO.getSt_address());
			pstmt.setString(8, studentVO.getSt_password());
			pstmt.setDate(9, studentVO.getSt_birthday());
			pstmt.setInt(10, studentVO.getSt_status());
			pstmt.setBytes(11, studentVO.getSt_photo());
			pstmt.setString(12, studentVO.getSt_num());
			
			pstmt.executeUpdate();
	
			// Handle any driver errors
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
	public void delete(String st_num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, st_num);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public List<StudentVO> getAll() {
		List<StudentVO> list = new ArrayList<StudentVO>();
		StudentVO studentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				studentVO = new StudentVO();
				studentVO.setSt_num(rs.getString("st_num"));
				studentVO.setCs_num(rs.getString("cs_num"));
				studentVO.setGd_id(rs.getString("gd_id"));
				studentVO.setSt_name(rs.getString("st_name"));
				studentVO.setSt_gender(rs.getString("st_gender"));
				studentVO.setSt_id(rs.getString("st_id"));
				studentVO.setSt_r_address(rs.getString("st_r_address"));
				studentVO.setSt_address(rs.getString("st_address"));
				studentVO.setSt_password(rs.getString("st_password"));
				studentVO.setSt_birthday(rs.getDate("st_birthday"));
				studentVO.setSt_status(rs.getInt("st_status"));
				list.add(studentVO);
			}

			// Handle any driver errors
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
	public List<StudentVO> getStudentBySt_Name(String st_name) {

		List<StudentVO> list = new ArrayList<StudentVO>();
		StudentVO studentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BySt_Name);
			pstmt.setString(1, st_name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				studentVO = new StudentVO();
				studentVO.setSt_num(rs.getString("st_num"));
				studentVO.setCs_num(rs.getString("cs_num"));
				studentVO.setGd_id(rs.getString("gd_id"));
				studentVO.setSt_name(rs.getString("st_name"));
				studentVO.setSt_gender(rs.getString("st_gender"));
				studentVO.setSt_id(rs.getString("st_id"));
				studentVO.setSt_r_address(rs.getString("st_r_address"));
				studentVO.setSt_address(rs.getString("st_address"));
				studentVO.setSt_password(rs.getString("st_password"));
				studentVO.setSt_birthday(rs.getDate("st_birthday"));
				studentVO.setSt_status(rs.getInt("st_status"));
				list.add(studentVO);
			}

			// Handle any driver errors
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
	public StudentVO getStudentBySt_Num(String st_num) {
		Set<StudentVO> set = new LinkedHashSet<StudentVO>();
		StudentVO studentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BySt_Num);
			pstmt.setString(1, st_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				studentVO = new StudentVO();
				studentVO.setSt_num(rs.getString("st_num"));
				studentVO.setCs_num(rs.getString("cs_num"));
				studentVO.setGd_id(rs.getString("gd_id"));
				studentVO.setSt_name(rs.getString("st_name"));
				studentVO.setSt_gender(rs.getString("st_gender"));
				studentVO.setSt_id(rs.getString("st_id"));
				studentVO.setSt_r_address(rs.getString("st_r_address"));
				studentVO.setSt_address(rs.getString("st_address"));
				studentVO.setSt_password(rs.getString("st_password"));
				studentVO.setSt_birthday(rs.getDate("st_birthday"));
				studentVO.setSt_status(rs.getInt("st_status"));
				studentVO.setSt_photo(rs.getBytes("st_photo"));
				set.add(studentVO);
			}

			// Handle any driver errors
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
		return studentVO;
	}

	@Override
	public List<StudentVO> getStudentByCs_Num(String cs_num) {
		List<StudentVO> list = new ArrayList<StudentVO>();
		StudentVO studentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ByCs_Num);
			pstmt.setString(1, cs_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				studentVO = new StudentVO();
				studentVO.setSt_num(rs.getString("st_num"));
				studentVO.setCs_num(rs.getString("cs_num"));
				studentVO.setGd_id(rs.getString("gd_id"));
				studentVO.setSt_name(rs.getString("st_name"));
				studentVO.setSt_gender(rs.getString("st_gender"));
				studentVO.setSt_id(rs.getString("st_id"));
				studentVO.setSt_r_address(rs.getString("st_r_address"));
				studentVO.setSt_address(rs.getString("st_address"));
				studentVO.setSt_password(rs.getString("st_password"));
				studentVO.setSt_birthday(rs.getDate("st_birthday"));
				studentVO.setSt_status(rs.getInt("st_status"));
				list.add(studentVO);
			}

			// Handle any driver errors
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
	public List<String> get_AllNum() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BySt_ALLNum);			
			rs = pstmt.executeQuery();
			List<String> list = new ArrayList<String>();
			while (rs.next()) {				
			list.add(rs.getString("st_num"));		
							}
		return list;
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
	}
	

	@Override
	public StudentVO getStudentBySt_Name_and_Cs_Num(String st_name, String cs_num) {
		Set<StudentVO> set = new LinkedHashSet<StudentVO>();
		StudentVO studentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BySt_Name_Num);
			pstmt.setString(1, st_name);
			pstmt.setString(2, cs_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				studentVO = new StudentVO();
				studentVO.setSt_num(rs.getString("st_num"));
				studentVO.setCs_num(rs.getString("cs_num"));
				studentVO.setGd_id(rs.getString("gd_id"));
				studentVO.setSt_name(rs.getString("st_name"));
				studentVO.setSt_gender(rs.getString("st_gender"));
				studentVO.setSt_id(rs.getString("st_id"));
				studentVO.setSt_r_address(rs.getString("st_r_address"));
				studentVO.setSt_address(rs.getString("st_address"));
				studentVO.setSt_password(rs.getString("st_password"));
				studentVO.setSt_birthday(rs.getDate("st_birthday"));
				studentVO.setSt_status(rs.getInt("st_status"));
				set.add(studentVO);
			}

			// Handle any driver errors
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
		return studentVO;
	}
	
	
	
	@Override
	public StudentVO getStudentBySt_Id(String st_id) {
		Set<StudentVO> set = new LinkedHashSet<StudentVO>();
		StudentVO studentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BySt_Id);
			pstmt.setString(1, st_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				studentVO = new StudentVO();
				studentVO.setSt_num(rs.getString("st_num"));
				studentVO.setCs_num(rs.getString("cs_num"));
				studentVO.setGd_id(rs.getString("gd_id"));
				studentVO.setSt_name(rs.getString("st_name"));
				studentVO.setSt_gender(rs.getString("st_gender"));
				studentVO.setSt_id(rs.getString("st_id"));
				studentVO.setSt_r_address(rs.getString("st_r_address"));
				studentVO.setSt_address(rs.getString("st_address"));
				studentVO.setSt_password(rs.getString("st_password"));
				studentVO.setSt_birthday(rs.getDate("st_birthday"));
				studentVO.setSt_status(rs.getInt("st_status"));
				studentVO.setSt_photo(rs.getBytes("st_photo"));
				set.add(studentVO);
			}

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
		return studentVO;
	}
	
	
	

	public static void main(String[] args) {

		StudentJDBCDAO dao = new StudentJDBCDAO();

		try {
			// 新增
			StudentVO vo1 = new StudentVO();
			vo1.setCs_num("C001");
			vo1.setGd_id("Z123456784");
			vo1.setSt_name("美心");
			vo1.setSt_gender("女");
			vo1.setSt_id("S000000010");
			vo1.setSt_r_address("Kouhshoung");
			vo1.setSt_address("Kouhshoung");
			vo1.setSt_password("000010");
			vo1.setSt_birthday(Date.valueOf("2019-01-11"));
			vo1.setSt_status(0);
			dao.insert(vo1);
			System.out.println("新增成功");
			System.out.println("======================");
		} catch (RuntimeException re) {
			System.out.println("請檢查該學生的監護人是否已存在(FK)");
			System.out.println("======================");
		}

		// 修改
		StudentVO vo2 = new StudentVO();
		vo2.setCs_num("C001");
		vo2.setGd_id("Z123456784");
		vo2.setSt_name("美心");
		vo2.setSt_gender("女");
		vo2.setSt_id("S000000010");
		vo2.setSt_r_address("Tainan");
		vo2.setSt_address("Tainan");
		vo2.setSt_password("000011");
		vo2.setSt_birthday(Date.valueOf("2019-01-11"));
		vo2.setSt_status(0);
		vo2.setSt_num("S011");
		dao.update(vo2);
		System.out.println("修改成功");
		System.out.println("======================");

		// 查詢全部
		List<StudentVO> list1 = dao.getAll();
		for (StudentVO vo3 : list1) {
			System.out.println("學生學號 : " + vo3.getSt_num());
			System.out.println("班級編號 : " + vo3.getCs_num());
			System.out.println("監護人身分證字號 : " + vo3.getGd_id());
			System.out.println("學生姓名 : " + vo3.getSt_name());
			System.out.println("學生性別 : " + vo3.getSt_gender());
			System.out.println("學生身分證字號 : " + vo3.getSt_id());
			System.out.println("戶籍地址 : " + vo3.getSt_r_address());
			System.out.println("通訊地址 : " + vo3.getSt_address());
			System.out.println("密碼 : " + vo3.getSt_password());
			System.out.println("生日 : " + vo3.getSt_birthday());
			System.out.println("學生狀態 : " + vo3.getSt_status());
			System.out.println("======================");
		}

		// 以學生姓名查詢
		List<StudentVO> list2 = dao.getStudentBySt_Name("正男");
		for (StudentVO vo4 : list2) {
			System.out.println("學號 : " + vo4.getSt_num());
			System.out.println("班級代號 : " + vo4.getCs_num());
			System.out.println("監護人身分證字號 : " + vo4.getGd_id());
			System.out.println("學生姓名 : " + vo4.getSt_name());
			System.out.println("學生性別 : " + vo4.getSt_gender());
			System.out.println("學生身分證字號 : " + vo4.getSt_id());
			System.out.println("戶籍地址 : " + vo4.getSt_r_address());
			System.out.println("通訊地址 : " + vo4.getSt_address());
			System.out.println("密碼 : " + vo4.getSt_password());
			System.out.println("生日 : " + vo4.getSt_birthday());
			System.out.println("學生狀態 : " + vo4.getSt_status());
			System.out.println("======================");
		}

		// 以學號查詢
		StudentVO vo5 = dao.getStudentBySt_Num("S011");
		try {
			System.out.println("學號 : " + vo5.getSt_num());
			System.out.println("班級代號 : " + vo5.getCs_num());
			System.out.println("監護人身分證字號 : " + vo5.getGd_id());
			System.out.println("學生姓名 : " + vo5.getSt_name());
			System.out.println("學生性別 : " + vo5.getSt_gender());
			System.out.println("學生身分證字號 : " + vo5.getSt_id());
			System.out.println("戶籍地址 : " + vo5.getSt_r_address());
			System.out.println("通訊地址 : " + vo5.getSt_address());
			System.out.println("密碼 : " + vo5.getSt_password());
			System.out.println("生日 : " + vo5.getSt_birthday());
			System.out.println("學生狀態 : " + vo5.getSt_status());
			System.out.println("======================");
		} catch (NullPointerException ne) {
			System.out.println("查無此學生");
			System.out.println("======================");
		}

		// 以班級代號查詢
		List<StudentVO> list3 = dao.getStudentByCs_Num("C003");
		for (StudentVO vo6 : list3) {
			System.out.println("學號 : " + vo6.getSt_num());
			System.out.println("班級代號 : " + vo6.getCs_num());
			System.out.println("監護人身分證字號 : " + vo6.getGd_id());
			System.out.println("學生姓名 : " + vo6.getSt_name());
			System.out.println("學生性別 : " + vo6.getSt_gender());
			System.out.println("學生身分證字號 : " + vo6.getSt_id());
			System.out.println("戶籍地址 : " + vo6.getSt_r_address());
			System.out.println("通訊地址 : " + vo6.getSt_address());
			System.out.println("密碼 : " + vo6.getSt_password());
			System.out.println("生日 : " + vo6.getSt_birthday());
			System.out.println("學生狀態 : " + vo6.getSt_status());
			System.out.println("======================");
		}

		// 以學生姓名及班級編號查詢學生資料
		StudentVO vo7 = dao.getStudentBySt_Name_and_Cs_Num("蠟筆小新", "C001");
		try {
			System.out.println("學號 : " + vo7.getSt_num());
			System.out.println("班級代號 : " + vo7.getCs_num());
			System.out.println("監護人身分證字號 : " + vo7.getGd_id());
			System.out.println("學生姓名 : " + vo7.getSt_name());
			System.out.println("學生性別 : " + vo7.getSt_gender());
			System.out.println("學生身分證字號 : " + vo7.getSt_id());
			System.out.println("戶籍地址 : " + vo7.getSt_r_address());
			System.out.println("通訊地址 : " + vo7.getSt_address());
			System.out.println("密碼 : " + vo7.getSt_password());
			System.out.println("生日 : " + vo7.getSt_birthday());
			System.out.println("學生狀態 : " + vo7.getSt_status());
			System.out.println("======================");
		} catch (NullPointerException ne) {
			System.out.println("查無此資料");
		}

//		// 刪除
//		dao.delete("S011");
//		System.out.println("刪除成功");
	}

	

}