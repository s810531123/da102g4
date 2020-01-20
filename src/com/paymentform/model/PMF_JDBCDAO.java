package com.paymentform.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.paymentlist.model.PML_Service;
import com.paymentlist.model.PML_VO;
import com.paymentproject.model.PMP_VO;
import com.student.model.StudentService;

public class PMF_JDBCDAO implements PMFDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA102_G4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO PAYMENT_FORM (PF_NUM,ST_NUM,PML_TRAIL,PF_STATUS,MONTH,PF_TIME) VALUES (PF_NUM_seq.NEXTVAL,?,?,?,?,?)";
	private static final String INSERT_STMT2 = "INSERT INTO PAYMENT_FORM (PF_NUM,ST_NUM,PML_TRAIL,PF_STATUS,MONTH,PF_TIME) VALUES (PF_NUM_seq.CURRVAL,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT PF_NUM,ST_NUM,PML_TRAIL,PF_STATUS,MONTH,PF_TIME FROM PAYMENT_FORM order by PF_NUM";
	private static final String GET_ONE_STMT = "SELECT PF_NUM,ST_NUM,PML_TRAIL,PF_STATUS,MONTH,PF_TIME FROM PAYMENT_FORM where empno = ?";
	private static final String DELETE = "DELETE FROM PAYMENT_FORM where PF_NUM = ?";
	private static final String UPDATE = "UPDATE PAYMENT_FORM set ST_NUM=?,PML_TRAIL=?,PF_STATUS=?,MONTH=?,PF_TIME=? where PF_NUM = ?";
	private static final String currseq1 = "SELECT pf_num_seq.nextval  FROM DUAL";
	private static final String currseq2 = "SELECT pf_num_seq.currval  FROM DUAL";
	
	
	@Override
	public void insert(PMF_VO pmfVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			int pf_status = 0;

			if ("已繳費".equals(pmfVO.getPf_status())) {
				pf_status = 1;
			} else if ("未繳費".equals(pmfVO.getPf_status())) {
				pf_status = 0;
			}
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, pmfVO.getSt_num());
			pstmt.setInt(2, pmfVO.getPml_trail());
			pstmt.setInt(3, pf_status);
			pstmt.setDate(4, pmfVO.getMonth());
			pstmt.setDate(5, pmfVO.getPf_time());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			int pf_status = 0;

			if ("已繳費".equals(pmfVO.getPf_status())) {
				pf_status = 1;
			} else if ("未繳費".equals(pmfVO.getPf_status())) {
				pf_status = 0;
			}
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, pmfVO.getSt_num());
			pstmt.setInt(2, pmfVO.getPml_trail());
			pstmt.setInt(3, pf_status);
			pstmt.setDate(4, pmfVO.getMonth());
			pstmt.setDate(5, pmfVO.getPf_time());
			pstmt.setInt(6, pmfVO.getPf_num());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, pf_num);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		return list;
	}

	@Override
	public Set<PMF_VO> getEmpsByDeptno(Integer pf_num) {

		return null;
	}

	public static void main(String[] args) {		
		
		java.util.Date ud = new java.util.Date();
		java.sql.Date sd = new java.sql.Date(ud.getTime());
		StudentService STsvc = new StudentService();
		
		PMF_Service pmfsvc = new PMF_Service();
		PMF_VO pmfVO = new PMF_VO();
		pmfVO.setPf_status(0);
		pmfVO.setMonth(sd);
		pmfVO.setPml_trail(0);
		pmfVO.setPf_time(null);	
		
		PML_VO pmlVO = new PML_VO();		
		PML_Service pmlsvc = new PML_Service();		
		
		for(String str :STsvc.getStudentNum()) {		
		int pf_num = pmfsvc.getcurrseq1();
		pmfVO.setSt_num(str);				
		pmfsvc.addPMF2(pmfVO);
		
		pmlVO.setPml_num(1031);
		pmlVO.setPf_num(pf_num);
	//	System.out.println(pmfsvc.getcurrseq1());
		pmlsvc.addASC(pmlVO);}
		
		
//		PMF_DAO dao = new PMF_DAO();
//
//		PMF_VO pmfVO1 = new PMF_VO();
//		pmfVO1.setMonth(java.sql.Date.valueOf("2019-08-31"));
//		pmfVO1.setPf_status(1);
//		pmfVO1.setPf_time(java.sql.Date.valueOf("2019-08-30"));
//		pmfVO1.setPml_trail(3333);
//		pmfVO1.setSt_num("S002");
//		dao.insert(pmfVO1);

//		PMF_VO pmfVO2 = new PMF_VO();
//		pmfVO2.setMonth(java.sql.Date.valueOf("2019-07-31"));		
//		pmfVO2.setPf_status(0);
//		pmfVO2.setPf_time(java.sql.Date.valueOf("2019-07-30"));
//		pmfVO2.setPml_trail(555);
//		pmfVO2.setSt_num("S010");
//		pmfVO2.setPf_num(1010);
//		dao.update(pmfVO2);

//  	dao.delete(1010);

//		PMF_VO pmfVO3 = dao.findByPrimaryKey(1002);
//		System.out.print(pmfVO3.getSt_num() + ",");
//		System.out.print(pmfVO3.getMonth() + ",");
//		System.out.print(pmfVO3.getPf_num() + ",");
//		System.out.print(pmfVO3.getPf_status() + ",");
//		System.out.print(pmfVO3.getPf_time() + ",");
//		System.out.print(pmfVO3.getPml_trail() + ",");
//		System.out.println("---------------------");

//		List<PMF_VO> list = dao.getAll();
//		for (PMF_VO pmfVO : list) {
//			System.out.print(pmfVO.getSt_num()+ ",");
//			System.out.print(pmfVO.getMonth()+ ",");
//			System.out.print(pmfVO.getPf_num()+ ",");
//			System.out.print(pmfVO.getPf_status()+ ",");
//			System.out.print(pmfVO.getPf_time()+ ",");
//			System.out.print(pmfVO.getPml_trail()+ ",");					
//		}
	}
	

	@Override
	public Integer getcurrseq1() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int seq=0;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(currseq1);		
			rs = pstmt.executeQuery();
			rs.next();
			seq=rs.getInt("nextval");			
		}catch (ClassNotFoundException e) {
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
		return seq;
	}

	@Override
	public Integer getcurrseq2() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int seq=0;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(currseq2);		
			rs = pstmt.executeQuery();
			rs.next();
			seq=rs.getInt("nextval");			
		}catch (ClassNotFoundException e) {
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
		return seq;
	}

	@Override
	public void insert2(PMF_VO pmfVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<PMP_VO> getPmlByPfnum(Integer pf_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean havepmf(String st_num, Date month) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer getOnePMF(String st_num, Date month) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addmoney(Integer pf_num, Integer pml_trail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateStatus(Integer num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PMF_VO GetOnePMF(Integer num) {
		// TODO Auto-generated method stub
		return null;
	}
}