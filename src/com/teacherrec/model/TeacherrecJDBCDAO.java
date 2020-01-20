package com.teacherrec.model;

import java.util.*;

import java.sql.*;

public class TeacherrecJDBCDAO implements TeacherrecDAO_interface {

	private final static String driver = "oracle.jdbc.driver.OracleDriver";

	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA102_G4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO TEACHER_RECORD(r_Num,t_Num,on_Time,off_Time) VALUES (SEQ_TEACHER_RECORD.nextval, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT r_Num,t_Num,on_Time,off_Time FROM TEACHER_RECORD order by r_Num";
	private static final String GET_ONE_STMT = "SELECT r_Num,t_Num,on_Time,off_Time FROM TEACHER_RECORD where r_Num = ?";
	private static final String DELETE = "DELETE FROM TEACHER_RECORD where r_Num = ?";
	private static final String UPDATE = "UPDATE TEACHER_RECORD set t_Num=?, on_Time=?, off_Time=? where r_Num = ?";
	private static final String GET_BY_T_NUM = "SELECT * FROM TEACHER_RECORD WHERE t_Num = ?";

	@Override
	public void insert(TeacherrecVO treVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, treVO.getT_Num());
			pstmt.setTimestamp(2, treVO.getOn_Time());
			pstmt.setTimestamp(3, treVO.getOff_Time());

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
	public void update(TeacherrecVO treVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, treVO.getT_Num());
			pstmt.setTimestamp(2, treVO.getOn_Time());
			pstmt.setTimestamp(3, treVO.getOff_Time());
			pstmt.setInt(4, treVO.getR_Num());

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
	public void delete(Integer r_Num) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, r_Num);

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
	public List<TeacherrecVO> getTrecByT_num(String t_Num) {
		List<TeacherrecVO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_T_NUM);

			pstmt.setString(1, t_Num);

			rs = pstmt.executeQuery();
			list = new ArrayList<TeacherrecVO>();

			while (rs.next()) {
				TeacherrecVO trecVO = new TeacherrecVO();
				trecVO = new TeacherrecVO();
				trecVO.setR_Num(rs.getInt("r_Num"));
				trecVO.setT_Num(rs.getString("t_Num"));
				trecVO.setOn_Time(rs.getTimestamp("on_Time"));
				trecVO.setOff_Time(rs.getTimestamp("off_Time"));
				list.add(trecVO);
				
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
	public List<TeacherrecVO> getAll() {
		List<TeacherrecVO> list = new ArrayList<TeacherrecVO>();
		TeacherrecVO trecVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				trecVO = new TeacherrecVO();
				trecVO.setR_Num(rs.getInt("r_Num"));
				trecVO.setT_Num(rs.getString("t_Num"));
				trecVO.setOn_Time(rs.getTimestamp("on_Time"));
				trecVO.setOff_Time(rs.getTimestamp("off_Time"));
				list.add(trecVO); 
			}

			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
		
	}

	public static void main(String[] args) {
		TeacherrecJDBCDAO dao = new TeacherrecJDBCDAO();

		// 新增
		TeacherrecVO trecVO1 = new TeacherrecVO();
		
		trecVO1.setT_Num("T002");
		trecVO1.setOn_Time(java.sql.Timestamp.valueOf("2019-09-02 07:15:00"));
		trecVO1.setOff_Time(java.sql.Timestamp.valueOf("2019-09-02 16:15:00"));
		dao.insert(trecVO1);
		System.out.println("新增成功");

		// 修改
		TeacherrecVO trecVO2 = new TeacherrecVO();
		trecVO2.setR_Num(1001);
		trecVO2.setT_Num("T002");
		trecVO2.setOn_Time(java.sql.Timestamp.valueOf("2019-09-02 07:15:00"));
		trecVO2.setOff_Time(java.sql.Timestamp.valueOf("2019-09-02 16:16:00"));
		dao.update(trecVO2);
		System.out.println("修改成功");

		// 刪除
		dao.delete(1001);
		System.out.print("刪除成功");
		
		//以教師編號查詢
		String t_Num = "T002";
		List<TeacherrecVO> list = dao.getTrecByT_num("T002");
		for (TeacherrecVO aTrec : list) {
			System.out.print(aTrec.getR_Num() + ",");
			System.out.println(aTrec.getT_Num()+ ",");
			System.out.println(aTrec.getOn_Time() + ",");
			System.out.println(aTrec.getOff_Time() + ",");
			System.out.println();
			
		}
		
		//查詢全部
		List<TeacherrecVO> list2 = dao.getAll();
		for (TeacherrecVO aTrec2 : list2) {
			System.out.print(aTrec2.getR_Num() + ",");
			System.out.print(aTrec2.getT_Num() + ",");
			System.out.print(aTrec2.getOn_Time() + ",");
			System.out.print(aTrec2.getOff_Time());
			System.out.println();

		}
	}

}
