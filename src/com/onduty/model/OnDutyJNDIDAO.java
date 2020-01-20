package com.onduty.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OnDutyJNDIDAO implements OnDutyDAO_interface{
	
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, onDutyVO.getOn_duty_cs());
			pstmt.setString(2, onDutyVO.getOn_duty_time());
			
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
	public void update(OnDutyVO onDutyVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(3, onDutyVO.getOn_duty_num());
			pstmt.setString(1, onDutyVO.getOn_duty_cs());
			pstmt.setString(2, onDutyVO.getOn_duty_time());
			
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
	public void delete(Integer on_duty_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, on_duty_num);
			
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
	public OnDutyVO findByPrimaryKey(Integer on_duty_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OnDutyVO vo = null;
		
		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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
