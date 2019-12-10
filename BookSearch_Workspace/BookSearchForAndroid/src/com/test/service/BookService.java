package com.test.service;

import java.sql.Connection;
import java.util.List;
import com.test.dao.BookDAO;
import com.test.dto.BookVO;

// Service 객체를 만들기 위한 class
public class BookService {

	// Business Logic(Transaction)에 대한 method만 나와요!
	// 하나의 Transaction(기능)당 1개의 method가 이용.
	public List<String> getBooksTitle(String keyword) {
		/*
		 * // 로직처리(더하기, 뺴기, for, if, etc...) // 로직처리 코드가 많이 나와요!!! // Database 처리가 나와야
		 * 해요!! // DAO를 만들어서 database처리를 한 후 결과를 가져와요!! BookDAO dao = new BookDAO(); //
		 * CRUD를 다루는 List<String> list = dao.selectTitle(keyword); return list;
		 */
		Connection con = null;
		List<String> list = null;
		try {
			con = common.DBTemplate.getConnection();
			// con 객페에 대한 transaction이 시작
			BookDAO dao = new BookDAO(con); // 데이터베이스를 처리할때 BookDAO한데 con을 넘겨줌 => 인젝션
			list = dao.selectTitle(keyword);
			
			// 얻어온 결과를 이요해서 transaction의 commit과 rollback을 판단
			if(list != null) {
				// transaction 이 정상적으로 처리 되었을 경우
				con.commit();
			}else {
				// transaction 처리에 오류가 있는 경우
				con.rollback();
			}
						
			}catch (Exception e) {
				System.out.println(e);
					
			} finally {
				try {
						
				// close작업을 해줘야함 close할때도 Exception이 발생할수 있기때문에 try-catch 사용해야함
				con.close();
							
			} catch (Exception e1) {
				System.out.println(e1);
			}
								
		}
		
		return list;
		
	}
	
	

	public List<BookVO> getBooks(String keyword) {
		// 로직처리(DB처리를 포함해서)
		// Transaction : 작업의 최소 단위
		// 데이터베이스 connection에대한 코드를 여기에 적어준다. 
		// DAO가 아닌 => Transation을 처리하기위해서는 Service에서 정의해주는 게 맞다.
		
		Connection con = null;
		List<BookVO> list = null;
		
		try {
			con = common.DBTemplate.getConnection();
			// con 객페에 대한 transaction이 시작
			BookDAO dao = new BookDAO(con); // 데이터베이스를 처리할때 BookDAO한데 con을 넘겨줌 => 인젝션
			list = dao.select(keyword);
			
			// 얻어온 결과를 이요해서 transaction의 commit과 rollback을 판단
			if(list != null) {
				// transaction 이 정상적으로 처리 되었을 경우
				con.commit();
			}else {
				// transaction 처리에 오류가 있는 경우
				con.rollback();
			}
			
		}catch (Exception e) {
			System.out.println(e);
		
		} finally {
			try {
				
				// close작업을 해줘야함 close할때도 Exception이 발생할수 있기때문에 try-catch 사용해야함
				con.close();
				
			} catch (Exception e1) {
				System.out.println(e1);
			}
			
				
		}
		
		/*
		 * // DB처리 첫번째 dao.firstMethod();
		 * 
		 * // DB처리 두번째 dao.secondMethod();
		 * 
		 * // DB처리 세번째 dao.thirdMethod();
		 */
		
		return list;
	}

}
