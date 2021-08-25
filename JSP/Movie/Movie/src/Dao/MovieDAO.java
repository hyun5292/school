package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Dto.MovieDTO;

public class MovieDAO {
	private DataSource dataSource;

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public MovieDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}
	
	// 사진가져오기
	public int isChoice(String M_IMG) {
		String SQL = "SELECT M_IMG FROM MOVIE WHERE M_IMG = M_IMG";
		try {
			con = dataSource.getConnection();
			pst = con.prepareStatement(SQL);
			pst.setString(1, M_IMG);
			rs = pst.executeQuery();
			if (rs.isBeforeFirst()) {
				return 1; // 예매가 검색 되었을 경우
			} else {
				return 0; // 예매가 검색 안 되었을 경우
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}

	// 예매여부 확인
	public ArrayList<MovieDTO> LoadMovie() {
		String SQL = "SELECT * FROM MOVIE";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MovieDTO> dtos = new ArrayList<MovieDTO>();
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MovieDTO dto = new MovieDTO();
				dto.setM_no(rs.getString("M_NO"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setDir(rs.getString("DIR"));
				dto.setActor(rs.getString("ACTOR"));
				dto.setGenre(rs.getString("GENRE"));
				dto.setOpendate(rs.getString("OPENDATE"));
				dto.setPlaytime(rs.getString("PLAYTIME"));
				dto.setAge(rs.getString("AGE"));
				dto.setOutline(rs.getString("OUTLINE"));
				dto.setM_img(rs.getString("M_IMG"));
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 내부 객체 닫아주는 코드
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}

	
	// 영화 리스트 출력
		public ArrayList<MovieDTO> getMovieAll() {
			ArrayList<MovieDTO> list = new ArrayList<MovieDTO>();
			try {
				String sql = "select * from movie";
				con = dataSource.getConnection();
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery();

				while (rs.next()) {
					MovieDTO dto = new MovieDTO();
					dto.setM_no(rs.getString("m_no"));
					dto.setTitle(rs.getString("title"));
					dto.setDir(rs.getString("dir"));
					dto.setActor(rs.getString("actor"));
					dto.setGenre(rs.getString("genre"));
					dto.setOpendate(rs.getString("opendate"));
					dto.setPlaytime(rs.getString("playtime"));
					dto.setAge(rs.getString("age"));
					dto.setOutline(rs.getString("outline"));
					dto.setM_img(rs.getString("m_img"));
					list.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (pst != null) {
						pst.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return list;
		}
		
		// 사진가져오기
			public MovieDTO getMovie(String M_NO) {
				String SQL = "SELECT * FROM MOVIE WHERE M_NO=?";
				MovieDTO dto = new MovieDTO();
				try {
					con = dataSource.getConnection();
					pst = con.prepareStatement(SQL);
					pst.setString(1, M_NO);
					rs = pst.executeQuery();

					while(rs.next()) {
						dto.setM_no(rs.getString("M_NO"));
						dto.setTitle(rs.getString("TITLE"));
						dto.setDir(rs.getString("DIR"));
						dto.setActor(rs.getString("ACTOR"));
						dto.setGenre(rs.getString("GENRE"));
						dto.setOpendate(rs.getString("OPENDATE"));
						dto.setPlaytime(rs.getString("PLAYTIME"));
						dto.setAge(rs.getString("AGE"));
						dto.setOutline(rs.getString("OUTLINE"));
						dto.setM_img(rs.getString("M_IMG"));
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return dto; // DB오류
			}
}
