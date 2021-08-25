package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Dto.BoardDTO;

public class BoardDAO {
	private DataSource dataSource;

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public BoardDAO() {
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

	public boolean write(String bTitle, String bName, String bDate, String bContent) {
		boolean rs = false;
		int bId = getBId();

		try {
			con = dataSource.getConnection();
			String query = "insert into Board values(?, ?, ?, ?, 0, ?, 0, 0, ?)";
			pst = con.prepareStatement(query);
			pst.setString(1, bTitle);
			pst.setInt(2, bId);
			pst.setString(3, bName);
			pst.setString(4, bContent);
			pst.setInt(5, bId);
			pst.setString(6, bDate);
			int rn = pst.executeUpdate();

			if (rn > 0) {
				rs = true;
			} else {
				rs = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (pst != null)
					pst.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return rs;
	}

	public int getBId() {
		int bId = 0;
		try {
			con = dataSource.getConnection();
			String query = "select bId from Board";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				if (bId < rs.getInt("bId")) {
					bId = rs.getInt("bId");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (pst != null)
					pst.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return bId + 1;
	}

	public ArrayList<BoardDTO> list(int start, int end) {
		ArrayList<BoardDTO> dtos = new ArrayList<BoardDTO>();
		try {
			con = dataSource.getConnection();
			String query = "select * from Board where BID >= ? and BID <= ? order by bGroup asc, bStep asc";
			pst = con.prepareStatement(query);
			pst.setInt(1, start);
			pst.setInt(2, end);
			rs = pst.executeQuery();

			while (rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = "";
				for (int i = 0; i < rs.getInt("bIndent"); i++) {
					bTitle += "¤¤";
				}
				bTitle += rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				String bDate = rs.getString("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");

				BoardDTO dto = new BoardDTO(bTitle, bId, bName, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dtos;
	}

	public BoardDTO contentView(String bId_str) {
		upHit(bId_str);
		BoardDTO dto = null;

		try {
			con = dataSource.getConnection();
			String query = "select * from Board where bId=?";
			pst = con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(bId_str));
			rs = pst.executeQuery();

			if (rs.next()) {
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				String bDate = rs.getString("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				int bId = Integer.parseInt(bId_str);
				dto = new BoardDTO(bTitle, bId, bName, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dto;
	}

	private void upHit(String bId) {
		try {
			con = dataSource.getConnection();
			String query = "update Board set bHit = bHit + 1 where bId = ?";
			pst = con.prepareStatement(query);
			pst.setString(1, bId);

			int rn = pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void delete(String bId) {
		try {
			con = dataSource.getConnection();
			String query = "delete from Board where bId=?";
			pst = con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(bId));
			int rn = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void modify(String bId, String bName, String bTitle, String bContent) {
		try {
			con = dataSource.getConnection();
			String query = "update Board set bName=?, bTitle=?, bContent=? where bId=?";
			pst = con.prepareStatement(query);
			pst.setString(1, bName);
			pst.setString(2, bTitle);
			pst.setString(3, bContent);
			pst.setInt(4, Integer.parseInt(bId));
			int rn = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public BoardDTO replyView(String Id) {
		BoardDTO dto = null;

		try {
			con = dataSource.getConnection();
			String query = "select * from Board where bId = ?";
			pst = con.prepareStatement(query);

			pst.setString(1, Id);
			rs = pst.executeQuery();

			if (rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				String bDate = rs.getString("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");

				dto = new BoardDTO(bTitle, bId, bName, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dto;
	}

	public void reply(int bId, String bTitle, String bName, String bDate, String bContent, String bHit, String bGroup, String bStep, String bIndent) {
		replyShape(bGroup, bStep);
		System.out.println(bId);

		try {
			con = dataSource.getConnection();

			String query = "insert into Board(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, bDate) values (?,?,?,?,?,?,?,?,?)";

			pst = (PreparedStatement) con.prepareStatement(query);

			pst.setInt(1, bId);
			pst.setString(2, bName);
			pst.setString(3, bTitle);
			pst.setString(4, bContent);
			pst.setInt(5, Integer.parseInt(bHit));
			pst.setInt(6, Integer.parseInt(bGroup));
			pst.setInt(7, Integer.parseInt(bStep) + 1);
			pst.setInt(8, Integer.parseInt(bIndent) + 1);
			pst.setString(9, bDate);

			int rn = pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private void replyShape(String strGroup, String strStep) {
		try {
			con = dataSource.getConnection();
			String query = "update Board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
			pst = con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(strGroup));
			pst.setInt(2, Integer.parseInt(strStep));

			int rn = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public int getCount() {
		int cnt = 0;

		try {
			con = dataSource.getConnection();
			String query = "select count(bGroup) as cnt from Board";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cnt;
	}

	public void ResetNum() {
		int num = 0;
		try {
			con = dataSource.getConnection();
			String query = "select count(BID) as cnt from Board";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt("cnt");
			}
			
			for(int i = 0; i < num; i++) {
				query = "select BID from Board order by bGroup asc";
				pst = con.prepareStatement(query);
				rs = pst.executeQuery();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
