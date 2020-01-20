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

public class Class_picturesJDBCDAO implements Class_picturesDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA102_G4";
	String passwd = "123456";
	
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,class_picturesVO.getCs_num());
			pstmt.setString(2, class_picturesVO.getPic_cs());
			pstmt.setBytes(3, class_picturesVO.getPic());
			pstmt.setTimestamp(4, class_picturesVO.getUl_date());
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1,class_picturesVO.getCs_num());
			pstmt.setTimestamp(2, class_picturesVO.getUl_date());
			pstmt.setBytes(3, class_picturesVO.getPic());
			pstmt.setString(4, class_picturesVO.getPic_cs());
			pstmt.setInt(5, class_picturesVO.getCs_pic_num());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, cs_pic_num);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
	
	@Override
	public Class_picturesVO findByPic_cs(String pic_cs) {
		Class_picturesVO class_picturesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
	
	
	public static void main(String[] args) {
		Class_picturesJDBCDAO dao = new Class_picturesJDBCDAO();
		
		
		// 新增(successed)
//		Class_picturesVO class_picturesVO1 = new Class_picturesVO();
//		class_picturesVO1.setCs_num("C001");
//		class_picturesVO1.setUl_date(java.sql.Timestamp.valueOf("2019-01-04 09:00:00"));
//		
//		try {
//			class_picturesVO1.setPic(getPictureByteArray("D:\\class_pics\\childs\\005.jpg"));
//		}catch(IOException io) {
//			io.printStackTrace();
//		}
//		
//		class_picturesVO1.setPic_cs("新增測試");
//		dao.insert(class_picturesVO1);
	
		
		
		// 修改(successed)
//		Class_picturesVO class_picturesVO2 = new Class_picturesVO();
//		class_picturesVO2.setCs_pic_num(1010);
//		class_picturesVO2.setCs_num("C001");
//		class_picturesVO2.setUl_date(java.sql.Timestamp.valueOf("2019-12-30 15:30:00"));
//		
//		try {
//			class_picturesVO2.setPic(getPictureByteArray("D:/class_pics/childs/c010.JPG"));
//		}catch(IOException io) {
//			io.printStackTrace();
//		}
//		
//		class_picturesVO2.setPic_cs("死小孩");
//		
//		dao.update(class_picturesVO2);
		
		
		// 刪除(successed)
//		dao.delete(1025);
		
		
//		// 查詢(successed)
//		Class_picturesVO class_picturesVO3 = dao.findByPrimaryKey(1015);
//		System.out.print(class_picturesVO3.getCs_pic_num() + ",");
//		System.out.print(class_picturesVO3.getCs_num() + ",");
//		System.out.print(class_picturesVO3.getUl_date() + ",");
//		System.out.print(class_picturesVO3.getPic() + ",");
//		System.out.print(class_picturesVO3.getPic_cs() + ",");
//		System.out.println("---------------------");
		
		
		//查詢全部(successed)
//		List<Class_picturesVO> list = dao.getAll();
//		for (Class_picturesVO aClass_pictures : list) {
//			System.out.print(aClass_pictures.getCs_pic_num() + ",");
//			System.out.print(aClass_pictures.getCs_num() + ",");
//			System.out.print(aClass_pictures.getUl_date() + ",");
//			System.out.print(aClass_pictures.getPic() + ",");
//			System.out.print(aClass_pictures.getPic_cs() + ",");
//
//			System.out.println();
//		}
			

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
	
}