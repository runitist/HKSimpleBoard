package com.hk.itedu.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hk.itedu.VO.S_Board_VO;
import com.hk.itedu.VO.S_Comment_VO;

public class BoardDAO {
	// 게시판 DB 자료를 다루기 위한 클래스

	// 반환값이 void인 메서드 예시
	// public static void 메서드명(파라미터) {
	// //~를 ~하기위한 메서드
	// System.out.println("[~ 메서드 실행]");
	// Connection conn = DBConnector.getConn();
	// PreparedStatement ps = null;
	//
	// String sql = "";
	//
	// try {
	// ps = conn.prepareStatement(sql);
	// ps.executeQuery();
	// System.out.println("~ 완료");
	// }catch(Exception e) {
	// System.out.println("~ 실패");
	// e.printStackTrace();
	// }finally{
	// DBConnector.closeConn(null, ps, conn);
	// }
	// System.out.println("[~ 메서드 종료]");
	// }

	// 반환값이 void가 아닌 메서드 예시
	// public static 반환타입 메서드명(파라미터) {
	// //~를 ~하기위한 메서드
	// System.out.println("[~ 메서드 실행]");
	// Connection conn = DBConnector.getConn();
	// PreparedStatement ps = null;
	// ResultSet rs = null;
	//
	// String sql = "";
	//
	// try {
	// ps = conn.prepareStatement(sql);
	// rs = ps.executeQuery();
	// while(rs.next()){
	// 값 = rs.getInt(1);
	// }
	// System.out.println("~ 완료");
	// }catch(Exception e) {
	// System.out.println("~ 실패");
	// e.printStackTrace();
	// }finally{
	// DBConnector.closeConn(rs, ps, conn);
	// }
	// System.out.println("[~ 메서드 종료]");
	// }

