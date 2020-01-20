package com.courselist.model;

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

import com.course.model.CourseDAO;
import com.course.model.CourseService;
import com.course.model.CourseVO;

public class CourseListJNDIDAO implements CourseListDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA102_G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO COURSE_LIST VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM COURSE_LIST";
	private static final String GET_ONE_STMT = "SELECT * FROM COURSE_LIST WHERE COU_NUM = ? AND ST_NUM = ?";
	private static final String GET_ALL_BY_COU_NUM = "SELECT * FROM COURSE_LIST WHERE COU_NUM = ?";
	private static final String GET_COUNUM_BY_STNUM = "SELECT * FROM COURSE_LIST WHERE ST_NUM = ?";
	private static final String GET_SIGNUP_COUNT = "SELECT COUNT(*) FROM COURSE_LIST WHERE COU_NUM = ?";
	private static final String GET_PAY_STATUS_COUNT = "SELECT COUNT(*) FROM COURSE_LIST WHERE COU_NUM = ? AND PAY_STATUS = 1";
	
	private static final String DELETE_DEPT = "DELETE FROM COURSE_LIST WHERE COU_NUM = ? AND ST_NUM = ?";

	private static final String UPDATE = "UPDATE COURSE_LIST SET PAY_STATUS=?,COU_LIST_DATE=? WHERE COU_NUM = ? AND ST_NUM = ?";
	private static final String UPDATE_STATUS = "UPDATE COURSE_LIST SET PAY_STATUS=? WHERE COU_NUM = ? AND ST_NUM = ?";

	@Override
	public void insert(CourseListVO courseListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, courseListVO.getCou_num());
			pstmt.setString(2, courseListVO.getSt_num());
			pstmt.setInt(3, courseListVO.getPay_status());
			pstmt.setDate(4, courseListVO.getCou_list_date());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(CourseListVO courseListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(3, courseListVO.getCou_num());
			pstmt.setString(4, courseListVO.getSt_num());
			pstmt.setInt(1, courseListVO.getPay_status());
			pstmt.setDate(2, courseListVO.getCou_list_date());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void updateStatus(CourseListVO courseListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);
			
			
			pstmt.setInt(1, courseListVO.getPay_status());
			pstmt.setInt(2, courseListVO.getCou_num());
			pstmt.setString(3, courseListVO.getSt_num());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer cou_num,String st_num) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

		
			pstmt = con.prepareStatement(DELETE_DEPT);
			pstmt.setInt(1, cou_num);
			pstmt.setString(2, st_num);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
		
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
	public Integer signUpCount(Integer cou_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer signUpCount = 0 ;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SIGNUP_COUNT);

			pstmt.setInt(1, cou_num);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
			signUpCount = rs.getInt("count(*)");
			}
			
			// Handle any driver errors
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
		return signUpCount;
	}

	@Override
	public Integer payStatusCount(Integer cou_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer payStatusCount = 0;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PAY_STATUS_COUNT);

			pstmt.setInt(1, cou_num);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
			payStatusCount = rs.getInt("count(*)");
			
			}
			
			// Handle any driver errors
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
		return payStatusCount;
	}
	
	@Override
	public List<CourseListVO> getCou_numBySt_num(String st_num) {
		
		List<CourseListVO> list = new ArrayList<CourseListVO>();
//		CourseListVO courseListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CourseListVO courseListVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COUNUM_BY_STNUM);
			
			pstmt.setString(1,st_num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				courseListVO = new CourseListVO();
				courseListVO.setCou_num(rs.getInt("cou_num"));
				courseListVO.setSt_num(rs.getString("st_num"));
				courseListVO.setPay_status(rs.getInt("pay_status"));
				courseListVO.setCou_list_date(rs.getDate("cou_list_date"));
				list.add(courseListVO);
				
			}
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured " + se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}				
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}	
		}
			return list;
	}
	
	
	@Override
	public List<CourseListVO> findByCourseNum(Integer cou_num) {
		
		List<CourseListVO> list = new ArrayList<CourseListVO>();
		CourseListVO courseListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_COU_NUM);
			
			pstmt.setInt(1,cou_num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				courseListVO = new CourseListVO();
				courseListVO.setCou_num(rs.getInt("cou_num"));
				courseListVO.setSt_num(rs.getString("st_num"));
				courseListVO.setPay_status(rs.getInt("pay_status"));
				courseListVO.setCou_list_date(rs.getDate("cou_list_date"));
				list.add(courseListVO);
			}
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured " + se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}				
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	

	@Override
	public CourseListVO findByPrimaryKey(Integer cou_num , String st_num) {

		CourseListVO courseListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, cou_num);
			pstmt.setString(2, st_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				courseListVO = new CourseListVO();
				courseListVO.setCou_num(rs.getInt("cou_num"));
				courseListVO.setSt_num(rs.getString("st_num"));
				courseListVO.setPay_status(rs.getInt("pay_status"));
				courseListVO.setCou_list_date(rs.getDate("cou_list_date"));

			}

			// Handle any driver errors
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
		return courseListVO;
	}

	@Override
	public List<CourseListVO> getAll() {
		List<CourseListVO> list = new ArrayList<CourseListVO>();
		CourseListVO courseListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				courseListVO = new CourseListVO();
				courseListVO.setCou_num(rs.getInt("cou_num"));
				courseListVO.setSt_num(rs.getString("st_num"));
				courseListVO.setPay_status(rs.getInt("pay_status"));
				courseListVO.setCou_list_date(rs.getDate("cou_list_date"));

				list.add(courseListVO); // Store the row in the list
			}

			// Handle any driver errors
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

