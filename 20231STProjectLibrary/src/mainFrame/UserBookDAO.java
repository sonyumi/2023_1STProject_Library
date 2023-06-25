package mainFrame;

import java.sql.SQLException;
import java.util.ArrayList;

import login.MemberDAO;
import login.MemberVo;

public class UserBookDAO extends MemberDAO {

	public ArrayList<BookVo> list(MemberVo userInfo) {
		ArrayList<BookVo> list = new ArrayList<BookVo>();
		//
		try {
			connDB();
			String query = "SELECT g.id, r.BOOK_CODE,b.BOOK_NAME,b.WRITER ,b.PUBLISHER,TO_char(r.RENTAL_DATE,'YYYY-MM-DD HH:MI:SS') 대여시작일,r.rental_days 대여일수,TO_char(r.RETURN_DATE ,'YYYY-MM-DD HH:MI:SS') 반납일 FROM g_user g,BOOKLIST b ,RENTAL r ";

			if (userInfo != null) {
				query += "WHERE b.BOOK_CODE = r.BOOK_CODE AND r.RENTAL_USER = g.ID and return_date IS NULL AND Rental_user='"
						+ userInfo.getId() + "'"
						+ " GROUP BY g.id,r.BOOK_CODE,b.BOOK_NAME,b.WRITER,b.PUBLISHER,r.RENTAL_DATE,r.RETURN_DATE,r.rental_days ";
			}
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow());

			if (rs.getRow() == 0) {
				// 입력된 아이디 값과 일치하는 데이터가 없을때
			} else {
				System.out.println(rs.getRow()); // 현재 커서 값 출력
				rs.beforeFirst(); // 커서를 이전으로 돌리기
				while (rs.next()) { // 커서를 다음으로 이동, 데이터가 없을시 false
					String uCode = rs.getString("id"); // 로그인 한 유저 아이디
					String bCode = rs.getString("book_code"); // 책코드
					String bName = rs.getString("book_name");
					String bWriter = rs.getString("writer");
					String bPublisher = rs.getString("publisher");
					String bRental = rs.getString("대여시작일");
					String bDays = rs.getString("대여일수");
					String bReturn = rs.getString("반납일");
					BookVo data = new BookVo(uCode, bCode, bName, bWriter, bPublisher, bRental, bDays, bReturn); // BookVo(값
																													// 불러오는
					// 클래스)
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

	public String getReturnValue(String code, int i, String id) {
		String value = null;
		//
		try {
			connDB(); // DB에 연결 하도록 만든 메소드
			String query = "SELECT * FROM ";
			int row1 = 0;
			int row2 = 0;

			// 대출실행 쿼리
			if (code != null && i == 0) {
				query += "(SELECT rental_code,BOOK_CODE ,rental_user,rental_date,rental_days,return_date,rank() over(PARTITION BY book_code ORDER BY RENTAL_CODE desc) a\r\n"
						+ "FROM RENTAL r) \r\n"
						+ "WHERE a = 1 AND rental_date IS NOT NULL AND return_date IS NOT NULL AND book_code='" + code
						+ "'";
				rs = stmt.executeQuery(query);
				rs.last();
				row1 = rs.getRow();
				System.out.println("SQL1 : " + query);
				query = "SELECT *\r\n" + "FROM RENTAL r ,BOOKLIST b \r\n"
						+ "WHERE b.book_code(+) = r.book_code AND b.BOOK_CODE ='" + code + "'";
				rs = stmt.executeQuery(query);
				rs.last();
				row2 = rs.getRow();
				System.out.println("SQL2-1 : " + query);

				if ((row1 != 0 && row2 != 0) || (row1 == 0 && row2 == 0)) {
					query = "INSERT INTO rental (rental_code,book_code,rental_user,rental_date,rental_days)\r\n"
							+ "VALUES (num1.nextval,'" + code + "','" + id + "',sysdate,'14')";
					rs = stmt.executeQuery(query);
					System.out.println("SQL3 : " + query);
					value = "성공";
				} else {
					value = "없음";
				}
			}

			// 반납 실행 쿼리
			if (code != null && i == 1) {
				query += "RENTAL\r\n" + "WHERE BOOK_CODE ='" + code
						+ "' AND RENTAL_DATE IS not NULL AND RETURN_DATE IS NULL" + " AND rental_user = '" + id + "'";
				System.out.println(query);
				rs = stmt.executeQuery(query);
				rs.last();

				if (rs.getRow() != 0) {
					query = "UPDATE rental SET return_date=SYSDATE \r\n" + "WHERE book_code='" + code + "'";
					rs = stmt.executeQuery(query);
					value = "성공";
				} else {
					value = "없음";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println(value);
		return value;
	}

}
