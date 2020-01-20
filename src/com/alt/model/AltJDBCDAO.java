package com.alt.model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class AltJDBCDAO implements AltDAO_interface {

	private final static String driver = "oracle.jdbc.driver.OracleDriver";

	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA102_G4";
	String passwd = "123456";
/* SEQ_ARRIVE_LEAVE_TIME.nextval */
	private static final String INSERT_STMT = "INSERT INTO ARRIVE_LEAVE_TIME(alt_No,st_Num,alt_Arr,alt_Lea,alt_Leat) VALUES (SEQ_ARRIVE_LEAVE_TIME.nextval, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT alt_No,st_Num,alt_Arr,alt_Lea,alt_Leat FROM ARRIVE_LEAVE_TIME order by alt_No";
	private static final String GET_ONE_STMT = "SELECT alt_No,st_Num,alt_Arr,alt_Lea,alt_Leat FROM ARRIVE_LEAVE_TIME where alt_No = ?";
	private static final String DELETE = "DELETE FROM ARRIVE_LEAVE_TIME where alt_No = ?";
	private static final String UPDATE = "UPDATE ARRIVE_LEAVE_TIME set st_Num=?, alt_Arr=?, alt_Lea=?, alt_Leat=? where alt_No = ?";
	private static final String UPDATE_ALTARR_OR_ALTLEA = "UPDATE ARRIVE_LEAVE_TIME set alt_Arr=?, alt_Lea=? WHERE ST_NUM = ? AND ((ALT_ARR >= TO_DATE(?,'YYYY-MM-DD') AND ALT_ARR <= TO_DATE(?,'YYYY-MM-DD')) OR (ALT_LEA >= TO_DATE(?,'YYYY-MM-DD') AND ALT_LEA <= TO_DATE(?,'YYYY-MM-DD')))";
	private static final String SELECT_ALTARR_OR_ALTLEA = "SELECT * FROM ARRIVE_LEAVE_TIME WHERE ST_NUM = ? AND ((ALT_ARR >= TO_DATE(?,'YYYY-MM-DD') AND ALT_ARR <= TO_DATE(?,'YYYY-MM-DD')) OR (ALT_LEA >= TO_DATE(?,'YYYY-MM-DD') AND ALT_LEA <= TO_DATE(?,'YYYY-MM-DD')))";
	private static final String GET_BY_ST_NUM = "SELECT * FROM ARRIVE_LEAVE_TIME WHERE st_Num = ?";

	@Override
	public void insert(AltVO altVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
//			pstmt.setInt(1, 1026);
//			pstmt.setString(2, altVO.getSt_Num());
//			pstmt.setTimestamp(3, altVO.getAlt_Arr());
//			pstmt.setTimestamp(4, altVO.getAlt_Lea());
//			pstmt.setTimestamp(5, altVO.getAlt_Leat());
			
			
			pstmt.setString(1, altVO.getSt_Num());
			pstmt.setTimestamp(2, altVO.getAlt_Arr());
			pstmt.setTimestamp(3, altVO.getAlt_Lea());
			pstmt.setTimestamp(4, altVO.getAlt_Leat());

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
	public void update(AltVO altVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, altVO.getSt_Num());
			pstmt.setTimestamp(2, altVO.getAlt_Arr());
			pstmt.setTimestamp(3, altVO.getAlt_Lea());
			pstmt.setTimestamp(4, altVO.getAlt_Leat());
			pstmt.setInt(5, altVO.getAlt_No());

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
	public void delete(Integer alt_No) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, alt_No);

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
	public AltVO findByPrimaryKey(Integer alt_No) {

		AltVO altVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, alt_No);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				altVO = new AltVO();
				altVO.setAlt_No(rs.getInt("alt_No"));
				altVO.setSt_Num(rs.getString("st_Num"));
				altVO.setAlt_Arr(rs.getTimestamp("alt_Arr"));
				altVO.setAlt_Lea(rs.getTimestamp("alt_Lea"));
				altVO.setAlt_Leat(rs.getTimestamp("alt_Leat"));
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
		return altVO;
	}

	@Override
	public List<AltVO> getAll() {
		List<AltVO> list = new ArrayList<AltVO>();
		AltVO altVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				altVO = new AltVO();
				altVO.setAlt_No(rs.getInt("alt_No"));
				altVO.setSt_Num(rs.getString("st_Num"));
				altVO.setAlt_Arr(rs.getTimestamp("alt_Arr"));
				altVO.setAlt_Lea(rs.getTimestamp("alt_Lea"));
				altVO.setAlt_Leat(rs.getTimestamp("alt_Leat"));
				list.add(altVO);
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

	@Override
	public List<AltVO> getAltBySt_Num(String st_Num) {
		List<AltVO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_ST_NUM);
			pstmt.setString(1, st_Num);

			rs = pstmt.executeQuery();
			list = new ArrayList<AltVO>();

			while (rs.next()) {
				AltVO altVO = new AltVO();
				altVO.setAlt_No(rs.getInt("alt_No"));
				altVO.setSt_Num(rs.getString("st_Num"));
				altVO.setAlt_Arr(rs.getTimestamp("alt_Arr"));
				altVO.setAlt_Lea(rs.getTimestamp("alt_Lea"));
				altVO.setAlt_Leat(rs.getTimestamp("alt_Leat"));
				list.add(altVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
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

//		AltJDBCDAO dao = new AltJDBCDAO();
//
//		// 新增
//		AltVO altVO1 = new AltVO();
//		altVO1.setSt_Num("S002");
//		altVO1.setAlt_Arr(java.sql.Timestamp.valueOf("2019-09-01 08:21:00"));
//		altVO1.setAlt_Lea(java.sql.Timestamp.valueOf("2019-09-01 14:05:00"));
//		altVO1.setAlt_Leat(java.sql.Timestamp.valueOf("2019-09-01 16:00:00"));
//		dao.insert(altVO1);
//		System.out.println("新增成功!");
//
//		// 修改
//		AltVO altVO2 = new AltVO();
//		altVO2.setAlt_No(1012);
//		altVO2.setSt_Num("S002");
//		altVO2.setAlt_Arr(java.sql.Timestamp.valueOf("2019-09-01 08:25:00"));
//		altVO2.setAlt_Lea(java.sql.Timestamp.valueOf("2019-09-01 14:15:00"));
//		altVO2.setAlt_Leat(java.sql.Timestamp.valueOf("2019-09-01 20:00:00"));
//		dao.update(altVO2);
//		System.out.println("修改成功!");
//
//		// 刪除
//		dao.delete(1013);
//		System.out.print("刪除成功");
//
//		// 依學號查詢
//		String st_Num = "S001";
//		List<AltVO> list = dao.getAltBySt_Num("S001");
//		for (AltVO aAlt : list) {
//			System.out.print(aAlt.getAlt_No() + ",");
//			System.out.print(aAlt.getSt_Num() + ",");
//			System.out.print(aAlt.getAlt_Arr() + ",");
//			System.out.print(aAlt.getAlt_Lea() + ",");
//			System.out.print(aAlt.getAlt_Leat());
//			System.out.println();
//		}
//
//		// 查詢全部
//		List<AltVO> list2 = dao.getAll();
//		for (AltVO aAlt2 : list2) {
//			System.out.print(aAlt2.getAlt_No() + ",");
//			System.out.print(aAlt2.getSt_Num() + ",");
//			System.out.print(aAlt2.getAlt_Arr() + ",");
//			System.out.print(aAlt2.getAlt_Lea() + ",");
//			System.out.print(aAlt2.getAlt_Leat());
//
//			System.out.println();
//		}
		
		
	}

	@Override
	public void updateArrOrLea(AltVO altVO, String date) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_ALTARR_OR_ALTLEA);

			pstmt.setTimestamp(1,altVO.getAlt_Arr());
			pstmt.setTimestamp(2,altVO.getAlt_Lea());
			pstmt.setString(3,altVO.getSt_Num());
			pstmt.setString(4, date);
			
			java.sql.Date sqldate = java.sql.Date.valueOf(date);
			java.sql.Date sqlnext = new java.sql.Date(sqldate.getTime() + (86400 * 1000)) ;
			
			pstmt.setString(5, sqlnext.toString());
			pstmt.setString(6, date);
			pstmt.setString(7, sqlnext.toString());
			pstmt.executeUpdate();

			

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
	}

	@Override
	public AltVO getOneByDate(String st_num, String date) {
		AltVO altVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ALTARR_OR_ALTLEA);

			pstmt.setString(1, st_num);

			pstmt.setString(2, date);
			
			java.sql.Date sqldate = java.sql.Date.valueOf(date);
			java.sql.Date sqlnext = new java.sql.Date(sqldate.getTime() + (86400 * 1000)) ;
			
			pstmt.setString(3,sqlnext.toString());
			pstmt.setString(4, date);
			pstmt.setString(5, sqlnext.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {

				altVO = new AltVO();
				altVO.setAlt_No(rs.getInt("alt_No"));
				altVO.setSt_Num(rs.getString("st_Num"));
				altVO.setAlt_Arr(rs.getTimestamp("alt_Arr"));
				altVO.setAlt_Lea(rs.getTimestamp("alt_Lea"));
				altVO.setAlt_Leat(rs.getTimestamp("alt_Leat"));
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
		return altVO;
	}
}
