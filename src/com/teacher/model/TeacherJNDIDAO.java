package com.teacher.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TeacherJNDIDAO implements TeacherDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA102_G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static final String INSERT = "INSERT INTO teacher (t_num, t_name, t_gender, t_id, t_email,"
			+ "t_r_address, t_address, t_job, t_password, t_birthday, t_status, t_photo)"
			+ "VALUES ('T'||LPAD(to_char(SEQ_TEACHER.NEXTVAL), 3, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String UPDATE = "UPDATE teacher SET t_name = ?, t_gender = ?, t_id = ?,"
			+ "t_email = ?, t_r_address = ?, t_address = ?, t_job = ?, t_password = ?, t_birthday = ?,"
			+ "t_status = ?, t_photo=? WHERE t_num = ?";

	public static final String DELETE = "DELETE FROM teacher WHERE t_num = ?";

	public static final String GET_ALL = "SELECT * FROM teacher order by t_num";

	public static final String GET_BY_T_NUM = "SELECT * FROM teacher WHERE t_num = ?";
	
	public static final String GET_BY_T_ID = "SELECT * FROM teacher WHERE t_id = ?";

	@Override
	public void insert(TeacherVO teacherVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, teacherVO.getT_name());
			pstmt.setString(2, teacherVO.getT_gender());
			pstmt.setString(3, teacherVO.getT_id());
			pstmt.setString(4, teacherVO.getT_email());
			pstmt.setString(5, teacherVO.getT_r_address());
			pstmt.setString(6, teacherVO.getT_address());
			pstmt.setString(7, teacherVO.getT_job());
			pstmt.setString(8, teacherVO.getT_password());
			pstmt.setDate(9, teacherVO.getT_birthday());
			pstmt.setInt(10, teacherVO.getT_status());
			pstmt.setBytes(11, teacherVO.getT_photo());

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void update(TeacherVO teacherVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, teacherVO.getT_name());
			pstmt.setString(2, teacherVO.getT_gender());
			pstmt.setString(3, teacherVO.getT_id());
			pstmt.setString(4, teacherVO.getT_email());
			pstmt.setString(5, teacherVO.getT_r_address());
			pstmt.setString(6, teacherVO.getT_address());
			pstmt.setString(7, teacherVO.getT_job());
			pstmt.setString(8, teacherVO.getT_password());
			pstmt.setDate(9, teacherVO.getT_birthday());
			pstmt.setInt(10, teacherVO.getT_status());
			pstmt.setBytes(11, teacherVO.getT_photo());
			pstmt.setString(12, teacherVO.getT_num());

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

	@Override
	public void delete(String t_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, t_num);

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

	@Override
	public List<TeacherVO> getAll() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TeacherVO teacherVO = null;
		
		List<TeacherVO> list = new ArrayList<TeacherVO>();
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				teacherVO = new TeacherVO();
				teacherVO.setT_num(rs.getString("t_num"));
				teacherVO.setT_name(rs.getString("t_name"));
				teacherVO.setT_gender(rs.getString("t_gender"));
				teacherVO.setT_id(rs.getString("t_id"));
				teacherVO.setT_email(rs.getString("t_email"));
				teacherVO.setT_r_address(rs.getString("t_r_address"));
				teacherVO.setT_address(rs.getString("t_address"));
				teacherVO.setT_job(rs.getString("t_job"));
				teacherVO.setT_password(rs.getString("t_password"));
				teacherVO.setT_birthday(rs.getDate("t_birthday"));
				teacherVO.setT_status(rs.getInt("t_status"));
				teacherVO.setT_photo(rs.getBytes("t_photo"));
				list.add(teacherVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return list;
	}

	@Override
	public TeacherVO findByT_Num(String t_num) {
		
		Set<TeacherVO> set = new LinkedHashSet<TeacherVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TeacherVO teacherVO = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_T_NUM);
			pstmt.setString(1, t_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				teacherVO = new TeacherVO();
				teacherVO.setT_num(rs.getString("t_num"));
				teacherVO.setT_name(rs.getString("t_name"));
				teacherVO.setT_gender(rs.getString("t_gender"));
				teacherVO.setT_id(rs.getString("t_id"));
				teacherVO.setT_email(rs.getString("t_email"));
				teacherVO.setT_r_address(rs.getString("t_r_address"));
				teacherVO.setT_address(rs.getString("t_address"));
				teacherVO.setT_job(rs.getString("t_job"));
				teacherVO.setT_password(rs.getString("t_password"));
				teacherVO.setT_birthday(rs.getDate("t_birthday"));
				teacherVO.setT_status(rs.getInt("t_status"));
				teacherVO.setT_photo(rs.getBytes("t_photo"));
				set.add(teacherVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return teacherVO;
	}
	
	@Override
	public TeacherVO findByT_Id(String t_id) {
		
		Set<TeacherVO> set = new LinkedHashSet<TeacherVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TeacherVO teacherVO = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_T_ID);
			pstmt.setString(1, t_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				teacherVO = new TeacherVO();
				teacherVO.setT_num(rs.getString("t_num"));
				teacherVO.setT_name(rs.getString("t_name"));
				teacherVO.setT_gender(rs.getString("t_gender"));
				teacherVO.setT_id(rs.getString("t_id"));
				teacherVO.setT_email(rs.getString("t_email"));
				teacherVO.setT_r_address(rs.getString("t_r_address"));
				teacherVO.setT_address(rs.getString("t_address"));
				teacherVO.setT_job(rs.getString("t_job"));
				teacherVO.setT_password(rs.getString("t_password"));
				teacherVO.setT_birthday(rs.getDate("t_birthday"));
				teacherVO.setT_status(rs.getInt("t_status"));
				teacherVO.setT_photo(rs.getBytes("t_photo"));
				set.add(teacherVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return teacherVO;
	}
	

}
