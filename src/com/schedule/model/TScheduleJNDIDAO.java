package com.schedule.model;

import java.sql.Connection;
import java.sql.Date;
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

import com.onduty.model.OnDutyVO;

public class TScheduleJNDIDAO implements TScheduleDAO_interface{

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
			private static DataSource ds = null;
			static {
				try {
					Context ctx = new InitialContext();
					ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA102_G4");
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
	
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, tScheduleVO.getT_num());
			pstmt.setInt(2, tScheduleVO.getOn_duty_num());
			pstmt.setDate(3, tScheduleVO.getTs_date());
			
			pstmt.executeUpdate();
			System.out.println("新增成功");
			
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(4, tScheduleVO.getTs_num());
			pstmt.setString(1, tScheduleVO.getT_num());
			pstmt.setInt(2, tScheduleVO.getOn_duty_num());
			pstmt.setDate(3, tScheduleVO.getTs_date());		
			
			pstmt.executeUpdate();
			System.out.println("修改成功");
			
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, ts_num);
			
			pstmt.executeUpdate();
			System.out.println("刪除成功");
			
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
	public List<TScheduleVO> findByT_num(String t_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TScheduleVO> list = new ArrayList<TScheduleVO>();
		
		try {
			con = ds.getConnection();
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
