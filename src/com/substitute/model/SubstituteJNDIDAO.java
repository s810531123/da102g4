package com.substitute.model;

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

public class SubstituteJNDIDAO implements SubstituteDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA102_G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO SUBSTITUTE  ( SUB_NUM, CS_NUM, T_NUM, SUB_SDATE, SUB_EDATE ) VALUES (sub_seq.NEXTVAL, ? ,?, ?, ? ) ";

	private static final String UPDATE = "UPDATE SUBSTITUTE SET CS_NUM=?, T_NUM=?, SUB_SDATE=?, SUB_EDATE=? WHERE SUB_NUM=? ";

	private static final String GET_ONE_STMT = "SELECT SUB_NUM, CS_NUM, T_NUM, to_char(SUB_SDATE,'yyyy-mm-dd')SUB_SDATE, to_char(SUB_EDATE,'yyyy-mm-dd')SUB_EDATE FROM SUBSTITUTE WHERE SUB_NUM = ?";

	private static final String GET_ALL_STMT = "SELECT SUB_NUM, CS_NUM, T_NUM, to_char(SUB_SDATE,'yyyy-mm-dd')SUB_SDATE, to_char(SUB_EDATE,'yyyy-mm-dd')SUB_EDATE FROM SUBSTITUTE ORDER BY SUB_SDATE DESC ";


	@Override
	public void insert(SubstituteVO substituteVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, substituteVO.getCs_Num());
			pstmt.setString(2, substituteVO.getT_Num());
			pstmt.setDate(3, substituteVO.getSub_Sdate());
			pstmt.setDate(4, substituteVO.getSub_Edate());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured" + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					throw new RuntimeException("A database error occured" + se.getMessage());
				}
			}
		}
	}

	@Override
	public void update(SubstituteVO substituteVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, substituteVO.getCs_Num());
			pstmt.setString(2, substituteVO.getT_Num());
			pstmt.setDate(3, substituteVO.getSub_Sdate());
			pstmt.setDate(4, substituteVO.getSub_Edate());
			pstmt.setInt(5, substituteVO.getSub_Num());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured" + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					throw new RuntimeException("A database error occured" + se.getMessage());
				}
			}
		}
	}

	@Override
	public SubstituteVO findByPrimaryKey(Integer sub_Num) {

		SubstituteVO substituteVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, sub_Num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				substituteVO = new SubstituteVO();
				substituteVO.setSub_Num(rs.getInt("sub_Num"));
				substituteVO.setCs_Num(rs.getString("cs_Num"));
				substituteVO.setT_Num(rs.getString("t_Num"));
				substituteVO.setSub_Sdate(rs.getDate("sub_Sdate"));
				substituteVO.setSub_Edate(rs.getDate("sub_Edate"));
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
		return substituteVO;
	}

	@Override
	public List<SubstituteVO> getAll() {

		List<SubstituteVO> list = new ArrayList<SubstituteVO>();
		SubstituteVO substituteVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				substituteVO = new SubstituteVO();
				substituteVO.setSub_Num(rs.getInt("sub_Num"));
				substituteVO.setCs_Num(rs.getString("cs_Num"));
				substituteVO.setT_Num(rs.getString("t_Num"));
				substituteVO.setSub_Sdate(rs.getDate("sub_Sdate"));
				substituteVO.setSub_Edate(rs.getDate("sub_Edate"));
				list.add(substituteVO);
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
		return list;
	}

	public static void main(String[] args) {

		SubstituteJNDIDAO dao = new SubstituteJNDIDAO();

		// 新增
		SubstituteVO substituteVO1 = new SubstituteVO();
		substituteVO1.setCs_Num("C001");
		substituteVO1.setT_Num("T008");
		substituteVO1.setSub_Sdate(java.sql.Date.valueOf("2019-10-01"));
		substituteVO1.setSub_Edate(java.sql.Date.valueOf("2019-10-01"));
		dao.insert(substituteVO1);

		// 修改
		SubstituteVO substituteVO2 = new SubstituteVO();
		substituteVO2.setSub_Num(1011);
		substituteVO2.setCs_Num("C001");
		substituteVO2.setT_Num("T009");
		substituteVO2.setSub_Sdate(java.sql.Date.valueOf("2019-10-01"));
		substituteVO2.setSub_Edate(java.sql.Date.valueOf("2019-10-01"));
		dao.update(substituteVO2);

		// 查詢一筆
		Integer sub_Num = 1001;
		SubstituteVO substituteVO3 = dao.findByPrimaryKey(sub_Num);
		System.out.print(substituteVO3.getSub_Num() + ",");
		System.out.print(substituteVO3.getCs_Num() + ",");
		System.out.print(substituteVO3.getT_Num() + ",");
		System.out.print(substituteVO3.getSub_Sdate() + ",");
		System.out.println(substituteVO3.getSub_Edate());
		System.out.println("---------------------");

		// 查詢全部
		List<SubstituteVO> list = dao.getAll();
		for (SubstituteVO aSubstituteVO : list) {
			System.out.print(aSubstituteVO.getSub_Num() + ",");
			System.out.print(aSubstituteVO.getCs_Num() + ",");
			System.out.print(aSubstituteVO.getT_Num() + ",");
			System.out.print(aSubstituteVO.getSub_Sdate() + ",");
			System.out.println(aSubstituteVO.getSub_Edate());
			System.out.println();
		}
	}

}
