package com.student_leave_list.model;

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


public class Student_leave_listJNDIDAO implements Student_leave_listDAO_interface{

	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA102_G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT =
			"INSERT INTO student_leave_list (st_list_num,st_num,start_date,end_date,st_list_sort,st_list_note)VALUES(seq_st_leave_list.NEXTVAL,?,?,?,?,?)";
	private static final String GET_ALL_STMT =
			"SELECT st_list_num,st_num,to_char(start_date,'yyyy-mm-dd hh24:mi:ss')start_date,to_char(end_date,'yyyy-mm-dd hh24:mi:ss') end_date,st_list_sort,st_list_note FROM student_leave_list order by st_list_num";
	private static final String GET_ONE_STMT =
			"SELECT st_list_num,st_num,to_char(start_date,'yyyy-mm-dd hh24:mi:ss')start_date,to_char(end_date,'yyyy-mm-dd hh24:mi:ss') end_date,st_list_sort,st_list_note FROM student_leave_list where st_list_num = ?";
	private static final String DELETE = 
			"DELETE FROM student_leave_list where st_list_num=?";
	private static final String UPDATE =
			"UPDATE student_leave_list set st_num=?, start_date=?, end_date=?, st_list_sort=? ,st_list_note=? where st_list_num=?";
	private static final String GET_ONE_STMTST =
			"SELECT st_list_num,st_num,to_char(start_date,'yyyy-mm-dd hh24:mi:ss')start_date,to_char(end_date,'yyyy-mm-dd hh24:mi:ss')end_date,st_list_sort,st_list_note FROM student_leave_list where st_num = ?";
	
	
	@Override
	public void insert(Student_leave_listVO student_leave_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con= ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,student_leave_listVO.getSt_num());
			pstmt.setTimestamp(2, student_leave_listVO.getStart_date());
			pstmt.setTimestamp(3, student_leave_listVO.getEnd_date());
			pstmt.setInt(4, student_leave_listVO.getSt_list_sort());
			pstmt.setString(5, student_leave_listVO.getSt_list_note());
			pstmt.executeUpdate();
		
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
	public void update(Student_leave_listVO student_leave_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con= ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, student_leave_listVO.getSt_num());
			pstmt.setTimestamp(2, student_leave_listVO.getStart_date());
			pstmt.setTimestamp(3, student_leave_listVO.getEnd_date());
			pstmt.setInt(4, student_leave_listVO.getSt_list_sort());
			pstmt.setString(5, student_leave_listVO.getSt_list_note());
			pstmt.setInt(6, student_leave_listVO.getSt_list_num());

			pstmt.executeUpdate();

		
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
	public void delete(Integer st_list_num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con= ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, st_list_num);

			pstmt.executeUpdate();

			
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
	public Student_leave_listVO findByPrimaryKey(Integer st_list_num) {
		Student_leave_listVO student_leave_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con= ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, st_list_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				student_leave_listVO = new Student_leave_listVO();
				student_leave_listVO.setSt_list_num(rs.getInt("st_list_num"));
				student_leave_listVO.setSt_num(rs.getString("st_num"));
				student_leave_listVO.setStart_date(rs.getTimestamp("start_date"));
				student_leave_listVO.setEnd_date(rs.getTimestamp("end_date"));
				student_leave_listVO.setSt_list_sort(rs.getInt("st_list_sort"));
				student_leave_listVO.setSt_list_note(rs.getString("st_list_note"));
			}

			
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
		return student_leave_listVO;
	}
	
	
	@Override
	public List<Student_leave_listVO> getAll() {
		List<Student_leave_listVO> list = new ArrayList<Student_leave_listVO>();
		Student_leave_listVO student_leave_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con= ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				student_leave_listVO = new Student_leave_listVO();
				student_leave_listVO.setSt_list_num(rs.getInt("st_list_num"));
				student_leave_listVO.setSt_num(rs.getString("st_num"));
				student_leave_listVO.setStart_date(rs.getTimestamp("start_date"));
				student_leave_listVO.setEnd_date(rs.getTimestamp("end_date"));
				student_leave_listVO.setSt_list_sort(rs.getInt("st_list_sort"));
				student_leave_listVO.setSt_list_note(rs.getString("st_list_note"));
				list.add(student_leave_listVO); // Store the row in the list
			}

			
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
		Student_leave_listJNDIDAO dao = new Student_leave_listJNDIDAO();
		
