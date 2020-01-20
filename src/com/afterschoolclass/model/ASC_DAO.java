package com.afterschoolclass.model;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ASC_DAO implements ASCDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA102_G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO AFTER_SCHOOL_CLASS (NURSERY_NO,ST_NUM,ST_SUM,LEVELTIME) VALUES (NURSERY_NO_SEQ.NEXTVAL,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT NURSERY_NO,ST_NUM,ST_SUM,LEVELTIME FROM AFTER_SCHOOL_CLASS order by NURSERY_NO";
	private static final String GET_ONE_STMT = "SELECT NURSERY_NO,ST_NUM,ST_SUM,LEVELTIME FROM AFTER_SCHOOL_CLASS where st_num = ?";
	private static final String DELETE = "DELETE FROM AFTER_SCHOOL_CLASS where NURSERY_NO = ?";
	private static final String UPDATE = "UPDATE AFTER_SCHOOL_CLASS set ST_NUM=?,ST_SUM=?,LEVELTIME=? where NURSERY_NO = ?";
	private static final String INSERT ="SELECT ST_NUM,ALT_LEA,ALT_LEAT FROM ARRIVE_LEAVE_TIME WHERE TO_CHAR (ALT_LEA, 'YYYY/MM/DD') = (SELECT TO_CHAR(sysdate-1, 'YYYY/MM/DD') FROM dual)";
	@Override
	public void insert(ASC_VO ascVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, ascVO.getSt_num());
			pstmt.setInt(2, ascVO.getSt_sum());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
			Date date = null;
			
			try {
				date = sdf.parse(ascVO.getLeveltime());
			} catch (ParseException e) {			
				e.printStackTrace();
			}	
			
			java.sql.Timestamp timestamp = new	java.sql.Timestamp(date.getTime());
		
			
			pstmt.setTimestamp(3, timestamp);
			pstmt.executeUpdate();

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
	public void update(ASC_VO ascVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, ascVO.getSt_num());
			pstmt.setInt(2, ascVO.getSt_sum());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
			Date date = null;
			try {
				date = sdf.parse(ascVO.getLeveltime());
			} catch (ParseException e) {			
				e.printStackTrace();
			}			
			pstmt.setDate(3, (java.sql.Date) date);		
			pstmt.setInt(4, ascVO.getNursery_no());
			
			pstmt.executeUpdate();

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
	public void delete(Integer nursery_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, nursery_no);

			pstmt.executeUpdate();

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
	public  List<ASC_VO> findByPrimaryKey(String st_num) {
		List<ASC_VO> list = new ArrayList<ASC_VO>();
		ASC_VO ascVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, st_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ascVO = new ASC_VO();
				ascVO.setNursery_no(rs.getInt("NURSERY_NO"));
				ascVO.setSt_num(rs.getString("ST_NUM"));
				ascVO.setSt_sum(rs.getInt("ST_SUM"));
				ascVO.setLeveltime(rs.getDate("LEVELTIME"));	
				ascVO.setTime(rs.getTimestamp("LEVELTIME"));
				list.add(ascVO);
			}

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
	public List<ASC_VO> getAll() {
		List<ASC_VO> list = new ArrayList<ASC_VO>();
		ASC_VO ascVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ascVO = new ASC_VO();
				ascVO.setNursery_no(rs.getInt("NURSERY_NO"));
				ascVO.setSt_num(rs.getString("ST_NUM"));
				ascVO.setSt_sum(rs.getInt("ST_SUM"));
				ascVO.setLeveltime(rs.getDate("LEVELTIME"));
				ascVO.setTime(rs.getTimestamp("LEVELTIME"));
				list.add(ascVO);
			}
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
	public List<ASC_VO> insert() {
		List<ASC_VO> list = new ArrayList<ASC_VO>();
		ASC_VO ascVO = null;		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		try {			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);			
			rs = pstmt.executeQuery();		

			while (rs.next()) {	
				
				if(Math.abs((rs.getTimestamp("ALT_LEA").getTime()-rs.getTimestamp("ALT_LEAT").getTime()))>1800000) {
					ascVO = new ASC_VO();					
					ascVO.setSt_num(rs.getString("ST_NUM"));
					ascVO.setSt_sum(500);					
					java.util.Date utilDate = new java.util.Date();
					utilDate.setTime(rs.getTimestamp("ALT_LEA").getTime());
					ascVO.setLeveltime(utilDate);
					ascVO.setTime(rs.getTimestamp("ALT_LEA"));
					list.add(ascVO);
				}
				else if(Math.abs((rs.getTimestamp("ALT_LEA").getTime()-rs.getTimestamp("ALT_LEAT").getTime()))>3600000) {
					ascVO = new ASC_VO();					
					ascVO.setSt_num(rs.getString("ST_NUM"));
					ascVO.setSt_sum(1000);					
					java.util.Date utilDate = new java.util.Date();
					utilDate.setTime(rs.getTimestamp("ALT_LEA").getTime());
					ascVO.setLeveltime(utilDate);
					ascVO.setTime(rs.getTimestamp("ALT_LEA"));
					list.add(ascVO);
				}
				else if(Math.abs((rs.getTimestamp("ALT_LEA").getTime()-rs.getTimestamp("ALT_LEAT").getTime()))>7200000) {
					ascVO = new ASC_VO();					
					ascVO.setSt_num(rs.getString("ST_NUM"));
					ascVO.setSt_sum(1500);					
					java.util.Date utilDate = new java.util.Date();
					utilDate.setTime(rs.getTimestamp("ALT_LEA").getTime());
					ascVO.setLeveltime(utilDate);
					ascVO.setTime(rs.getTimestamp("ALT_LEA"));
					list.add(ascVO);
				}
				else if(Math.abs((rs.getTimestamp("ALT_LEA").getTime()-rs.getTimestamp("ALT_LEAT").getTime()))>9000000) {
					ascVO = new ASC_VO();					
					ascVO.setSt_num(rs.getString("ST_NUM"));
					ascVO.setSt_sum(2000);					
					java.util.Date utilDate = new java.util.Date();
					utilDate.setTime(rs.getTimestamp("ALT_LEA").getTime());
					ascVO.setLeveltime(utilDate);
					ascVO.setTime(rs.getTimestamp("ALT_LEA"));
					list.add(ascVO);
				}
				else if(Math.abs((rs.getTimestamp("ALT_LEA").getTime()-rs.getTimestamp("ALT_LEAT").getTime()))>10800000) {
					ascVO = new ASC_VO();					
					ascVO.setSt_num(rs.getString("ST_NUM"));
					ascVO.setSt_sum(2500);					
					java.util.Date utilDate = new java.util.Date();
					utilDate.setTime(rs.getTimestamp("ALT_LEA").getTime());
					ascVO.setLeveltime(utilDate);	
					ascVO.setTime(rs.getTimestamp("ALT_LEA"));
					list.add(ascVO);
				}
				else if(Math.abs((rs.getTimestamp("ALT_LEA").getTime()-rs.getTimestamp("ALT_LEAT").getTime()))>12600000) {
					ascVO = new ASC_VO();					
					ascVO.setSt_num(rs.getString("ST_NUM"));
					ascVO.setSt_sum(500);					
					java.util.Date utilDate = new java.util.Date();
					utilDate.setTime(rs.getTimestamp("ALT_LEA").getTime());
					ascVO.setLeveltime(utilDate);
					ascVO.setTime(rs.getTimestamp("ALT_LEA"));
					list.add(ascVO);
				}
			}

		
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

}
