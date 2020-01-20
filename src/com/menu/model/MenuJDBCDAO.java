package com.menu.model;

import java.util.*;

import java.sql.*;

public class MenuJDBCDAO implements MenuDAO_interface {

	private final static String driver = "oracle.jdbc.driver.OracleDriver";

	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA102_G4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO MENU(mu_Num,mu_Name,mu_Time,mu_Date) VALUES (MU_NUM.nextval, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT mu_Num,mu_Name,mu_Time,to_char(mu_Date,'yyyy-mm-dd')mu_Date FROM MENU order by mu_Date desc";
	private static final String GET_ONE_STMT = "SELECT mu_Num,mu_Name,mu_Time,to_char(mu_Date,'yyyy-mm-dd')mu_Date FROM MENU where mu_Num = ?";
	private static final String DELETE = "DELETE FROM MENU where mu_Num = ?";
	private static final String UPDATE = "UPDATE MENU set mu_Name=?, mu_Time=?, mu_Date=? where mu_Num = ?";
	private static final String GET_BY_MU_DATE = "SELECT * FROM MENU WHERE mu_Date=to_date(?,'yyyy-mm-dd')";

	@Override
	public void insert(MenuVO menuVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, menuVO.getMu_Name());
			pstmt.setString(2, menuVO.getMu_Time());
			pstmt.setDate(3, menuVO.getMu_Date());

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
	public void update(MenuVO menuVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, menuVO.getMu_Name());
			pstmt.setString(2, menuVO.getMu_Time());
			pstmt.setDate(3, menuVO.getMu_Date());
			pstmt.setInt(4, menuVO.getMu_Num());

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
	public void delete(Integer mu_Num) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mu_Num);

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
	public MenuVO findByPrimaryKey(Integer mu_Num) {

		MenuVO menuVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mu_Num);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				menuVO = new MenuVO();
				menuVO.setMu_Num(rs.getInt("mu_Num"));
				menuVO.setMu_Name(rs.getString("mu_Name"));
				menuVO.setMu_Time(rs.getString("mu_Time"));
				menuVO.setMu_Date(rs.getDate("mu_Date"));
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
		return menuVO;
	}

	@Override
	public List<MenuVO> getAll() {
		List<MenuVO> list = new ArrayList<MenuVO>();
		MenuVO menuVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				menuVO = new MenuVO();
				menuVO.setMu_Num(rs.getInt("mu_Num"));
				menuVO.setMu_Name(rs.getString("mu_Name"));
				menuVO.setMu_Time(rs.getString("mu_Time"));
				menuVO.setMu_Date(rs.getDate("mu_Date"));
				list.add(menuVO);
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
	public List<MenuVO> getMenuBymu_Date(String datestr) {
		List<MenuVO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_MU_DATE);
			pstmt.setString(1, datestr);

			rs = pstmt.executeQuery();
			list = new ArrayList<MenuVO>();

			while (rs.next()) {

				MenuVO menuVO = new MenuVO();
				menuVO.setMu_Num(rs.getInt("mu_Num"));
				menuVO.setMu_Name(rs.getString("mu_Name"));
				menuVO.setMu_Time(rs.getString("mu_Time"));
				menuVO.setMu_Date(rs.getDate("mu_Date"));
				list.add(menuVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
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

		MenuJDBCDAO dao = new MenuJDBCDAO();

		// 新增
		MenuVO menuVO1 = new MenuVO();
		menuVO1.setMu_Date(java.sql.Date.valueOf("2019-09-04"));
		menuVO1.setMu_Time("早餐");
		menuVO1.setMu_Name("大麥克雞塊");
		dao.insert(menuVO1);
		System.out.println("新增成功!");

		// 修改
		MenuVO menuVO2 = new MenuVO();

		menuVO2.setMu_Date(java.sql.Date.valueOf("2019-09-03"));
		menuVO2.setMu_Num(1036);
		menuVO2.setMu_Time("點心");
		menuVO2.setMu_Name("黑糖奶酪");
		dao.update(menuVO2);
		System.out.println("修改成功!");

		// 刪除
		dao.delete(1036);
		System.out.print("刪除成功");

		// 依日期查詢
		String datestr = "2019-09-01";
		List<MenuVO> list = dao.getMenuBymu_Date(datestr);
		for (MenuVO aMenu : list) {
			System.out.print(aMenu.getMu_Num() + ",");
			System.out.print(aMenu.getMu_Date() + ",");
			System.out.print(aMenu.getMu_Time() + ",");
			System.out.print(aMenu.getMu_Name());
			System.out.println();
		}

		// 查詢全部
		List<MenuVO> list2 = dao.getAll();
		for (MenuVO aMenu2 : list2) {
			System.out.print(aMenu2.getMu_Num() + ",");
			System.out.print(aMenu2.getMu_Date() + ",");
			System.out.print(aMenu2.getMu_Time() + ",");
			System.out.print(aMenu2.getMu_Name());
			System.out.println();

			System.out.println();
		}
	}
}
