package mainFrame;

import java.sql.SQLException;
import java.util.ArrayList;

import login.MemberDAO;

public class BookDAO extends MemberDAO {

	public BookDAO() {
		super.connDB();
	}

	public ArrayList<BookVo> list(String code) {
		ArrayList<BookVo> list = new ArrayList<BookVo>();
		//
		try {
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

	// 일반 사용자가 볼수있는 빌릴수 있는 도서 or 없는도서 목록 출력하기
	public ArrayList<BookVo> list(String code, int colnum) {
		ArrayList<BookVo> list = new ArrayList<BookVo>();
		//
		try {
			String query = "SELECT bb.book_code,bb.book_name,bb.writer,bb.publisher,bb.POSITION,c.대여가능여부,bb.image\r\n"
					+ "FROM (SELECT b.BOOK_CODE, r.RETURN_DATE ,r.RENTAL_CODE  ,rank() over(PARTITION BY b.BOOK_CODE ORDER BY rental_code desc) ra,\r\n"
					+ "	CASE\r\n" + "	WHEN r.return_date IS NULL and r.rental_date IS NOT NULL THEN 'N'\r\n"
					+ "	WHEN r.rental_date IS null AND r.return_date IS NULL THEN 'Y'\r\n" + "	ELSE 'Y'\r\n"
					+ "	END 대여가능여부\r\n" + "FROM BOOKLIST b ,RENTAL r \r\n"
					+ "WHERE b.BOOK_CODE =r.BOOK_CODE (+)) c , booklist bb\r\n"
					+ "where ra = 1 AND bb.book_code = c.book_code(+)";
			System.out.println(code + " " + colnum);
			if (code != null) {
				// 쿼리에 조건을 더해줌
				if (colnum == 0) {
					query += "";
				} else if (colnum == 1) {
					query += " AND BB.BOOK_CODE like'%" + code + "%'";
				} else if (colnum == 2) {
					query += " AND BB.BOOK_NAME like'%" + code + "%'";
				} else if (colnum == 3) {
					query += " AND BB.WRITER like'%" + code + "%'";
				} else if (colnum == 4) {
					query += " AND BB.PUBLISHER like'%" + code + "%'";
				}
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
				// System.out.println(rs.getRow()); // 현재 커서 값 출력
				rs.beforeFirst(); // 커서를 0번으로 돌리기
				while (rs.next()) { // 커서를 다음으로 이동, 데이터가 없을시 false
					String bCode = rs.getString("book_code"); // 북코드 저장
					String bName = rs.getString("book_name"); // 책이름
					String bWriter = rs.getString("writer");
					String bPublisher = rs.getString("publisher");
					String bPosition = rs.getString("position");
					String bReturn = rs.getString("대여가능여부");
					String bImg = rs.getString("image");

					BookVo data = new BookVo(bCode, bName, bWriter, bPublisher, bPosition, bReturn, bImg); // BookVo(값
																											// 불러오는 클래스)
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
	public ArrayList<BookVo> list(String code, String name, String writer, String publisher, String position,
			String image, int i) {
		ArrayList<BookVo> list = new ArrayList<BookVo>();
		code = code.trim();
		name = name.trim();
		writer = writer.trim();
		publisher = publisher.trim();
		position = position.trim();
		try {
			if (code != null) {
				String query = "select * from booklist";
				if (i == 0) {
					query = " UPDATE booklist b SET b.BOOK_NAME = '" + name + "',b.WRITER = '" + writer
							+ "',b.PUBLISHER ='" + publisher + "',b.POSITION = '" + position + "',b.IMAGE ='" + image
							+ "'  WHERE book_code = '" + code + "'";
				} else if (i == 1) { // 새로 만들기
					query = "Insert into booklist (book_code,book_name,writer,publisher,POSITION,image)" + " VALUES ("
							+ "'" + code + "'," + "'" + name + "'," + "'" + writer + "'," + "'" + publisher + "'," + "'"
							+ position + "'," + "'" + image + "')";
				} else if (i == 2) {
					query = "DELETE rental WHERE book_code = '" + code + "'";
					rs = stmt.executeQuery(query);
					query = "DELETE booklist WHERE book_code = '" + code + "'";
				}
				// System.out.println("SQL : " + query);
				rs = stmt.executeQuery(query); // 쿼리 실행문
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<BookVo> list1(String code, String table) {
		ArrayList<BookVo> list1 = new ArrayList<BookVo>();
		//
		try {
			String query = "SELECT bb.book_code,bb.book_name,bb.writer,bb.publisher,bb.POSITION,bb.image,c.반납예정일\r\n"
					+ "FROM (\r\n"
					+ "SELECT b.BOOK_CODE ,RANK() OVER(PARTITION BY b.BOOK_CODE ORDER BY rental_code DESC) a,TO_char(r.RENTAL_DATE+r.RENTAL_DAYS,'YYYY-MM-DD HH:MI:SS') 반납예정일\r\n"
					+ "FROM booklist b, RENTAL r \r\n"
					+ "WHERE b.BOOK_CODE =r.BOOK_CODE (+) AND r.RETURN_DATE IS NULL) c , booklist bb\r\n"
					+ "WHERE bb.book_code = c.book_code(+)";

			if (code != null) {
				if (table.equals("all")) {
				} else if (table.equals("책코드")) {
					query += " AND bb.book_code like '%" + code + "%'";
				} else if (table.equals("책이름")) {
					query += " AND bb.book_name LIKE '%" + code + "%'";
				} else if (table.equals("저자")) {
					query += " AND bb.writer LIKE '%" + code + "%'";
				} else if (table.equals("출판사")) {
					query += " AND bb.publisher LIKE '%" + code + "%'";
				}

			}
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query); // 쿼리 실행문
			rs.last();
			System.out.println(rs.getRow());

			if (rs.getRow() == 0) {
				// 입력된 아이디 값과 일치하는 데이터가 없을때
			} else {
				System.out.println(rs.getRow()); // 현재 커서 값 출력
				rs.beforeFirst(); // 커서를 0번으로 돌리기 (0번부터 조회)
				while (rs.next()) { // 커서를 다음으로 이동, 데이터가 없을시 false
					String bCode = rs.getString("book_code");
					String bName = rs.getString("book_name");
					String bWirter = rs.getString("writer");
					String bPublisher = rs.getString("publisher");
					String bPosition = rs.getString("position");
					String bReturndate = rs.getString("반납예정일");
					String bImg = rs.getString("image");
					BookVo data = new BookVo(bCode, bName, bWirter, bPublisher, bPosition, bReturndate, bImg);
					// 변수생성(쿼리문을 저장하기위함)
					list1.add(data);// 리스트에 값 저장
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list1;
	}

	// 상위 6개의 책을 찾는 쿼리
	public ArrayList<BookVo> list(int i) {
		ArrayList<BookVo> list = new ArrayList<BookVo>();
		//
		try {
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

			if (i<15 || rs.getRow()==0) {
				query = "select * from (\r\n" + "    select * from BOOKLIST b  order by DBMS_RANDOM.RANDOM)\r\n"
						+ "where rownum <= 6";
				rs = stmt.executeQuery(query);
				System.out.println("SQL : " + query);
				rs.beforeFirst();
				while (rs.next()) { // 커서를 다음으로 이동, 데이터가 없을시 false
					String bCode = rs.getString("book_code");
					String bName = rs.getString("book_name");
					String bWirter = rs.getString("writer");
					String bPublisher = rs.getString("publisher");
					String bImg = rs.getString("image");
					BookVo data = new BookVo(bCode, bName, bWirter, bPublisher, bImg); // BookVo(값 불러오는 클래스) 타입의
					// 변수생성(쿼리문을 저장하기위함)
					list.add(data);// 리스트에 값 저장
				}
				list.add(6, new BookVo());
			} else {
				System.out.println(rs.getRow()); // 현재 커서 값 출력
				rs.previous(); // 커서를 0번으로 돌리기 (0번부터 조회)
				while (rs.next()) { // 커서를 다음으로 이동, 데이터가 없을시 false
					String bCode = rs.getString("book_code");
					String bName = rs.getString("book_name");
					String bWirter = rs.getString("writer");
					String bPublisher = rs.getString("publisher");
					String bImg = rs.getString("image");
					BookVo data = new BookVo(bCode, bName, bWirter, bPublisher, bImg); // BookVo(값 불러오는 클래스) 타입의
					// 변수생성(쿼리문을 저장하기위함)
					list.add(data);// 리스트에 값 저장
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public String getNewCode(String date) {
		String code = null;
		try {
			String query = "SELECT max(book_code)\r\n" + "FROM BOOKLIST b \r\n" + "WHERE book_code LIKE '%" + date
					+ "%'";

			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			String s = rs.getString("max(book_code)");
			if (s == null) {
				code = "A" + date + "01";
			} else {
				System.out.println(s);
				int n = Integer.parseInt(s.substring(9));
				System.out.println();
				char a = s.charAt(0);
				if (n < 10) {
					n += 1;
					code = a + date + "0" + n;
				} else if (n == 99) {
					a += 1;
					n = 1;
					code = a + date + "0" + n;
				} else {
					code = a + date + n;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return code;
	}

}
