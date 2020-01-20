package com.car.model;

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

public class CarJNDIDAO implements CarDAO_interface{

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
	
	
	private static final String INSERT_STMT = "INSERT INTO SCHOOL_CAR VALUES(SCHOOL_CAR_SEQ.NEXTVAL, ?, ?)" ;
	private static final String UPDATE_STMT = "UPDATE SCHOOL_CAR SET ST_NUM = ?, CAR_MONTH = ? WHERE CAR_NUM = ?";
	private static final String DELETE_STMT = "DELETE FROM SCHOOL_CAR WHERE CAR_NUM = ?";
	private static final String GET_BY_PK = "SELECT * FROM SCHOOL_CAR WHERE CAR_NUM = ?";
	private static final String GET_ALL = "SELECT * FROM SCHOOL_CAR ORDER BY CAR_NUM";
	private static final String GET_BY_ST = "SELECT * FROM SCHOOL_CAR WHERE ST_NUM = ?";
	private static final String GET_BY_MONTH = "SELECT ST_NUM FROM SCHOOL_CAR WHERE CAR_MONTH = ?";
	
	@Override
	public void insert(CarVO carVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, carVO.getSt_num());
			pstmt.setString(2, carVO.getCar_month());
			
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
	public void update(CarVO carVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(3, carVO.getCar_num());
			pstmt.setString(1, carVO.getSt_num());
			pstmt.setString(2, carVO.getCar_month());
			
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
	public void delete(Integer car_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, car_num);
			
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
	public CarVO findByPrimaryKey(Integer car_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CarVO vo = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_PK);
			
			pstmt.setInt(1, car_num);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new CarVO();
				vo.setCar_num(rs.getInt(1));
				vo.setSt_num(rs.getString(2));
				vo.setCar_month(rs.getString(3));
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
	public List<CarVO> findByStNum(String st_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CarVO> list = new ArrayList<CarVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_ST);
			
			pstmt.setString(1, st_num);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CarVO vo = new CarVO();
				vo.setCar_num(rs.getInt(1));
				vo.setSt_num(rs.getString(2));
				vo.setCar_month(rs.getString(3));
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
	public List<CarVO> getAll() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CarVO> list = new ArrayList<CarVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CarVO vo = new CarVO();
				vo.setCar_num(rs.getInt(1));
				vo.setSt_num(rs.getString(2));
				vo.setCar_month(rs.getString(3));
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
	public List<CarVO> getStNumByMonth(String month) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CarVO> list = new ArrayList<CarVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MONTH);
			
			pstmt.setString(1, month);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CarVO vo = new CarVO();
				vo.setSt_num(rs.getString("ST_NUM"));			
				list.add(vo);
			}			
		
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
