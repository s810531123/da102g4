package com.car.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarJDBCDAO implements CarDAO_interface{

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "DA102_G4";
	private static final String PASSWORD = "123456";
	
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
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, carVO.getSt_num());
			pstmt.setString(2, carVO.getCar_month());
			
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
	public void update(CarVO carVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(3, carVO.getCar_num());
			pstmt.setString(1, carVO.getSt_num());
			pstmt.setString(2, carVO.getCar_month());
			
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
	public void delete(Integer car_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, car_num);
			
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
	public CarVO findByPrimaryKey(Integer car_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CarVO vo = null;
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
	public List<CarVO> findByStNum(String st_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CarVO> list = new ArrayList<CarVO>();
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
	public List<CarVO> getAll() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CarVO> list = new ArrayList<CarVO>();
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
		
		CarJDBCDAO dao = new CarJDBCDAO();
		
//		//新增
//		CarVO carVO1 = new CarVO();
//		carVO1.setSt_num("S001");
//		carVO1.setCar_month("2030-01");
//		dao.insert(carVO1);
//		
//		//修改
//		CarVO carVO2 = new CarVO();
//		carVO2.setCar_num(2001);
//		carVO2.setSt_num("S010");
//		carVO2.setCar_month("1970-01");
//		dao.update(carVO2);
//		
//		//刪除
//		dao.delete(2010);
//		
//		//主鍵查詢
//		CarVO carVO3 = dao.findByPrimaryKey(300);
//		System.out.print(carVO3.getCar_num() + ",");
//		System.out.print(carVO3.getSt_num() + ",");
//		System.out.print(carVO3.getCar_month()+ "\n");
//		
//		//查詢全部
//		List<CarVO> list = dao.getAll();
//		for (CarVO vo : list) {
//			System.out.print(vo.getCar_num() + ",");
//			System.out.print(vo.getSt_num() + ",");
//			System.out.print(vo.getCar_month() + "\n");
//		}
		
		//學號查詢
		List<CarVO> list2 = dao.findByStNum("S002");
		for (CarVO vo : list2) {
			System.out.print(vo.getCar_num() + ",");
			System.out.print(vo.getSt_num() + ",");
			System.out.print(vo.getCar_month() + "\n");
		}
	}

	@Override
	public List<CarVO> getStNumByMonth(String month) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CarVO> list = new ArrayList<CarVO>();
		
		try {
			Class.forName(DRIVER);			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BY_MONTH);
			
			pstmt.setString(1, month);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CarVO vo = new CarVO();
				vo.setSt_num(rs.getString("ST_NUM"));			
				list.add(vo);
			}			
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
