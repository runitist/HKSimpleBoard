package com.hk.itedu.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {
	// DB의 커넥터 객체를 얻기위한 클래스

	public static Connection getConn() {
		System.out.println("[getConn 메서드 실행]");
		Connection conn = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hkitedu";

		try {
			Class.forName(driver);
			System.out.println("DB드라이버 연결 성공");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB계정 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("DB드라이버 연결 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB계정 연결 실패");
			e.printStackTrace();
		}

		System.out.println("[getConn 메서드 종료]");
		return conn;
	}

	public static void closeConn(ResultSet rs, PreparedStatement ps, Connection conn) {
		// DB 관련 객체를 해제하는 메서드
		System.out.println("[closeConn 메서드 실행]");
		try {
			if (rs != null) {
				rs.close();
				System.out.println("ResultSet 객체 해제 완료");
			}
		} catch (Exception e) {
			System.out.println("ResultSet 객체 해제 실패");
		}
		try {
			if (ps != null) {
				ps.close();
				System.out.println("PreparedStatement 객체 해제 완료");
			}
		} catch (Exception e) {
			System.out.println("PreparedStatement 객체 해제 실패");
		}
		try {
			if (conn != null) {
				conn.close();
				System.out.println("Connection 객체 해제 완료");
			}
		} catch (Exception e) {
			System.out.println("Connection 객체 해제 실패");
		}
		System.out.println("[closeConn 메서드 종료]");
	}

}