//		CourseListDAO dao = new CourseListDAO();
//		
//		System.out.println(dao.signUpCount(10));
//		System.out.println(dao.payStatusCount(1001));
//
//			// 新增
//		CourseListVO courseListVO = new CourseListVO();
//		courseListVO.setCou_num(1004);
//		courseListVO.setSt_num("S002");
//		courseListVO.setPay_status(0);
//		courseListVO.setCou_list_date(java.sql.Date.valueOf("2019-08-18"));
////		
//		dao.insert(courseListVO);
//
//			// 修改
//			CourseListVO courseListVO = new CourseListVO();
//			courseListVO.setCou_num(1003);
//			courseListVO.setSt_num("S002");
//			courseListVO.setPay_status(1);
//			courseListVO.setCou_list_date(java.sql.Date.valueOf("2019-08-15"));
//			
//			
//			dao.update(courseListVO);
////
//			// 刪除
//			dao.delete(1004,"S002");
//
		// 查PK
//			CourseListVO courseListVO = dao.findByPrimaryKey(1001,"S001");
//			System.out.print(courseListVO.getCou_num() + ",");
//			System.out.print(courseListVO.getSt_num() + ",");
//			System.out.print(courseListVO.getPay_status() + ",");
//			System.out.print(courseListVO.getCou_list_date() + ",");

//		System.out.println("---------------------");

		// GetAll
//			List<CourseListVO> list = dao.getAll();
//			for (CourseListVO aCourse : list) {
//				System.out.print(aCourse.getCou_num() + ",");
//				System.out.print(aCourse.getSt_num() + ",");
//				System.out.print(aCourse.getPay_status() + ",");
//				System.out.print(aCourse.getCou_list_date() + ",");
//
//				System.out.println();
//			}
//		int i = 2;
//		CourseService couSvc = new CourseService();
//	    List<CourseVO> list = couSvc.getAll();
//
//	   
//	    CourseListService coulSvc = new CourseListService();
//	    Integer scount = list.size() + 1000;
//	    Integer pcount = list.size() + 1000;
//	    
//	    if (i == 2){
//     	   scount = list.size() - (i-1)*5 +1000; 
//     	   pcount = list.size() - (i-1)*5 +1000; 
//        }
//	    System.out.println(scount);
//	    System.out.println(pcount);

	}

	

	

	

}
