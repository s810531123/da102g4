package com.anno.model;

import java.sql.*;
import java.util.*;

public class AnnoJDBCDAO implements AnnoDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "DA102_G4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO announcement(anno_no , anno_cla , anno_title , anno_text , anno_date ) VALUES (anno_no.nextval, ?, ? , ?, ?)";
	private static final String GET_ALL_STMT = "SELECT anno_no , anno_cla , anno_title , anno_text , anno_date FROM announcement order by anno_no ";
	private static final String GET_ONE_STMT = "SELECT anno_no , anno_cla , anno_title , anno_text , anno_date FROM announcement where anno_no = ? ";
	private static final String DELETE = "DELETE FROM announcement where anno_no = ?";
	private static final String UPDATE = "UPDATE announcement set anno_cla = ? , anno_title = ? , anno_text = ? , anno_date = ? where anno_no = ?";

	public void insert(AnnoVO annoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, annoVO.getAnno_cla());
			pstmt.setString(2, annoVO.getAnno_title());
			pstmt.setString(3, annoVO.getAnno_text());
			pstmt.setDate(4, annoVO.getAnno_date());

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
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}

	}

	public void update(AnnoVO annoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, annoVO.getAnno_cla());
			pstmt.setString(2, annoVO.getAnno_title());
			pstmt.setString(3, annoVO.getAnno_text());
			pstmt.setDate(4, annoVO.getAnno_date());
			pstmt.setInt(5, annoVO.getAnno_no());

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

	public void delete(Integer anno_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, anno_no);

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

	public AnnoVO findByPrimaryKey(Integer anno_no) {
		AnnoVO annoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, anno_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				annoVO = new AnnoVO();
				annoVO.setAnno_no(rs.getInt("anno_no"));
				annoVO.setAnno_cla(rs.getString("anno_cla"));
				annoVO.setAnno_title(rs.getString("anno_title"));
				annoVO.setAnno_text(rs.getString("anno_text"));
				annoVO.setAnno_date(rs.getDate("anno_date"));

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
		return annoVO;
	}

	public List<AnnoVO> getAll() {
		List<AnnoVO> list = new ArrayList<AnnoVO>();
		AnnoVO annoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				annoVO = new AnnoVO();
				annoVO.setAnno_no(rs.getInt("anno_no"));
				annoVO.setAnno_cla(rs.getString("anno_cla"));
				annoVO.setAnno_title(rs.getString("anno_title"));
				annoVO.setAnno_text(rs.getString("anno_text"));
				annoVO.setAnno_date(rs.getDate("anno_date"));
				list.add(annoVO);
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

	public static void main(String[] args) {
		AnnoDAO_interface dao = new AnnoJDBCDAO();

		// 新增
		AnnoVO annoVO1 = new AnnoVO();
		annoVO1.setAnno_no(1011);
		annoVO1.setAnno_cla("當月活動");
		annoVO1.setAnno_title("運動會");
		annoVO1.setAnno_text("今天是一年一度的運動會");
		annoVO1.setAnno_date(java.sql.Date.valueOf("2019-08-15"));
		dao.insert(annoVO1);

		// 修改
		AnnoVO annoVO2 = new AnnoVO();
		annoVO2.setAnno_no(1012);
		annoVO2.setAnno_cla("停課訊息");
		annoVO2.setAnno_title("颱風停課");
		annoVO2.setAnno_text("今日颱風，停課一天");
		annoVO2.setAnno_date(java.sql.Date.valueOf("2019-08-15"));
		dao.update(annoVO2);

		// 刪除
		dao.delete(1011);

		// 查詢
		AnnoVO annoVO3 = dao.findByPrimaryKey(1002);
		System.out.print(annoVO3.getAnno_no() + ",");
		System.out.print(annoVO3.getAnno_cla() + ",");
		System.out.print(annoVO3.getAnno_title() + ",");
		System.out.print(annoVO3.getAnno_text() + ",");
		System.out.print(annoVO3.getAnno_date());
		System.out.println("---------------------");

		// 查詢
		List<AnnoVO> list = dao.getAll();
		for (AnnoVO aAnno : list) {
			System.out.print(aAnno.getAnno_no() + ",");
			System.out.print(aAnno.getAnno_cla() + ",");
			System.out.print(aAnno.getAnno_title() + ",");
			System.out.print(aAnno.getAnno_text() + ",");
			System.out.print(aAnno.getAnno_date());
			System.out.println();
		}

	}

//	

}
