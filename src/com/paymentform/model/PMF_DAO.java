package com.paymentform.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.afterschoolclass.model.ASC_VO;
import com.paymentlist.model.PML_VO;
import com.paymentproject.model.PMP_VO;

public class PMF_DAO implements PMFDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA102_G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO PAYMENT_FORM (PF_NUM,ST_NUM,PML_TRAIL,PF_STATUS,MONTH,PF_TIME) VALUES (PF_NUM_seq.NEXTVAL,?,?,?,?,?)";
	private static final String INSERT_STMT2 = "INSERT INTO PAYMENT_FORM (PF_NUM,ST_NUM,PML_TRAIL,PF_STATUS,MONTH,PF_TIME) VALUES (PF_NUM_seq.CURRVAL,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT PF_NUM,ST_NUM,PML_TRAIL,PF_STATUS,MONTH,PF_TIME FROM PAYMENT_FORM order by PF_NUM";
	private static final String GET_ONE_STMT = "SELECT PF_NUM,ST_NUM,PML_TRAIL,PF_STATUS,MONTH,PF_TIME FROM PAYMENT_FORM where st_num = ?";
	private static final String DELETE = "DELETE FROM PAYMENT_FORM where PF_NUM = ?";
	private static final String UPDATE = "UPDATE PAYMENT_FORM set ST_NUM=?,PML_TRAIL=?,PF_STATUS=?,MONTH=?,PF_TIME=? where PF_NUM = ?";
	private static final String currseq1 = "SELECT pf_num_seq.nextval  FROM DUAL";
	private static final String currseq2 = "SELECT pf_num_seq.currval  FROM DUAL";
	private static final String GET_ALL_PMP = "SELECT PAYMENT_FORM_LIST.PML_NUM,PML_ITEM,PML_MONEY,PF_STATUS FROM PAYMENT_FORM_LIST	JOIN PAYMENT_PROJECT ON PAYMENT_FORM_LIST.PML_NUM "
			+ "= PAYMENT_PROJECT.PML_NUM WHERE PAYMENT_FORM_LIST.PF_NUM=? AND PF_STATUS=1";
	private static final String GET_ST_NUM_MONTH = "select st_num,month from payment_form";
	private static final String GET_PF_NUM_ByST_NUM_MONTH="select pf_num from payment_form where st_num=? and month=?"; 
	private static final String ADD_PML_TRAIL="UPDATE PAYMENT_FORM SET PML_TRAIL = (SELECT PML_TRAIL FROM PAYMENT_FORM WHERE PF_NUM = ?) + ?  WHERE PF_NUM = ?";
	private static final String UPDATENUM = "UPDATE PAYMENT_FORM SET PF_STATUS = 1,PF_TIME=?  WHERE PF_NUM = ?";
	private static final String GETONEPMF = "SELECT PF_NUM,ST_NUM,PML_TRAIL,PF_STATUS,MONTH,PF_TIME FROM PAYMENT_FORM where PF_NUM = ?";
	@Override
	public Integer getcurrseq1() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int seq = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(currseq1);
			rs = pstmt.executeQuery();
			rs.next();
			seq = rs.getInt("nextval");
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
		return seq;
	}

	@Override
	public Integer getcurrseq2() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int seq = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(currseq2);
			rs = pstmt.executeQuery();
			rs.next();
			seq = rs.getInt("nextval");
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
		return seq;
	}

	@Override
	public void insert(PMF_VO pmfVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			int pf_status = 0;

			if ("已繳費".equals(pmfVO.getPf_status())) {
				pf_status = 1;
			} else if ("未繳費".equals(pmfVO.getPf_status())) {
				pf_status = 0;
			}

			pstmt.setString(1, pmfVO.getSt_num());
			pstmt.setInt(2, pmfVO.getPml_trail());
			pstmt.setInt(3, pf_status);
			pstmt.setDate(4, pmfVO.getMonth());
			pstmt.setDate(5, pmfVO.getPf_time());

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
	public void insert2(PMF_VO pmfVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT2);
			int pf_status = 0;

			if ("已繳費".equals(pmfVO.getPf_status())) {
				pf_status = 1;
			} else if ("未繳費".equals(pmfVO.getPf_status())) {
				pf_status = 0;
			}

			pstmt.setString(1, pmfVO.getSt_num());
			pstmt.setInt(2, pmfVO.getPml_trail());
			pstmt.setInt(3, pf_status);
			pstmt.setDate(4, pmfVO.getMonth());
			pstmt.setDate(5, pmfVO.getPf_time());

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
	public void update(PMF_VO pmfVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			int pf_status = 0;

			if ("已繳費".equals(pmfVO.getPf_status())) {
				pf_status = 1;
			} else if ("未繳費".equals(pmfVO.getPf_status())) {
				pf_status = 0;
			}
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(6, pmfVO.getPf_num());
			pstmt.setString(1, pmfVO.getSt_num());
			pstmt.setInt(2, pmfVO.getPml_trail());
			pstmt.setInt(3, pf_status);
			pstmt.setDate(4, pmfVO.getMonth());
			pstmt.setDate(5, pmfVO.getPf_time());
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
	public void delete(Integer pf_num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, pf_num);

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
	public List<PMF_VO> findByPrimaryKey(String st_num) {
		List<PMF_VO> list = new ArrayList<PMF_VO>();
		PMF_VO pmfVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, st_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				pmfVO = new PMF_VO();
				pmfVO.setMonth(rs.getDate("MONTH"));
				pmfVO.setPf_num(rs.getInt("PF_NUM"));
				pmfVO.setPf_status(rs.getInt("PF_STATUS"));
				pmfVO.setPf_time(rs.getDate("PF_TIME"));
				pmfVO.setPml_trail(rs.getInt("PML_TRAIL"));
				pmfVO.setSt_num(rs.getString("ST_NUM"));
				list.add(pmfVO);
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
	public List<PMF_VO> getAll() {

		List<PMF_VO> list = new ArrayList<PMF_VO>();
		PMF_VO pmfVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pmfVO = new PMF_VO();
				pmfVO.setPf_num(rs.getInt("PF_NUM"));
				pmfVO.setSt_num(rs.getString("ST_NUM"));
				pmfVO.setPml_trail(rs.getInt("PML_TRAIL"));
				pmfVO.setPf_status(rs.getInt("PF_STATUS"));
				pmfVO.setMonth(rs.getDate("MONTH"));
				pmfVO.setPf_time(rs.getDate("PF_TIME"));
				list.add(pmfVO); // Store the row in the list
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
		return list;
	}

	@Override
	public Set<PMF_VO> getEmpsByDeptno(Integer pf_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PMP_VO> getPmlByPfnum(Integer pf_num) {
		List<PMP_VO> list = new ArrayList<PMP_VO>();
		PMP_VO pmpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_PMP);

			pstmt.setInt(1, pf_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				pmpVO = new PMP_VO();

				pmpVO.setPml_item(rs.getString("PML_ITEM"));
				pmpVO.setPml_money(rs.getInt("PML_MONEY"));

				list.add(pmpVO);
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
	public boolean havepmf(String st_num, Date month) {		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean flag = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ST_NUM_MONTH);
			rs = pstmt.executeQuery();
			while (rs.next()) {
		//	System.out.println(month.toString().substring(0,7) );
		//	System.out.println(rs.getDate("MONTH").toString().substring(0,7));
//				System.out.println( "st_num 為"+rs.getString("ST_NUM") );
				if (st_num.equals(rs.getString("ST_NUM")) && month.toString().substring(0,7).equals(rs.getDate("MONTH").toString().substring(0,7))) {					
					flag = true;
				}
			}

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

		return flag;
	}

	@Override
	public Integer getOnePMF(String st_num, Date month) {	
	
		PMF_VO pmfVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pf_num;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PF_NUM_ByST_NUM_MONTH);
			
			pstmt.setString(1,st_num);
			pstmt.setDate(2,month);
			rs = pstmt.executeQuery();
			rs.next();
			//System.out.println(rs.getInt("PF_NUM"));
			pf_num = rs.getInt("PF_NUM");
			

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
	
		return pf_num;
	}

	@Override
	public void addmoney(Integer pf_num, Integer pml_trail) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ADD_PML_TRAIL);	

			pstmt.setInt(1, pf_num);
			pstmt.setInt(2, pml_trail);
			pstmt.setInt(3,pf_num);
			

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
	public void updateStatus(Integer num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		java.util.Date date1 = new java.util.Date();
		Date date2 = new java.sql.Date(date1.getTime());
		try {		
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATENUM);
			pstmt.setInt(2, num);
			pstmt.setDate(1,date2);
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
	public PMF_VO GetOnePMF(Integer num) {
		PMF_VO pmfVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETONEPMF);

			pstmt.setInt(1,num);

			rs = pstmt.executeQuery();

			while (rs.next()) {			
				pmfVO = new PMF_VO();				
				pmfVO.setMonth(rs.getDate("Month"));
				pmfVO.setPml_trail(rs.getInt("PML_TRAIL"));
				
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());			
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
		return pmfVO;
	}
public static void main(String[] args) {
	java.util.Date date = new java.util.Date();
	SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM");	
	System.out.println(sdf4.format(date)+"-01");
}
}
