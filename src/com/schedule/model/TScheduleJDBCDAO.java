package com.schedule.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.onduty.model.OnDutyVO;

public class TScheduleJDBCDAO implements TScheduleDAO_interface{

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "DA102_G4";
	private static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO TEACHER_SCHEDULE VALUES(TEACHER_SCHEDULE_SEQ.NEXTVAL, ?, ?, ?)" ;
	private static final String UPDATE_STMT = "UPDATE TEACHER_SCHEDULE SET T_NUM = ?, ON_DUTY_NUM = ?, TS_DATE = ? WHERE TS_NUM = ?";
	private static final String DELETE_STMT = "DELETE FROM TEACHER_SCHEDULE WHERE TS_NUM = ?";
	private static final String GET_BY_PK = "SELECT * FROM TEACHER_SCHEDULE WHERE TS_NUM = ?";
	private static final String GET_ALL = "SELECT * FROM TEACHER_SCHEDULE ORDER BY TS_NUM";
	private static final String GET_BY_MONTH = "SELECT * FROM teacher_schedule WHERE to_char(ts_date,'MM') = ? AND to_char(ts_date,'YYYY') = ? order by ts_date";
	private static final String GET_BY_DATE = "SELECT * FROM teacher_schedule WHERE to_char(ts_date,'YYYY-MM-DD') = ? order by ON_DUTY_NUM";
	private static final String GET_BY_MONTH_ONE_TEACHER = "SELECT * FROM teacher_schedule WHERE to_char(ts_date,'MM') = ? AND to_char(ts_date,'YYYY') = ? and t_num = ? order by ts_date";
	private static final String GET_BY_T_NUM = "SELECT * FROM teacher_schedule WHERE T_NUM = ?";
	
	
	@Override
	public void insert(TScheduleVO tScheduleVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, tScheduleVO.getT_num());
			pstmt.setInt(2, tScheduleVO.getOn_duty_num());
			pstmt.setDate(3, tScheduleVO.getTs_date());
			
			pstmt.executeUpdate();
			System.out.println("新增成功");
			
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. "
					+ ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			
			if (con!= null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(TScheduleVO tScheduleVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(4, tScheduleVO.getTs_num());
			pstmt.setString(1, tScheduleVO.getT_num());
			pstmt.setInt(2, tScheduleVO.getOn_duty_num());
			pstmt.setDate(3, tScheduleVO.getTs_date());		
			
			pstmt.executeUpdate();
			System.out.println("修改成功");
			
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. "
					+ ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			
			if (con!= null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(Integer ts_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, ts_num);
			
			pstmt.executeUpdate();
			System.out.println("刪除成功");
			
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. "
					+ ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			
			if (con!= null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public TScheduleVO findByPrimaryKey(Integer ts_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TScheduleVO vo = null;
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BY_PK);
			
			pstmt.setInt(1, ts_num);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new TScheduleVO();
				vo.setTs_num(rs.getInt(1));
				vo.setT_num(rs.getString(2));
				vo.setOn_duty_num(rs.getInt(3));
				vo.setTs_date(rs.getDate(4));
				
			}
			System.out.println("查詢成功");
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. "
					+ ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			
			if (con!= null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return vo;
	}

	@Override
	public List<TScheduleVO> getAll() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TScheduleVO> list = new ArrayList<TScheduleVO>();
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TScheduleVO vo = new TScheduleVO();
				vo.setTs_num(rs.getInt(1));
				vo.setT_num(rs.getString(2));
				vo.setOn_duty_num(rs.getInt(3));
				vo.setTs_date(rs.getDate(4));
				list.add(vo);
			}
			System.out.println("查詢成功");
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. "
					+ ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			
			if (con!= null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}

	
	@Override
	public List<TScheduleVO> findByMonth(Integer year, Integer month) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TScheduleVO> list = new ArrayList<TScheduleVO>();
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BY_MONTH);
			
			pstmt.setInt(1, month);
			pstmt.setInt(2, year);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TScheduleVO vo = new TScheduleVO();
				vo.setTs_num(rs.getInt(1));
				vo.setT_num(rs.getString(2));
				vo.setOn_duty_num(rs.getInt(3));
				vo.setTs_date(rs.getDate(4));
				list.add(vo);
			}
			System.out.println("查詢成功");
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. "
					+ ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			
			if (con!= null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
		
		return list;
	}

