package com.class_pictures.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

public class Class_picturesJNDIDAO implements Class_picturesDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA102_G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT =
			"INSERT INTO class_pictures (cs_pic_num,cs_num,pic_cs,pic,ul_date)VALUES(seq_class_pictures.NEXTVAL,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT cs_pic_num,cs_num,to_char(ul_date,'yyyy-mm-dd HH24:MI:SS')ul_date,pic,pic_cs FROM class_pictures order by cs_pic_num";
	private static final String GET_ONE_STMT =
			"SELECT cs_pic_num,cs_num,to_char(ul_date,'yyyy-mm-dd HH24:MI:SS')ul_date,pic,pic_cs FROM class_pictures where cs_pic_num = ?";
	private static final String DELETE = 
			"DELETE FROM class_pictures where cs_pic_num=?";
	private static final String UPDATE =
			"UPDATE class_pictures set cs_num=?, ul_date=?, pic=?, pic_cs=? where cs_pic_num=?";
	private static final String FIND_BY_PIC_CS = 
			"SELECT cs_pic_num,cs_num,to_char(ul_date,'yyyy-mm-dd HH24:MI:SS')ul_date,pic,pic_cs FROM class_pictures where pic_cs = ?";
	
	
	
	@Override
	public void insert(Class_picturesVO class_picturesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,class_picturesVO.getCs_num());
			pstmt.setString(2, class_picturesVO.getPic_cs());
			pstmt.setBytes(3, class_picturesVO.getPic());
			pstmt.setTimestamp(4, class_picturesVO.getUl_date());
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
	public void update(Class_picturesVO class_picturesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1,class_picturesVO.getCs_num());
			pstmt.setTimestamp(2, class_picturesVO.getUl_date());
			pstmt.setBytes(3, class_picturesVO.getPic());
			pstmt.setString(4, class_picturesVO.getPic_cs());
			pstmt.setInt(5, class_picturesVO.getCs_pic_num());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(Integer cs_pic_num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, cs_pic_num);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public Class_picturesVO findByPrimaryKey(Integer cs_pic_num) {
		Class_picturesVO class_picturesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, cs_pic_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				class_picturesVO = new Class_picturesVO();
				class_picturesVO.setCs_pic_num(rs.getInt("cs_pic_num"));
				class_picturesVO.setCs_num(rs.getString("cs_num"));
				class_picturesVO.setUl_date(rs.getTimestamp("ul_date"));
				class_picturesVO.setPic(rs.getBytes("pic"));
				class_picturesVO.setPic_cs(rs.getString("pic_cs"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return class_picturesVO;
	}

	
	
	
	@Override
	public List<Class_picturesVO> getAll() {
		List<Class_picturesVO> list = new ArrayList<Class_picturesVO>();
		Class_picturesVO class_picturesVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				class_picturesVO = new Class_picturesVO();
				class_picturesVO.setCs_pic_num(rs.getInt("cs_pic_num"));
				class_picturesVO.setCs_num(rs.getString("cs_num"));
				class_picturesVO.setUl_date(rs.getTimestamp("ul_date"));
				class_picturesVO.setPic(rs.getBytes("pic"));
				class_picturesVO.setPic_cs(rs.getString("pic_cs"));
				list.add(class_picturesVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	
	
	//BLOB JDBC
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();
		
		return baos.toByteArray();
	}
	
	// Handle with byte array data
	public static void readPicture(byte[] bytes) throws IOException {
		FileOutputStream fos = new FileOutputStream("Output/2.png");
		fos.write(bytes);
		fos.flush();
		fos.close();
		
	}



	@Override
	public Class_picturesVO findByPic_cs(String pic_cs) {
		Class_picturesVO class_picturesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PIC_CS);

			pstmt.setString(1, pic_cs);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				class_picturesVO = new Class_picturesVO();
				class_picturesVO.setCs_pic_num(rs.getInt("cs_pic_num"));
				class_picturesVO.setCs_num(rs.getString("cs_num"));
				class_picturesVO.setUl_date(rs.getTimestamp("ul_date"));
				class_picturesVO.setPic(rs.getBytes("pic"));
				class_picturesVO.setPic_cs(rs.getString("pic_cs"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return class_picturesVO;
	}
	
}