	public static int getMaxNo() {
		// s_board의 회원번호 최대값을 갖고오기 위한 메서드
		System.out.println("[getMaxNo 메서드 실행]");
		int maxNo = 0;
		Connection conn = DBConnector.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select max(board_no) maxno from s_board";

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				maxNo = rs.getInt("maxno");
			}
			System.out.println("회원번호 최대값 갖고오기 성공");
		} catch (Exception e) {
			System.out.println("회원번호 최대값 갖고오기 실패");
			e.printStackTrace();
		} finally {
			DBConnector.closeConn(rs, ps, conn);
		}

		System.out.println("[getMaxNo 메서드 종료]");
		return maxNo;
	}

	public static List<S_Board_VO> get_S_Board_List() {
		// s_board의 정보를 리스트화 하기 위한 메서드
		System.out.println("[get_S_Board_List 메서드 실행]");
		List<S_Board_VO> lvo = new ArrayList<S_Board_VO>();
		Connection conn = DBConnector.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select board_no, board_title, board_content, "
				+ " TO_CHAR(regdate, 'yyyy-mm-dd') regdate, "
				+ " cnt from s_board order by board_no desc";

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				S_Board_VO vo = new S_Board_VO();
				vo.setBoard_No(rs.getInt("board_no"));
				vo.setBoard_Title(rs.getString("board_title"));
				vo.setBoard_Content(rs.getString("board_content"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setCnt(rs.getInt("cnt"));
				lvo.add(vo);
			}
			System.out.println("s_board 리스트화 성공");
		} catch (Exception e) {
			System.out.println("s_board 리스트화 실패");
			e.printStackTrace();
		} finally {
			DBConnector.closeConn(rs, ps, conn);
		}

		System.out.println("[get_S_Board_List 메서드 종료]");
		return lvo;
	}

	public static void insert_S_Board(S_Board_VO vo) {
		// s_board에 자료를 입력하기위한 메서드(글쓰기)
		System.out.println("[insert_S_Board 메서드 실행]");
		Connection conn = DBConnector.getConn();
		PreparedStatement ps = null;

		String sql = "insert into s_board "
				+ " (board_no, board_title, board_content, regdate, cnt) "
				+ " values(?,?,?,?,0) ";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getBoard_No());
			ps.setString(2, vo.getBoard_Title());
			ps.setString(3, vo.getBoard_Content());
			ps.setString(4, vo.getRegdate());
			ps.executeQuery();
			System.out.println("글쓰기 완료");
		} catch (Exception e) {
			System.out.println("글쓰기 실패");
			e.printStackTrace();
		} finally {
			DBConnector.closeConn(null, ps, conn);
		}
		System.out.println("[insert_S_Board 메서드 종료]");
	}

	public static S_Board_VO get_S_Board(int Board_No) {
		// 해당 게시번호의 글 본문을 가져오는 메서드
		System.out.println("[get_S_Board 메서드 실행]");
		S_Board_VO vo = new S_Board_VO();
		Connection conn = DBConnector.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select board_no, board_title, board_content, "
				+ " TO_CHAR(regdate, 'yyyy-mm-dd') regdate, "
				+ " cnt from s_board where board_no = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Board_No);
			rs = ps.executeQuery();
			while (rs.next()) {
				vo.setBoard_No(rs.getInt("board_No"));
				vo.setBoard_Title(rs.getString("board_Title"));
				vo.setBoard_Content(rs.getString("board_Content"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setCnt(rs.getInt("cnt"));
			}
			System.out.println("글 본문 가져오기 성공");
		} catch (Exception e) {
			System.out.println("글 본문 가져오기 실패");
			e.printStackTrace();
		} finally {
			DBConnector.closeConn(rs, ps, conn);
		}

		System.out.println("[get_S_Board 메서드 종료]");
		return vo;
	}

	public static List<S_Comment_VO> getComment(int board_no) {
		// 해당 글의 댓글을 가져와 리스트화 하는 메서드
		System.out.println("[getComment 메서드 실행]");
		List<S_Comment_VO> lsv = new ArrayList<S_Comment_VO>();
		Connection conn = DBConnector.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select comment_no, comment_content, "
				+ " to_char(regdate, 'yyyy-mm-dd') regdate "
				+ "from s_comment where board_no = ? order by comment_no desc";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				S_Comment_VO vo = new S_Comment_VO();
				int comment_no = rs.getInt("comment_no");
				String comment_content = rs.getString("comment_content");
				String regdate = rs.getString("regdate");
				vo.setBoard_no(board_no);
				vo.setComment_no(comment_no);
				vo.setComment_content(comment_content);
				vo.setRegdate(regdate);

				lsv.add(vo);
			}
			System.out.println("댓글 가져오기 성공");
		} catch (Exception e) {
			System.out.println("댓글 가져오기 실패");
			e.printStackTrace();
		} finally {
			DBConnector.closeConn(rs, ps, conn);
		}
		System.out.println("[getComment 메서드 종료]");
		return lsv;
	}

	public static int getMaxCommentNo(int board_no) {
		// s_comment의 commentno의 최대 값을 갖고오는 메서드
		System.out.println("[getMaxCommentNo 메서드 실행]");
		int maxno = 0;
		Connection conn = DBConnector.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select max(comment_no) cno from s_comment where board_no = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				maxno = rs.getInt("cno");
			}
			System.out.println("comment 최대번호 갖고오기 성공");
		} catch (Exception e) {
			System.out.println("comment 최대번호 갖고오기 실패");
			e.printStackTrace();
		} finally {
			DBConnector.closeConn(rs, ps, conn);
		}

		System.out.println("[getMaxCommentNo 메서드 종료]");
		return maxno;
	}

	public static void insertComment(int board_no, String comment_content) {
		// s_comment에 값을 입력하는 메서드(댓글 입력)
		System.out.println("[insertComment 메서드 실행]");
		Connection conn = DBConnector.getConn();
		PreparedStatement ps = null;

		String sql = "insert into s_comment "
				+ " (board_no, comment_no, comment_content, regdate) "
				+ " values (?,?,?,sysdate)";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board_no);
			ps.setInt(2, getMaxCommentNo(board_no) + 1);
			ps.setString(3, comment_content);
			ps.execute();
			System.out.println("댓글 입력 성공");
		} catch (Exception e) {
			System.out.println("댓글 입력 실패");
			e.printStackTrace();
		} finally {
			DBConnector.closeConn(null, ps, conn);
		}
		System.out.println("[insertComment 메서드 종료]");
	}

	public static int getCommentCnt(int board_no) {
		// 코맨트 수를 갖고오기 위한 메서드
		System.out.println("[getCommentCnt 메서드 실행]");
		int cmcnt = 0;
		Connection conn = DBConnector.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select count(comment_no) cno from s_comment where board_no = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				cmcnt = rs.getInt("cno");
			}

			System.out.println("코맨트 수 갖고오기 성공");
		} catch (Exception e) {
			System.out.println("코맨트 수 갖고오기 실패");
			e.printStackTrace();
		} finally {
			DBConnector.closeConn(rs, ps, conn);
		}

		System.out.println("[getCommentCnt 메서드 종료]");
		return cmcnt;
	}

	public static int getCnt(int board_no) {
		// s_board의 각 board_no 별 cnt 값을 가져오는 메서드 방문자수 가져옴.
		System.out.println("[getCnt 메서드 실행]");
		int cnt = 0;
		Connection conn = DBConnector.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select cnt from s_board where board_no =?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			System.out.println("방문자수 갖고오기 성공");
		} catch (Exception e) {
			System.out.println("방문자수 갖고오기 실패");
			e.printStackTrace();
		} finally {
			DBConnector.closeConn(rs, ps, conn);
		}

		System.out.println("[getCnt 메서드 종료]");
		return cnt;
	}

	public static void updateCnt(int board_no) {
		// 방문자수 업데이트를 위한 메서드
		System.out.println("[updateCnt 메서드 실행]");
		Connection conn = DBConnector.getConn();
		PreparedStatement ps = null;
		String sql = "update s_board set cnt = ? where board_no=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, getCnt(board_no) + 1);
			ps.setInt(2, board_no);
			ps.execute();
			System.out.println("방문자수 업데이트 완료");
		} catch (Exception e) {
			System.out.println("방문자수 업데이트 실패");
			e.printStackTrace();
		} finally {
			DBConnector.closeConn(null, ps, conn);
		}

		System.out.println("[updateCnt 메서드 종료]");
	}

	public static void deleteComment(int board_no, int comment_no) {
		// 댓글을 지우는 메서드
		System.out.println("[deleteComment 메서드 실행]");
		Connection conn = DBConnector.getConn();
		PreparedStatement ps = null;

		String sql = "delete from s_comment where "
				+ " board_no = ? and comment_no = ? ";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board_no);
			ps.setInt(2, comment_no);
			ps.execute();

			System.out.println("댓글 지움 성공");
		} catch (Exception e) {
			System.out.println("댓글 지움 실패");
			e.printStackTrace();
		} finally {
			DBConnector.closeConn(null, ps, conn);
		}

		System.out.println("[deleteComment 메서드 종료]");
	}

	public static void deleteCommentAll(int board_no) {
		// 해당 보드의 모든 댓글을 삭제하는 메서드
		System.out.println("[deleteComment 메서드 실행]");
		Connection conn = DBConnector.getConn();
		PreparedStatement ps = null;

		String sql = "delete from s_comment where board_no = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board_no);
			ps.execute();
			System.out.println("댓글 모두 삭제 완료");
		} catch (Exception e) {
			System.out.println("댓글 모두 삭제 실패");
			e.printStackTrace();
		} finally {
			DBConnector.closeConn(null, ps, conn);
		}

		System.out.println("[deleteComment 메서드 종료]");
	}

	public static void deleteBoard(int board_no) {
		// 해당 보드를 삭제하는 메서드
		System.out.println("[deleteBoard 메서드 실행]");
		Connection conn = DBConnector.getConn();
		PreparedStatement ps = null;
		deleteCommentAll(board_no);// 이 메서드를 실행 전에 댓글 삭제
		
		String sql = "delete from s_board where board_no=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board_no);
			ps.execute();
			System.out.println("글 삭제 완료");
		} catch (Exception e) {
			System.out.println("글 삭제 실패");
			e.printStackTrace();
		} finally {
			DBConnector.closeConn(null, ps, conn);
		}

		System.out.println("[deleteBoard 메서드 종료]");
	}
}