	@Override
	public List<TScheduleVO> findByDate(String date) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TScheduleVO> list = new ArrayList<TScheduleVO>();
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BY_DATE);
			
			pstmt.setString(1, date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TScheduleVO vo = new TScheduleVO();
				vo.setTs_num(rs.getInt(1));
				vo.setT_num(rs.getString(2));
				vo.setOn_duty_num(rs.getInt(3));
				vo.setTs_date(rs.getDate(4));
				list.add(vo);
			}
			System.out.println("查詢成功");
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. "
					+ ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			
			if (con!= null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}

	@Override
	public List<TScheduleVO> findByMonthFromOneTeacher(Integer year, Integer month, String t_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TScheduleVO> list = new ArrayList<TScheduleVO>();
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BY_MONTH_ONE_TEACHER);
			
			pstmt.setInt(1, month);
			pstmt.setInt(2, year);
			pstmt.setString(3, t_num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TScheduleVO vo = new TScheduleVO();
				vo.setTs_num(rs.getInt(1));
				vo.setT_num(rs.getString(2));
				vo.setOn_duty_num(rs.getInt(3));
				vo.setTs_date(rs.getDate(4));
				list.add(vo);
			}
			System.out.println("查詢成功");
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. "
					+ ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			
			if (con!= null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
		
		return list;
	}

	public static void main(String[] args) {
		TScheduleJDBCDAO dao = new TScheduleJDBCDAO();
		
		//新增
		TScheduleVO tScheduleVO1 = new TScheduleVO();
		tScheduleVO1.setT_num("T009");
		tScheduleVO1.setOn_duty_num(2004);
		tScheduleVO1.setTs_date(java.sql.Date.valueOf("2019-09-28"));
		dao.insert(tScheduleVO1);
		
		//修改
//		TScheduleVO tScheduleVO2 = new TScheduleVO();
//		tScheduleVO2.setTs_num(1001);
//		tScheduleVO2.setT_num("T001");
//		tScheduleVO2.setOn_duty_num(2002);
//		tScheduleVO2.setTs_date(java.sql.Date.valueOf("2030-01-01"));
//		dao.update(tScheduleVO2);
//		
//		//刪除
//		dao.delete(1001);
//		
//		//主鍵查詢
//		TScheduleVO tScheduleVO3 = dao.findByPrimaryKey(1010);
//		System.out.print(tScheduleVO3.getTs_num() + ",");
//		System.out.print(tScheduleVO3.getT_num() + ",");
//		System.out.print(tScheduleVO3.getOn_duty_num() + ",");
//		System.out.print(tScheduleVO3.getTs_date() + "\n");
//		
//		//查詢全部
//		List<TScheduleVO> list = dao.getAll();
//		for(TScheduleVO vo : list) {
//			System.out.print(vo.getTs_num() + ",");
//			System.out.print(vo.getT_num() + ",");
//			System.out.print(vo.getOn_duty_num() + ",");
//			System.out.print(vo.getTs_date() + "\n");
//		}
//		
//		//月份查詢
//		List<TScheduleVO> list2 = dao.findByMonth(2019, 8);
//		for(TScheduleVO vo : list2) {
//			System.out.print(vo.getTs_num() + ",");
//			System.out.print(vo.getT_num() + ",");
//			System.out.print(vo.getOn_duty_num() + ",");
//			System.out.print(vo.getTs_date() + "\n");
//		}
		
		//日期查詢
//		List<TScheduleVO> list3 = dao.findByDate("2019-09-30");
//		for(TScheduleVO vo : list3) {
//			System.out.print(vo.getTs_num() + ",");
//			System.out.print(vo.getT_num() + ",");
//			System.out.print(vo.getOn_duty_num() + ",");
//			System.out.print(vo.getTs_date() + "\n");
//		}
		
		
		//月份查詢 從一個老師
		List<TScheduleVO> list4 = dao.findByMonthFromOneTeacher(2019, 9, "T002");
		for(TScheduleVO vo : list4) {
			System.out.print(vo.getTs_num() + ",");
			System.out.print(vo.getT_num() + ",");
			System.out.print(vo.getOn_duty_num() + ",");
			System.out.print(vo.getTs_date() + "\n");
		}
	}

	@Override
	public List<TScheduleVO> findByT_num(String t_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TScheduleVO> list = new ArrayList<TScheduleVO>();
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BY_T_NUM);
			
			
			pstmt.setString(1, t_num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TScheduleVO vo = new TScheduleVO();
				vo.setTs_num(rs.getInt(1));
				vo.setT_num(rs.getString(2));
				vo.setOn_duty_num(rs.getInt(3));
				vo.setTs_date(rs.getDate(4));
				list.add(vo);
			}
			System.out.println("查詢成功");
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. "
					+ ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			
			if (con!= null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
		
		return list;
	}
}
