package com.guardian.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GuardianJNDIDAO implements GuardianDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA102_G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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

			con = ds.getConnection();
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
	public void update(GuardianVO guardianVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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
	public void delete(String gd_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, gd_id);

			pstmt.executeUpdate();

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
	public List<GuardianVO> getAll() {
		List<GuardianVO> list = new ArrayList<GuardianVO>();
		GuardianVO guardianVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
	public GuardianVO getGuardianByGd_Id(String gd_id) {
		Set<GuardianVO> set = new LinkedHashSet<GuardianVO>();
		GuardianVO guardianVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
}
