package com.prescription.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com._class.model.ClassVO;

public class PrescriptionDAO implements PrescriptionDAO_interface{
	
	
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA102_G4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO PRESCRIPTION VALUES (SEQ_PRESCRIPTION.NEXTVAL, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM PRESCRIPTION";
	private static final String GET_ONE_STMT = "SELECT * FROM PRESCRIPTION WHERE PRE_ID = ?";
	private static final String DELETE = "DELETE FROM PRESCRIPTION WHERE PRE_ID = ?";
	private static final String UPDATE = "UPDATE PRESCRIPTION SET ST_NUM = ?, PRE_CONTENT = ?, PRE_TIME = ? WHERE PRE_ID = ?";
	
	

	@Override
	public void insert(PrescriptionVO prescriptionVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, prescriptionVO.getSt_num());
			pstmt.setString(2, prescriptionVO.getPre_content());
			pstmt.setTimestamp(3, prescriptionVO.getPre_time());
			

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(Integer pre_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			pstmt.setInt(1, pre_id);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(PrescriptionVO prescriptionVO) {

		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, prescriptionVO.getSt_num());
			pstmt.setString(2, prescriptionVO.getPre_content());
			pstmt.setTimestamp(3, prescriptionVO.getPre_time());
			pstmt.setInt(4, prescriptionVO.getPre_id());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public PrescriptionVO findByPrimaryKey(Integer pre_id) {
		
		PrescriptionVO prescriptionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, pre_id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				prescriptionVO = new PrescriptionVO();
				prescriptionVO.setPre_id(rs.getInt("pre_id"));
				prescriptionVO.setSt_num(rs.getString("st_num"));
				prescriptionVO.setPre_content(rs.getString("pre_content"));
				prescriptionVO.setPre_time(rs.getTimestamp("pre_time"));
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return prescriptionVO;
	}

	@Override
	public List<PrescriptionVO> getAll() {
		List<PrescriptionVO> list = new ArrayList<PrescriptionVO>();
		PrescriptionVO prescriptionVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				prescriptionVO = new PrescriptionVO();
				prescriptionVO.setPre_id(rs.getInt("pre_id"));
				prescriptionVO.setSt_num(rs.getString("st_num"));
				prescriptionVO.setPre_content(rs.getString("pre_content"));
				prescriptionVO.setPre_time(rs.getTimestamp("pre_time"));
				list.add(prescriptionVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	
	public static void main(String[] args) {
		
		PrescriptionDAO dao = new PrescriptionDAO();
		
		//新增
//		PrescriptionVO prescriptionVO = new PrescriptionVO();
//		prescriptionVO.setSt_num("S002");
//		prescriptionVO.setPre_content("有病要吃藥lll.....");
//		prescriptionVO.setPre_time(java.sql.Timestamp.valueOf("2019-08-15 10:00:00"));
//		dao.insert(prescriptionVO);
		
		//刪除
//		dao.delete(1011);
		
		//修改
//		PrescriptionVO prescriptionVO = new PrescriptionVO();
//		prescriptionVO.setPre_id(1007);
//		prescriptionVO.setSt_num("S002");
//		prescriptionVO.setPre_content("有病要吃藥阿...");
//		prescriptionVO.setPre_time(java.sql.Timestamp.valueOf("2019-08-15 12:00:00"));
//		dao.update(prescriptionVO);
		
		//查PK
//		PrescriptionVO prescriptionVO = dao.findByPrimaryKey(1007);
//		System.out.println(prescriptionVO.getPre_id());
//		System.out.println(prescriptionVO.getSt_num());
//		System.out.println(prescriptionVO.getPre_content());
//		System.out.println(prescriptionVO.getPre_time());
//		System.out.println();
		
		// GetAll
//		List<PrescriptionVO> list = dao.getAll();
//		for (PrescriptionVO aPre : list) {
//			System.out.println(aPre.getPre_id());
//			System.out.println(aPre.getSt_num());
//			System.out.println(aPre.getPre_content());
//			System.out.println(aPre.getPre_time());
//			System.out.println();
//		}
		
	}

}
