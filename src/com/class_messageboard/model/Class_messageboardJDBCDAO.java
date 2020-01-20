package com.class_messageboard.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Class_messageboardJDBCDAO implements Class_messageboardDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA102_G4";
	String passwd = "123456";
	
	private static final String INSERT_BACK_STMT =
			"INSERT INTO class_messageboard (msg_num,cs_num,t_num,msg)VALUES(class_messageboard_seq.NEXTVAL,?,?,?)";
	private static final String INSERT_FONT_STMT =
			"INSERT INTO class_messageboard (msg_num,cs_num,gd_id,msg)VALUES(class_messageboard_seq.NEXTVAL,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT msg_num,cs_num,gd_id,t_num,msg FROM class_messageboard order by msg_num";
	private static final String GET_ONE_STMT =
			"SELECT msg_num,cs_num,gd_id,t_num,msg FROM class_messageboard where msg_num = ?";
	private static final String DELETE = 
			"DELETE FROM class_messageboard where msg_num=?";
	private static final String UPDATE =
			"UPDATE class_messageboard set cs_num=?, gd_id=?, t_num=?, msg=?  where msg_num=?";
	
	
	@Override
	public void insertBack(Class_messageboardVO class_messageboardVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_BACK_STMT);
			
			pstmt.setString(1,class_messageboardVO.getCs_num());
			pstmt.setString(2, class_messageboardVO.getT_num());
			pstmt.setString(3, class_messageboardVO.getMsg());
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
	public void insertFont(Class_messageboardVO class_messageboardVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_FONT_STMT);
			
			pstmt.setString(1,class_messageboardVO.getCs_num());
			pstmt.setString(2, class_messageboardVO.getGd_id());
			
			pstmt.setString(3, class_messageboardVO.getMsg());
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
	public void update(Class_messageboardVO class_messageboardVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1,class_messageboardVO.getCs_num());
			pstmt.setString(2, class_messageboardVO.getGd_id());
			pstmt.setString(3, class_messageboardVO.getT_num());
			pstmt.setString(4, class_messageboardVO.getMsg());
			pstmt.setInt(6, class_messageboardVO.getMsg_num());

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
	public void delete(Integer msg_num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, msg_num);

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
	public Class_messageboardVO findByPrimaryKey(Integer msg_num) {
		Class_messageboardVO class_messageboardVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, msg_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				class_messageboardVO = new Class_messageboardVO();
				class_messageboardVO.setMsg_num(rs.getInt("msg_num"));
				class_messageboardVO.setCs_num(rs.getString("cs_num"));
				class_messageboardVO.setGd_id(rs.getString("gd_id"));
				class_messageboardVO.setT_num(rs.getString("t_num"));
				class_messageboardVO.setMsg(rs.getString("msg"));
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
		return class_messageboardVO;
	}
	
	
	
	
	@Override
	public List<Class_messageboardVO> getAll() {
		List<Class_messageboardVO> list = new ArrayList<Class_messageboardVO>();
		Class_messageboardVO class_messageboardVO = null;

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
				class_messageboardVO = new Class_messageboardVO();
				class_messageboardVO.setMsg_num(rs.getInt("msg_num"));
				class_messageboardVO.setCs_num(rs.getString("cs_num"));
				class_messageboardVO.setGd_id(rs.getString("gd_id"));
				class_messageboardVO.setT_num(rs.getString("t_num"));
				class_messageboardVO.setMsg(rs.getString("msg"));
				list.add(class_messageboardVO); // Store the row in the list
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
		Class_messageboardJDBCDAO dao = new Class_messageboardJDBCDAO();
		
		
		// 新增(successed)
//		Class_messageboardVO class_messageboardVO1 = new Class_messageboardVO();
//		class_messageboardVO1.setCs_num("C001");
//		class_messageboardVO1.setGd_id("L123456789");
//		class_messageboardVO1.setT_num("");;
//		class_messageboardVO1.setMsg("家長留言測試");
//		dao.insert(class_messageboardVO1);
		
		
		
		// 修改(successed)
//		Class_messageboardVO class_messageboardVO2 = new Class_messageboardVO();
//		
//		class_messageboardVO2.setMsg_num(1009);
//		class_messageboardVO2.setCs_num("C001");
//		class_messageboardVO2.setGd_id("L123456789");
//		class_messageboardVO2.setT_num(null);
//		class_messageboardVO2.setMsg("家長留言修改測試");
//		
//		dao.update(class_messageboardVO2);
		
		
		// 刪除(successed)
//		dao.delete(1009);
		
		
		// 查詢(successed)
//		Class_messageboardVO class_messageboardVO3 = dao.findByPrimaryKey(1001);
//		System.out.print(class_messageboardVO3.getMsg_num() + ",");
//		System.out.print(class_messageboardVO3.getCs_num() + ",");
//		if(class_messageboardVO3.getT_num() == null) {
//			
//			System.out.print(class_messageboardVO3.getGd_id() + ",");
//			
//		}else {
//			System.out.print(class_messageboardVO3.getT_num() + ",");
//		}
//		
//		System.out.println(class_messageboardVO3.getMsg+"");
		
//		System.out.println("---------------------");
		
		
		//查詢全部(successed)
//		List<Class_messageboardVO> list = dao.getAll();
//		for (Class_messageboardVO aMessageboard : list) {
//			System.out.print(aMessageboard.getMsg_num() + ",");
//			System.out.print(aMessageboard.getCs_num() + ",");
//			System.out.print(aMessageboard.getGd_id() + ",");
//			System.out.print(aMessageboard.getT_num() + ",");
//			System.out.print(aMessageboard.getMsg() + ",");
//
//			System.out.println();
//		}
		
	}
	
	
	
}
