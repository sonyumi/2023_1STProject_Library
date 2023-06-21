package mainFrame;

import java.sql.SQLException;
import java.util.ArrayList;

import login.MemberDAO;

public class BookDAO extends MemberDAO {

	public ArrayList<BookVo> list(String code) {
		ArrayList<BookVo> list = new ArrayList<BookVo>();
		//
		try {
			connDB(); // DB에 연결 하도록 만든 메소드
			String query = "SELECT * FROM booklist";

			if (code != null) {
				// 쿼리에 조건을 더해줌
				query += " where book_code=trim('" + code + "')";
			}
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow());
			// rs.getRow() == 현재 커서가 가리키고 있는 row 번호 리턴

			// 커서 번호가 0인경우 (테이블에 조회할 레코드가 없을경우)
			if (rs.getRow() == 0) {
				// 입력된 아이디 값과 일치하는 데이터가 없을때(로그인실패)
			} else {
				System.out.println(rs.getRow()); // 현재 커서 값 출력
				rs.beforeFirst(); // 커서를 이전으로 돌리기
				while (rs.next()) { // 커서를 다음으로 이동, 데이터가 없을시 false
					String bCode = rs.getString("book_code"); // 북코드 저장
					String bName = rs.getString("book_name"); // 책이름
					String bWriter = rs.getString("writer");
					String bPublisher = rs.getString("publisher");
					String bPosition = rs.getString("position");
					String bImg = rs.getString("image");
					BookVo data = new BookVo(bCode, bName, bWriter, bPublisher, bPosition, bImg); // BookVo(값 불러오는 클래스)
																									// 타입의
					// 변수생성(쿼리문을 저장하기위함)
					list.add(data);// 리스트에 값 저장
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	// 레코드 추가 메서드
	public ArrayList<BookVo> list(String code, String name, String writer, String publisher, String POSITION,
			String image) {
		ArrayList<BookVo> list = new ArrayList<BookVo>();
		//
		code = code.trim();
		name = name.trim();
		writer = writer.trim();
		publisher = publisher.trim();
		POSITION = POSITION.trim();
		image = image.trim();

		try {
			connDB(); // DB에 연결 하도록 만든 메소드
			String query = "Insert into booklist (book_code,book_name,writer,publisher,POSITION,image)";

			if (code != null) {
				query += " VALUES (" + "'" + code + "'," + "'" + name + "'," + "'" + writer + "'," + "'" + publisher
						+ "'," + "'" + POSITION + "'," + "'" + image + "')";
			}
			System.out.println("SQL : " + query);
			super.rs = super.stmt.executeQuery(query); // 쿼리 실행문
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 상위 6개의 책을 찾는 쿼리
	public ArrayList<BookVo> list(int i) {
		ArrayList<BookVo> list = new ArrayList<BookVo>();
		//
		try {
			connDB(); // DB에 연결 하도록 만든 메소드
			String query = "SELECT a.BOOK_CODE,b.BOOK_NAME,b.WRITER ,b.PUBLISHER ,b.IMAGE FROM (SELECT rownum 순위,BOOK_CODE FROM (SELECT book_code,count(*) FROM rental WHERE to_char(rental_date, 'mm') LIKE to_char(SysDATE,'mm') GROUP BY book_code ORDER BY count(*) DESC) WHERE rownum<=";

			if (i != 0) {
				// 쿼리에 조건을 더해줌
				query += i + ") a, BOOKLIST b WHERE a.BOOK_CODE=b.BOOK_CODE ORDER BY 순위";
			}
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.first(); // 커서를 1번으로 이동
			System.out.println("rs.getRow() : " + rs.getRow());
			// rs.getRow() == 현재 커서가 가리키고 있는 row 번호 리턴

			// 커서 번호가 0인경우 (테이블에 조회할 레코드가 없을경우)
			if (rs.getRow() == 0) {
				// 입력된 아이디 값과 일치하는 데이터가 없을때(로그인실패)
			} else {
				System.out.println(rs.getRow()); // 현재 커서 값 출력
				rs.previous(); // 커서를 0번으로 돌리기 (0번부터 조회)
				while (rs.next()) { // 커서를 다음으로 이동, 데이터가 없을시 false
					String bCode = rs.getString("book_code");
					String bName = rs.getString("book_name");
					String bWirter = rs.getString("writer");
					String bPublisher = rs.getString("publisher");
					String bImg = rs.getString("image");
					BookVo data = new BookVo(bCode,bName,bWirter,bPublisher,bImg); // BookVo(값 불러오는 클래스) 타입의
														// 변수생성(쿼리문을 저장하기위함)
					list.add(data);// 리스트에 값 저장
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}