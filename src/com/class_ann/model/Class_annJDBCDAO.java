package com.class_ann.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Class_annJDBCDAO implements Class_annDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA102_G4";
	String passwd = "123456";

	private static final String INSERT_STMT =
			"INSERT INTO class_announcement (cs_ann_num,cs_num,cs_ann_text,cs_ann_date,cs_ann_ti,cs_ann_kind)VALUES(seq_class_announcement.NEXTVAL,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT cs_ann_num,cs_num,cs_ann_text,to_char(cs_ann_date,'yyyy-mm-dd')cs_ann_date,cs_ann_ti,cs_ann_kind FROM class_announcement order by cs_ann_num";
	private static final String GET_ONE_STMT =
			"SELECT cs_ann_num,cs_num,cs_ann_text,to_char(cs_ann_date,'yyyy-mm-dd')cs_ann_date,cs_ann_ti,cs_ann_kind FROM class_announcement where cs_ann_num = ?";
	private static final String DELETE = 
			"DELETE FROM class_announcement where cs_ann_num =?";
	private static final String UPDATE =
			"UPDATE class_announcement set cs_num=?, cs_ann_kind=?, cs_ann_ti=?, cs_ann_text=? ,cs_ann_date=? where cs_ann_num=?";
	
	@Override
	public void insert(Class_annVO class_annVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,class_annVO.getCs_num());
			pstmt.setString(2, class_annVO.getCs_ann_text());
			pstmt.setDate(3, class_annVO.getCs_ann_date());
			pstmt.setString(4, class_annVO.getCs_ann_ti());
			pstmt.setString(5, class_annVO.getCs_ann_kind());
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
	public void update(Class_annVO class_annVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1,class_annVO.getCs_num());
			pstmt.setString(2, class_annVO.getCs_ann_kind());
			pstmt.setString(3, class_annVO.getCs_ann_ti());
			pstmt.setString(4, class_annVO.getCs_ann_text());
			pstmt.setDate(5, class_annVO.getCs_ann_date());
			pstmt.setInt(6, class_annVO.getCs_ann_num());
		

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer cs_ann_num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, cs_ann_num);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public Class_annVO findByPrimaryKey(Integer cs_ann_num) {
		Class_annVO class_annVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, cs_ann_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				class_annVO = new Class_annVO();
				class_annVO.setCs_ann_num(rs.getInt("cs_ann_num"));
				class_annVO.setCs_num(rs.getString("cs_num"));
				class_annVO.setCs_ann_text(rs.getString("cs_ann_text"));
				class_annVO.setCs_ann_date(rs.getDate("cs_ann_date"));
				class_annVO.setCs_ann_ti(rs.getString("cs_ann_ti"));
				class_annVO.setCs_ann_kind(rs.getString("cs_ann_kind"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return class_annVO;
		
	}

	
	
	@Override
	public List<Class_annVO> getAll() {
		List<Class_annVO> list = new ArrayList<Class_annVO>();
		Class_annVO class_annVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				class_annVO = new Class_annVO();
				class_annVO.setCs_ann_num(rs.getInt("cs_ann_num"));
				class_annVO.setCs_num(rs.getString("cs_num"));
				class_annVO.setCs_ann_text(rs.getString("cs_ann_text"));
				class_annVO.setCs_ann_date(rs.getDate("cs_ann_date"));
				class_annVO.setCs_ann_ti(rs.getString("cs_ann_ti"));
				class_annVO.setCs_ann_kind(rs.getString("cs_ann_kind"));
				list.add(class_annVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
	
	public static void main(String[] args) {
		Class_annJDBCDAO dao = new Class_annJDBCDAO();
		
		
		// 新增(successed)
//		Class_annVO class_annVO1 = new Class_annVO();
//		class_annVO1.setCs_num("C001");
//		class_annVO1.setCs_ann_text("測試新增測試");
//		class_annVO1.setCs_ann_date(java.sql.Date.valueOf("2019-01-04"));
//		class_annVO1.setCs_ann_ti("測試測試測試");
//		class_annVO1.setCs_ann_kind("班級活動");
//		dao.insert(class_annVO1);
		
		
		
//		// 修改(successed)
//		Class_annVO class_annVO2 = new Class_annVO();
//		class_annVO2.setCs_ann_num(1013);
//		class_annVO2.setCs_num("C001");
//		class_annVO2.setCs_ann_kind("失物招領");
//		class_annVO2.setCs_ann_ti("有人失物招領嗎.......");
//		class_annVO2.setCs_ann_text("修改測試修改");
//		class_annVO2.setCs_ann_date(java.sql.Date.valueOf("2019-01-04"));
//		
//	
//		
//		dao.update(class_annVO2);
		
		
//		// 刪除(successed)
//		dao.delete(1014);
		
		
		// 查詢(successed)
//		Class_annVO class_annVO3 = dao.findByPrimaryKey(1002);
//		System.out.print(class_annVO3.getCs_ann_num() + ",");
//		System.out.print(class_annVO3.getCs_num() + ",");
//		System.out.print(class_annVO3.getCs_ann_kind() + ",");
//		System.out.print(class_annVO3.getCs_ann_ti() + ",");
//		System.out.print(class_annVO3.getCs_ann_text() + ",");
//		System.out.print(class_annVO3.getCs_ann_date() + ",");
//		
//		
//		System.out.println("---------------------");
		
		
		//查詢全部(successed)
//		List<Class_annVO> list = dao.getAll();
//		for (Class_annVO aClass_ann : list) {
//			System.out.print(aClass_ann.getCs_ann_num() + ",");
//			System.out.print(aClass_ann.getCs_num() + ",");
//			System.out.print(aClass_ann.getCs_ann_text() + ",");
//			System.out.print(aClass_ann.getCs_ann_date() + ",");
//			System.out.print(aClass_ann.getCs_ann_ti() + ",");
//			System.out.print(aClass_ann.getCs_ann_kind() + ",");
//
//			System.out.println();
//		}
		
	}
}