package com.afterschoolclass.model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ASC_JDBCDAO implements ASCDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA102_G4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO AFTER_SCHOOL_CLASS (NURSERY_NO,ST_NUM,ST_SUM,LEVELTIME) VALUES (NURSERY_seq.NEXTVAL,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT NURSERY_NO,ST_NUM,ST_SUM,LEVELTIME FROM AFTER_SCHOOL_CLASS order by NURSERY_NO";
	private static final String GET_ONE_STMT = "SELECT NURSERY_NO,ST_NUM,ST_SUM,LEVELTIME FROM AFTER_SCHOOL_CLASS where ST_NUM = ?";
	private static final String DELETE = "DELETE FROM AFTER_SCHOOL_CLASS where NURSERY_NO = ?";
	private static final String UPDATE = "UPDATE AFTER_SCHOOL_CLASS set ST_NUM=?,ST_SUM=?,LEVELTIME=? where NURSERY_NO = ?";

	@Override
	public void insert(ASC_VO ascVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, ascVO.getSt_num());
			pstmt.setInt(2, ascVO.getSt_sum());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
			Date date = null;
			try {
				date = sdf.parse(ascVO.getLeveltime());
			} catch (ParseException e) {			
				e.printStackTrace();
			}			
			pstmt.setDate(3, (java.sql.Date) date);
			
			pstmt.executeUpdate();

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

	}

	@Override
	public void update(ASC_VO ascVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);			
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, ascVO.getSt_num());
			pstmt.setInt(2, ascVO.getSt_sum());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
			Date date = null;
			try {
				date = sdf.parse(ascVO.getLeveltime());
			} catch (ParseException e) {			
				e.printStackTrace();
			}			
			pstmt.setDate(3, (java.sql.Date) date);
			pstmt.setInt(4, ascVO.getNursery_no());
			
			pstmt.executeUpdate();

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

	}

	@Override
	public void delete(Integer nursery_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, nursery_no);

			pstmt.executeUpdate();

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

	}

	@Override
	public List<ASC_VO> findByPrimaryKey(String st_num) {

		List<ASC_VO> list = new ArrayList<ASC_VO>();
		ASC_VO ascVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, st_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ascVO = new ASC_VO();
				ascVO.setNursery_no(rs.getInt("NURSERY_NO"));
				ascVO.setSt_num(rs.getString("ST_NUM"));
				ascVO.setSt_sum(rs.getInt("ST_SUM"));
				ascVO.setLeveltime(rs.getDate("LEVELTIME"));				
				list.add(ascVO);
			}

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
	public List<ASC_VO> getAll() {
		List<ASC_VO> list = new ArrayList<ASC_VO>();
		ASC_VO ascVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ascVO = new ASC_VO();
				ascVO.setNursery_no(rs.getInt("NURSERY_NO"));
				ascVO.setSt_num(rs.getString("ST_NUM"));
				ascVO.setSt_sum(rs.getInt("ST_SUM"));
				ascVO.setLeveltime(rs.getDate("LEVELTIME"));				
				list.add(ascVO); 
			}			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());		
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

	public static void main(String[] args) {

		ASC_JDBCDAO dao = new ASC_JDBCDAO();

//		ASC_VO ascVO1 = new ASC_VO();
//		ascVO1.setSt_num("S001");
//		ascVO1.setSt_sum(500);
//		ascVO1.setLeveltime(java.sql.Date.valueOf("2019-08-28"));
//		dao.insert(ascVO1);

			//dao.delete(1007);
//		List<ASC_VO> list1 = null;
//		ASC_VO ascVO3 = dao.findByPrimaryKey("S001");	
//		
//		for(ASC_VO asc : list1)
//		list1.add(ascVO3);
//		System.out.print(ascVO3.getNursery_no() + ",");
//		System.out.print(ascVO3.getSt_sum()+ ",");
//		System.out.print(ascVO3.getSt_num() + ",");
//		System.out.print(ascVO3.getLeveltime()+ ",");
		System.out.println("---------------------");
//
//		List<ASC_VO> list = dao.getAll();
//		for (ASC_VO aEmp : list) {
//			System.out.print(aEmp.getNursery_no()+ ",");
//			System.out.print(aEmp.getSt_sum() + ",");
//			System.out.print(aEmp.getSt_num() + ",");
//			System.out.print(aEmp.getLeveltime() + ",");			
//		}
	}

	@Override
	public List<ASC_VO> insert() {
		return null;
		// TODO Auto-generated method stub
		
	}


}