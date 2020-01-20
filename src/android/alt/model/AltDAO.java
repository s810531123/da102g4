package android.alt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class AltDAO implements AltDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:49161:XE";
	String userid = "DA102_G4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO ARRIVE_LEAVE_TIME(alt_No,st_Num,alt_Arr,alt_Lea,alt_Leat) VALUES (SEQ_ARRIVE_LEAVE_TIME.nextval, ?, ?, ?, ?) ";
	private static final String UPDATE = "UPDATE ARRIVE_LEAVE_TIME set st_Num=?, alt_Arr=?, alt_Lea=?, alt_Leat=? where alt_No = ?order by alt_lea DESC";
	private static final String GET_BY_ST_NUM = "SELECT * FROM ARRIVE_LEAVE_TIME WHERE st_Num = ? order by alt_lea DESC";
	private static final String GET_ALL_STMT = "SELECT * FROM ARRIVE_LEAVE_TIME order by alt_arr DESC";
	private static final String GET_BY_ST_NUM_TODAY = "SELECT * FROM ARRIVE_LEAVE_TIME WHERE ST_NUM = ? AND ALT_ARR >= TO_DATE(?,'YYYY-MM-DD') AND ALT_ARR <= TO_DATE(?,'YYYY-MM-DD')";
	private static final String UPDATE_ARR_LEA = "UPDATE ARRIVE_LEAVE_TIME set ALT_LEA= CURRENT_TIMESTAMP where st_num = ? and ALT_ARR >= TO_DATE(?,'YYYY-MM-DD') AND ALT_ARR <= TO_DATE(?,'YYYY-MM-DD')";
	private static final String INSERT_ASC = "INSERT INTO AFTER_SCHOOL_CLASS(NURSERY_NO,ST_SUM,ST_NUM,LEVELTIME)VALUES(NURSERY_NO_SEQ.NEXTVAL,?,?,?)";
	
	public AltDAO() {
		super();
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(Alt alt) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isInserted = false;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, alt.getSt_num());
			pstmt.setTimestamp(2, alt.getAlt_arr());
			pstmt.setTimestamp(3, alt.getAlt_lea());
			pstmt.setTimestamp(4, alt.getAlt_leat());

			int count = pstmt.executeUpdate();
			isInserted = count > 0 ? true : false;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Couldn't load database driver. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return isInserted;

	}
	
	
	@Override
	public boolean updateARR_LEA(String st_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isUpdated = false;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_ARR_LEA);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(new java.sql.Date(System.currentTimeMillis()));
			String tomorrow = sdf.format(new java.sql.Date(System.currentTimeMillis() + (86400*1000)));
		
			
			pstmt.setString(1, st_num);
			pstmt.setString(2, today);
			pstmt.setString(3, tomorrow);
			

			int count = pstmt.executeUpdate();
			isUpdated = count > 0 ? true : false;

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Couldn't load database driver. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return isUpdated;

	}
	

	@Override
	public boolean update(Alt alt) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isUpdated = false;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, alt.getSt_num());
			pstmt.setTimestamp(2, alt.getAlt_arr());
			pstmt.setTimestamp(3, alt.getAlt_lea());
			pstmt.setTimestamp(4, alt.getAlt_leat());
			pstmt.setInt(5, alt.getAlt_no());

			int count = pstmt.executeUpdate();
			isUpdated = count > 0 ? true : false;

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Couldn't load database driver. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return isUpdated;

	}

	@Override
	public List<Alt> getAltBySt_num(String st_num) {
		List<Alt> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_ST_NUM);
			pstmt.setString(1, st_num);

			rs = pstmt.executeQuery();
			list = new ArrayList<Alt>();
			
			while (rs.next()) {
				Alt alt = new Alt();
				alt.setAlt_no(rs.getInt("alt_No"));
				alt.setSt_num(rs.getString("st_Num"));
				alt.setAlt_arr(rs.getTimestamp("alt_Arr"));
				alt.setAlt_lea(rs.getTimestamp("alt_Lea"));
				alt.setAlt_leat(rs.getTimestamp("alt_Leat"));
				list.add(alt);
			}
			
		}catch (ClassNotFoundException e) {
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

	@Override
	public List<Alt> getAll() {
		List<Alt> list = new ArrayList<Alt>();
		Alt alt = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				alt = new Alt();
				alt.setAlt_no(rs.getInt("alt_No"));
				alt.setSt_num(rs.getString("st_Num"));
				alt.setAlt_arr(rs.getTimestamp("alt_Arr"));
				alt.setAlt_lea(rs.getTimestamp("alt_Lea"));
				alt.setAlt_leat(rs.getTimestamp("alt_Leat"));
				list.add(alt);
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
	public List<Alt> getAltBySt_num_today(String st_num) {
		List<Alt> list = new ArrayList<Alt>();
		Alt alt = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_ST_NUM_TODAY);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(new java.sql.Date(System.currentTimeMillis()));
			String tomorrow = sdf.format(new java.sql.Date(System.currentTimeMillis() + (86400*1000)));
		
			
			pstmt.setString(1, st_num);
			pstmt.setString(2, today);
			pstmt.setString(3, tomorrow);
			

			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				alt = new Alt();
				alt.setAlt_no(rs.getInt("alt_No"));
				alt.setSt_num(rs.getString("st_Num"));
				alt.setAlt_arr(rs.getTimestamp("alt_Arr"));
				alt.setAlt_lea(rs.getTimestamp("alt_Lea"));
				alt.setAlt_leat(rs.getTimestamp("alt_Leat"));
				list.add(alt);
			}
			

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Couldn't load database driver. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;

		
	}

	public static void main(String[] args) {

		AltDAO dao = new AltDAO();
		
		List<Alt> list = dao.getAltBySt_num_today("S002");
		for (Alt aAlt : list) {
			System.out.print(aAlt.getAlt_no() + ",");
			System.out.print(aAlt.getSt_num() + ",");
			System.out.print(aAlt.getAlt_arr() + ",");
			System.out.print(aAlt.getAlt_lea() + ",");
			System.out.print(aAlt.getAlt_leat());
			System.out.println();
		}
		
//		Boolean b = dao.updateARR_LEA("S001");
//		System.out.println(b);
//		
	}

	@Override
	public void insertASC(Alt alt) {
		long sum  = Math.abs(alt.getAlt_leat().getTime() - alt.getAlt_lea().getTime());
		int i = 0 ;
		if(sum > 1000*60*30) 
			i=1;
		else if(sum > 2*1000*60*30)
			i=2;
		else i=3;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_ASC);
			
			
			pstmt.setString(2, alt.getSt_num());
		 
			pstmt.setTimestamp(3,  alt.getAlt_lea());			
			
			pstmt.setLong(1, 500*i);
			

			
		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Couldn't load database driver. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}		

	}
	

}
