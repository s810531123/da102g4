package com._class.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ClassJNDIDAO implements ClassDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA102_G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO CLASS VALUES ('C'||LPAD(to_char(SEQ_CLASS.NEXTVAL), 3, '0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM CLASS order by cs_num";
	private static final String GET_ONE_STMT = "SELECT * FROM CLASS WHERE CS_NUM = ?";
	private static final String DELETE = "DELETE FROM CLASS WHERE CS_NUM = ?";
	private static final String UPDATE = "UPDATE CLASS SET T_NUM_1 = ?, T_NUM_2 = ?, CS_NAME = ?, CS_YEARS = ? WHERE CS_NUM = ?";

	@Override
	public void insert(ClassVO classVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, classVO.getT_num_1());
			pstmt.setString(2, classVO.getT_num_2());
			pstmt.setString(3, classVO.getCs_name());
			pstmt.setInt(4, classVO.getCs_years());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("Couldn't load database driver. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String cs_num) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
		
			pstmt = con.prepareStatement(DELETE);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			pstmt.setString(1, cs_num);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);

		
		} catch (SQLException se) {
			throw new RuntimeException("Couldn't load database driver. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(ClassVO classVO) {

		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
		
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(5, classVO.getCs_num());
			pstmt.setString(1, classVO.getT_num_1());
			pstmt.setString(2, classVO.getT_num_2());
			pstmt.setString(3, classVO.getCs_name());
			pstmt.setInt(4, classVO.getCs_years());

			pstmt.executeUpdate();

		
		} catch (SQLException se) {
			throw new RuntimeException("Couldn't load database driver. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public ClassVO findByPrimaryKey(String cs_num) {
		
		ClassVO classVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, cs_num);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				classVO = new ClassVO();
				classVO.setCs_num(rs.getString("cs_num"));
				classVO.setT_num_1(rs.getString("t_num_1"));
				classVO.setT_num_2(rs.getString("t_num_2"));
				classVO.setCs_name(rs.getString("cs_name"));
				classVO.setCs_years(rs.getInt("cs_years"));
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("Couldn't load database driver. "
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return classVO;
	}

	@Override
	public List<ClassVO> getAll() {
		
		List<ClassVO> list = new ArrayList<ClassVO>();
		ClassVO classVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try {
			
			con = ds.getConnection();
	
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				classVO = new ClassVO();
				classVO.setCs_num(rs.getString("cs_num"));
				classVO.setT_num_1(rs.getString("t_num_1"));
				classVO.setT_num_2(rs.getString("t_num_2"));
				classVO.setCs_name(rs.getString("cs_name"));
				classVO.setCs_years(rs.getInt("cs_years"));
				list.add(classVO);
			}
		
		}catch (SQLException se) {
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
	
	public static void main(String args[]) {
		
		ClassJNDIDAO dao = new ClassJNDIDAO();
		
		//新增
//		ClassVO classVO = new ClassVO();
//		classVO.setT_num_1("T005");
//		classVO.setT_num_2("T006");
//		classVO.setCs_name("大大班");
//		classVO.setCs_years(108);
//		dao.insert(classVO);
		
		//刪除
//		dao.delete("C004");
		
		//修改
//		ClassVO classVO = new ClassVO();
//		classVO.setCs_num("C003");
//		classVO.setT_num_1("T002");
//		classVO.setT_num_2("T003");
//		classVO.setCs_name("DA102");
//		classVO.setCs_years(108);
//		dao.update(classVO);
		
		//查PK
//		ClassVO classVO = dao.findByPrimaryKey("C001");
//		System.out.println(classVO.getCs_num());
//		System.out.println(classVO.getT_num_1());
//		System.out.println(classVO.getT_num_2());
//		System.out.println(classVO.getCs_name());
//		System.out.println(classVO.getCs_years());
		
//		// GetAll
//		List<ClassVO> list = dao.getAll();
//		for (ClassVO aClass : list) {
//			System.out.println(aClass.getCs_num());
//			System.out.println(aClass.getT_num_1());
//			System.out.println(aClass.getT_num_2());
//			System.out.println(aClass.getCs_name());
//			System.out.println(aClass.getCs_years());
//			System.out.println();
//		}
	}

}