//		// 新增(successed)
//				Student_leave_listVO student_leave_listVO1 = new Student_leave_listVO();
//				student_leave_listVO1.setSt_num("S001");
//				student_leave_listVO1.setStart_date(java.sql.Timestamp.valueOf("2019-01-01 09:00:00"));
//				student_leave_listVO1.setEnd_date(java.sql.Timestamp.valueOf("2019-01-01 12:00:00"));
//				student_leave_listVO1.setSt_list_sort(1);
//				student_leave_listVO1.setSt_list_note(new String("我就是要請假"));
//				dao.insert(student_leave_listVO1);
				
				
//		// 修改(successed)
//				Student_leave_listVO student_leave_listVO2 = new Student_leave_listVO();
//				student_leave_listVO2.setSt_list_num(1011);
//				student_leave_listVO2.setSt_num("S001");
//				student_leave_listVO2.setStart_date(java.sql.Timestamp.valueOf("2019-01-02 10:00:00"));
//				student_leave_listVO2.setEnd_date(java.sql.Timestamp.valueOf("2019-01-02 14:00:00"));
//				student_leave_listVO2.setSt_list_sort(2);
//				student_leave_listVO2.setSt_list_note(new String("請起來~~~~~"));
//				
//				dao.update(student_leave_listVO2);
				
				
//		// 刪除(successed)
//				dao.delete(1011);
				
				
				
//		// 查詢(successed)
//				Student_leave_listVO student_leave_listVO3 = dao.findByPrimaryKey(1001);
//				System.out.print(student_leave_listVO3.getSt_list_num() + ",");
//				System.out.print(student_leave_listVO3.getSt_num() + ",");
//				System.out.print(student_leave_listVO3.getStart_date() + ",");
//				System.out.print(student_leave_listVO3.getEnd_date() + ",");
//				System.out.print(student_leave_listVO3.getSt_list_sort() + ",");
//				System.out.print(student_leave_listVO3.getSt_list_note() + ",");
//				System.out.println("---------------------");

//		//查詢全部(successed)
//				List<Student_leave_listVO> list = dao.getAll();
//				for (Student_leave_listVO aSt_leave_list : list) {
//					System.out.print(aSt_leave_list.getSt_list_num() + ",");
//					System.out.print(aSt_leave_list.getSt_num() + ",");
//					System.out.print(aSt_leave_list.getStart_date() + ",");
//					System.out.print(aSt_leave_list.getEnd_date() + ",");
//					System.out.print(aSt_leave_list.getSt_list_sort() + ",");
//					System.out.print(aSt_leave_list.getSt_list_note() + ",");
//	
//					System.out.println();
//		}
		
			
	}



	@Override
	public List<Student_leave_listVO> findByPrimaryKeyyy(String st_num) {
		List<Student_leave_listVO> list = new ArrayList<Student_leave_listVO>();
		Student_leave_listVO student_leave_listVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con= ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMTST);
			pstmt.setString(1,st_num);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				student_leave_listVO = new Student_leave_listVO();
				student_leave_listVO.setSt_list_num(rs.getInt("st_list_num"));
				student_leave_listVO.setSt_num(rs.getString("st_num"));
				student_leave_listVO.setStart_date(rs.getTimestamp("start_date"));
				student_leave_listVO.setEnd_date(rs.getTimestamp("end_date"));
				student_leave_listVO.setSt_list_sort(rs.getInt("st_list_sort"));
				student_leave_listVO.setSt_list_note(rs.getString("st_list_note"));
				list.add(student_leave_listVO); // Store the row in the list
			}

			
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
}
