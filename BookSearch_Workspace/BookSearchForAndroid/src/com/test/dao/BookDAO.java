package com.test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.test.dto.BookVO;

public class BookDAO {
	
	// Service에서 사용해줬으니까 여기서 밑에 처럼 해줘야함
	private Connection con;
	
	public BookDAO(Connection con) {
		this.con = con;
	}

	public List<String> selectTitle(String keyword) {
		// keyword를 입력받아서 Database를 검색해서 
		// String[]을 만들어서 return 해주는 DB처리
		// JDBC를 이용한 DB처리
	
		// 결과가 저장될 list만들기
		List<String> list = new ArrayList<String>();
		
		// 오류가 발생할 수 있으니 try-catch사용
		try {
	
			/*
			 * // 1. Driver Loading // MySQL을 위한 JDBC Driver class를 로딩
			 * Class.forName("com.mysql.jdbc.Driver"); System.out.println("로딩 성공!!"); // 2.
			 * Connection 단계 String id = "android"; String pw = "android"; String jdbcUrl =
			 * "jdbc:mysql://localhost:3306/library?characterEncoding=UTF8"; // 상당히 로드가 많이
			 * 걸리는 작업 // Connection pool 을 사용하는 코드로 재작성 // -> 미리 conn을 만들어 놓고 필요할 때 마다 가져다
			 * 쓴다. // Apache Tomcat DBCP라는 Connection pool 기능을 제공 // DBCP는 JNDI을 이용
			 */			
			
			/*
			 * // Connection con = DriverManager.getConnection(jdbcUrl,id,pw); Connection
			 * con = common.DBTemplate.getConnection(); // 사용자가 많아져도 데이터베이스가 안힘들어함
			 * 
			 * System.out.println("연결 성공!!");
			 */
			
			// 3. Statement 작성
			String sql = "select btitle from book where btitle like ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,"%" + keyword + "%");
			
			// 4. Query 실행
			ResultSet rs = pstmt.executeQuery();
			
			// 5. 결과처리
			while(rs.next()) {
				list.add(rs.getString("btitle"));
			}
			
			// 6. 사용한 resource 해제
			rs.close();
			pstmt.close();
			/* con.close(); */			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public List<BookVO> select(String keyword) {
		
		List<BookVO> list = new ArrayList<BookVO>();
		try {
			
			String sql = "select bimgurl, btitle, bauthor, bprice from book where btitle like ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			
			ResultSet rs = pstmt.executeQuery();
		
			while(rs.next()) {
				// 각각의 책을 객체화 시켜서 ArrayList에 저장
				BookVO temp = new BookVO();
				temp.setBimgurl(rs.getString("bimgurl"));
				temp.setBtitle(rs.getString("btitle"));
				temp.setBauthor(rs.getString("bauthor"));
				temp.setBprice(rs.getString("bprice"));
				list.add(temp);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return list;
	}
	

}


