package com.resform.model;

import java.util.*;


import java.sql.*;

public class ResformJDBCDAO implements ResformDAO_interface {

	private final static String driver = "oracle.jdbc.driver.OracleDriver";

	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA102_G4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO RESERVATION_FORM(res_Num,t_Num,res_Name,res_Email,res_Phone,res_Date,res_Msg) VALUES (RES_NUM.nextval, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT res_Num,t_Num,res_Name,res_Email,res_Phone,to_char(res_Date,'yyyy-mm-dd hh24:mi:ss')res_Date,res_Msg FROM RESERVATION_FORM order by res_Num";
	private static final String GET_ONE_STMT = "SELECT res_Num,t_Num,res_Name,res_Email,res_Phone,to_char(res_Date,'yyyy-mm-dd hh24:mi:ss')res_Date,res_Msg FROM RESERVATION_FORM where res_Num = ?";
	private static final String DELETE = "DELETE FROM RESERVATION_FORM where res_Num = ?";
	private static final String UPDATE = "UPDATE RESERVATION_FORM set t_Num=?, res_Name=?, res_Email=?, res_Phone=?, res_Date=? ,res_Msg=? where res_Num = ?";
	//private static final String GET_BY_RES_DATE = "SELECT * FROM RESERVATION_FORM WHERE res_Date=to_timestamp(?,'yyyy-mm-dd hh24:mi:ss')";
	private static final String GET_BY_RES_DATE = "SELECT * FROM RESERVATION_FORM WHERE to_char(res_Date,'yyyy-mm-dd hh24:mi:ss') like ?";

	@Override
	public void insert(ResformVO resformVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, resformVO.getT_Num());
			pstmt.setString(2, resformVO.getRes_Name());
			pstmt.setString(3, resformVO.getRes_Email());
			pstmt.setString(4, resformVO.getRes_Phone());
			pstmt.setTimestamp(5, resformVO.getRes_Date());
			pstmt.setString(6, resformVO.getRes_Msg());

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
	public void update(ResformVO resformVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, resformVO.getT_Num());
			pstmt.setString(2, resformVO.getRes_Name());
			pstmt.setString(3, resformVO.getRes_Email());
			pstmt.setString(4, resformVO.getRes_Phone());
			pstmt.setTimestamp(5, resformVO.getRes_Date());
			pstmt.setString(6, resformVO.getRes_Msg());
			pstmt.setInt(7, resformVO.getRes_Num());

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
	public void delete(Integer res_Num) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, res_Num);

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
	public ResformVO findByPrimaryKey(Integer res_Num) {

		ResformVO resVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, res_Num);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				resVO = new ResformVO();
				resVO.setRes_Num(rs.getInt("res_Num"));
				resVO.setT_Num(rs.getString("t_Num"));
				resVO.setRes_Name(rs.getString("res_Name"));
				resVO.setRes_Email(rs.getString("res_Email"));
				resVO.setRes_Phone(rs.getString("res_Phone"));
				resVO.setRes_Date(rs.getTimestamp("res_Date"));
				resVO.setRes_Msg(rs.getString("res_Msg"));
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
		return resVO;
	}

	@Override
	public List<ResformVO> getAll() {
		List<ResformVO> list = new ArrayList<ResformVO>();
		ResformVO resVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				resVO = new ResformVO();
				resVO.setRes_Num(rs.getInt("res_Num"));
				resVO.setT_Num(rs.getString("t_Num"));
				resVO.setRes_Name(rs.getString("res_Name"));
				resVO.setRes_Email(rs.getString("res_Email"));
				resVO.setRes_Phone(rs.getString("res_Phone"));
				resVO.setRes_Date(rs.getTimestamp("res_Date"));
				resVO.setRes_Msg(rs.getString("res_Msg"));
				list.add(resVO);
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
	public List<ResformVO> getResByres_Date(String datestr) {
		List<ResformVO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_RES_DATE);
			pstmt.setString(1, "%" + datestr + "%");
			
			rs = pstmt.executeQuery();
			list = new ArrayList<ResformVO>();

			while (rs.next()) {

				ResformVO resVO = new ResformVO();
				resVO.setRes_Num(rs.getInt("res_Num"));
				resVO.setT_Num(rs.getString("t_Num"));
				resVO.setRes_Name(rs.getString("res_Name"));
				resVO.setRes_Email(rs.getString("res_Email"));
				resVO.setRes_Phone(rs.getString("res_Phone"));
				resVO.setRes_Date(rs.getTimestamp("res_Date"));
				resVO.setRes_Msg(rs.getString("res_Msg"));
				list.add(resVO);
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

		ResformJDBCDAO dao = new ResformJDBCDAO();

		// 新增
		ResformVO resVO1 = new ResformVO();

		resVO1.setT_Num("T001");
		resVO1.setRes_Name("神龍");
		resVO1.setRes_Email("shenlong@gmail.com");
		resVO1.setRes_Phone("0933245678");
		resVO1.setRes_Date(java.sql.Timestamp.valueOf("2019-09-01 16:00:00"));
		resVO1.setRes_Msg("我能實現你一個願望");
		dao.insert(resVO1);
		System.out.println("新增成功!");

		// 修改
		ResformVO resVO2 = new ResformVO();
		resVO2.setRes_Num(1001);
		resVO2.setT_Num("T001");
		resVO2.setRes_Name("那美");
		resVO2.setRes_Email("nameishenlong@gmail.com");
		resVO2.setRes_Phone("0933245679");
		resVO2.setRes_Date(java.sql.Timestamp.valueOf("2019-09-01 14:00:00"));
		resVO2.setRes_Msg("我能實現你三個願望");
		dao.update(resVO2);
		System.out.println("修改成功!");

		// 刪除
		dao.delete(1010);
		System.out.print("刪除成功");

		// 依日期時間查詢
		String datestr = "2019-08-25";
		List<ResformVO> list = dao.getResByres_Date(datestr);
		for (ResformVO aResform : list) {
			System.out.print(aResform.getRes_Num() + ",");
			System.out.print(aResform.getT_Num() + ",");
			System.out.print(aResform.getRes_Name() + ",");
			System.out.print(aResform.getRes_Email() + ",");
			System.out.print(aResform.getRes_Phone() + ",");
			System.out.print(aResform.getRes_Date() + ",");
			System.out.print(aResform.getRes_Msg() + ",");
			System.out.println();
		}
		
		//依預約編號查詢
		
		ResformVO resformVO3 = dao.findByPrimaryKey(1001);
		System.out.print(resformVO3.getRes_Num() + ",");
		System.out.print(resformVO3.getRes_Name() + ",");
		System.out.print(resformVO3.getRes_Email() + ",");
		System.out.print(resformVO3.getRes_Phone() + ",");
		System.out.print(resformVO3.getRes_Date() + ",");
		System.out.print(resformVO3.getRes_Msg() + ",");
		System.out.println("---------------------");

		// 查詢全部
		List<ResformVO> list2 = dao.getAll();
		for (ResformVO aResform2 : list2) {
			System.out.print(aResform2.getRes_Num() + ",");
			System.out.print(aResform2.getT_Num() + ",");
			System.out.print(aResform2.getRes_Name() + ",");
			System.out.print(aResform2.getRes_Email() + ",");
			System.out.print(aResform2.getRes_Phone() + ",");
			System.out.print(aResform2.getRes_Date() + ",");
			System.out.print(aResform2.getRes_Msg());
			System.out.println();

		}
	}
}
