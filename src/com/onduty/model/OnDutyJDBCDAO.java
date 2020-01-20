package com.onduty.model;

import java.sql.*;
import java.util.*;

public class OnDutyJDBCDAO implements OnDutyDAO_interface{
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "DA102_G4";
	private static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO ON_DUTY VALUES(ON_DUTY_SEQ.NEXTVAL, ?, ?)" ;
	private static final String UPDATE_STMT = "UPDATE ON_DUTY SET ON_DUTY_CS = ?, ON_DUTY_TIME = ? WHERE ON_DUTY_NUM = ?";
	private static final String DELETE_STMT = "DELETE FROM ON_DUTY WHERE ON_DUTY_NUM = ?";
	private static final String GET_BY_PK = "SELECT * FROM ON_DUTY WHERE ON_DUTY_NUM = ?";
	private static final String GET_ALL = "SELECT * FROM ON_DUTY ORDER BY ON_DUTY_NUM";
	
	@Override
	public void insert(OnDutyVO onDutyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, onDutyVO.getOn_duty_cs());
			pstmt.setString(2, onDutyVO.getOn_duty_time());
			
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
	public void update(OnDutyVO onDutyVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(3, onDutyVO.getOn_duty_num());
			pstmt.setString(1, onDutyVO.getOn_duty_cs());
			pstmt.setString(2, onDutyVO.getOn_duty_time());
			
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
	public void delete(Integer on_duty_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, on_duty_num);
			
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
	public OnDutyVO findByPrimaryKey(Integer on_duty_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OnDutyVO vo = null;
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BY_PK);
			
			pstmt.setInt(1, on_duty_num);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new OnDutyVO();
				vo.setOn_duty_num(rs.getInt(1));
				vo.setOn_duty_cs(rs.getString(2));
				vo.setOn_duty_time(rs.getString(3));
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
	public List<OnDutyVO> getAll() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OnDutyVO> list = new ArrayList<OnDutyVO>();
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OnDutyVO vo = new OnDutyVO();
				vo.setOn_duty_num(rs.getInt(1));
				vo.setOn_duty_cs(rs.getString(2));
				vo.setOn_duty_time(rs.getString(3));
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
		
		OnDutyJDBCDAO dao = new OnDutyJDBCDAO();
		
		//新增
		OnDutyVO onDutyVO1 = new OnDutyVO();
		onDutyVO1.setOn_duty_cs("白夜");
		onDutyVO1.setOn_duty_time("23:00-06:00");
		dao.insert(onDutyVO1);
		
		//修改
		OnDutyVO onDutyVO2 = new OnDutyVO();
		onDutyVO2.setOn_duty_num(2001);
		onDutyVO2.setOn_duty_cs("假日");
		onDutyVO2.setOn_duty_time("12:00-24:00");
		dao.update(onDutyVO2);
		
		//刪除
		dao.delete(2005);
		
		//主鍵查詢
		OnDutyVO onDutyVO3 = dao.findByPrimaryKey(2002);
		System.out.print(onDutyVO3.getOn_duty_num() + ",");
		System.out.print(onDutyVO3.getOn_duty_cs() + ",");
		System.out.print(onDutyVO3.getOn_duty_time() + "\n");
		
		//查詢全部
		List<OnDutyVO> list = dao.getAll();
		for (OnDutyVO vo : list) {
			System.out.print(vo.getOn_duty_num() + ",");
			System.out.print(vo.getOn_duty_cs() + ",");
			System.out.print(vo.getOn_duty_time() + "\n");
		}
		
	}
}
