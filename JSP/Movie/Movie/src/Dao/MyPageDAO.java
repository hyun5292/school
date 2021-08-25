package Dao;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Dto.MyPageDTO;

public class MyPageDAO {

	private DataSource dataSource;

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public MyPageDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static MyPageDAO instance = new MyPageDAO();

	public static MyPageDAO getInstance() {
		return instance;
	}

	public ArrayList<MyPageDTO> accountAll(String id) {
		ArrayList<MyPageDTO> dtos = new ArrayList<MyPageDTO>();
		String query = "select * from Reserve where id = ?";

		try {
			con = dataSource.getConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				MyPageDTO dto = new MyPageDTO();
				dto.setR_no(rs.getString("r_no"));
				dto.setId(rs.getString("id"));
				dto.setM_no(rs.getString("m_no"));
				dto.setTheater(rs.getString("theater"));
				dto.setR_date(rs.getString("r_date"));
				dto.setR_time(rs.getString("r_time"));
				dto.setSeat(rs.getString("seat"));

				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		return dtos;
	}

	public ArrayList<MyPageDTO> setImage(ArrayList<MyPageDTO> dtos) {
		try {
			for (int i = 0; i < dtos.size(); i++) {
				String query = "select m_img from movie where m_no = ?";
				con = dataSource.getConnection();
				pst = con.prepareStatement(query);
				pst.setString(1, dtos.get(i).getM_no());
				rs = pst.executeQuery();
				
				if(rs.next()) {
					dtos.get(i).setM_img(rs.getString("M_IMG"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		return dtos;
	}
}
