package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDAO {
	public static final String driver = "oracle.jdbc.driver.OracleDriver";
	public static final String url = "jdbc:oracle:thin:@localhost:1521/xe";
	public static final String user = "c##firstproj";
	public static final String password = "firstproj";

	protected Connection con; // sql connection interface
	protected Statement stmt; // connection으로 연결된 객체에게 쿼리작업을 실행할수있게 하는 객체
	protected ResultSet rs; // 조회되는 테이블의 값을 Statement를 통해 저장

	// DB와 연결하기위한 메소드 생성
	public void connDB() {

		try {
			Class.forName(driver); // 드라이버 연결 메소드 ,ClassNotFoundException 예외 해줘야함
			System.out.println("JDBC driver loading success."); // 연결이 완료되면 에러가 뜨지않고 프린트 메세지가 뜸
			con = DriverManager.getConnection(url, user, password); // SQLException 예외 해줘야됨, 오라클과 연결하는 구문 Connection
			System.out.println("oracle connection success."); // 오라클 연결이 완료되면 뜨는 메세지
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			// TYPE_SCROLL_SENSITIVE 커서를 자유롭게 이동가능하게 해줌
			// CONCUR_UPDATABLE 데이터 변경이 가능하도록 해줌
			// (커서를) rs.next() 다음위치로, rs.previous() 이전위치로
			// rs.beforeFirst() 처음위치로 , rs.afterLast() 마지막 위치로
			System.out.println("statement create success.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 리스트에 불러온 값을 차례대로 저장 (리턴타입 : list)
	public ArrayList<MemberVo> list(String id, String name, String num) {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		//
		try {
			connDB(); // DB에 연결 하도록 만든 메소드
			String query = "SELECT * FROM g_user";

			if (id != null) {
				// 쿼리에 조건을 더해줌
				query += " where id='" + id + "' and name='" + name + "' and p_number='" + num + "'";
			}
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last(); // 커서를 마지막으로 이동
			System.out.println("rs.getRow() : " + rs.getRow());
			// rs.getRow() == 현재 커서가 가리키고 있는 row 번호 리턴

			// 커서 번호가 0인경우 (테이블에 조회할 레코드가 없을경우)
			if (rs.getRow() == 0) {
				// 입력된 아이디 값과 일치하는 데이터가 없을때(로그인실패)
			} else {
				System.out.println(rs.getRow()); // 현재 커서 값 출력
				rs.previous(); // 커서를 이전으로 돌리기
				while (rs.next()) { // 커서를 다음으로 이동, 데이터가 없을시 false
					String sId = rs.getString("id"); // 현재 커서에 있는 아이디값 저장
					String sPw = rs.getString("pw"); // 현재 커서에 있는 비밀번호값 저장
					MemberVo data = new MemberVo(sId, sPw); // MemberVo(값 불러오는 클래스) 타입의
															// 변수생성(쿼리문을 저장하기위함)
					list.add(data);// 리스트에 값 저장
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 리스트에 불러온 값을 차례대로 저장 (리턴타입 : list)
	public ArrayList<MemberVo> list(String name, String birth, String num, String table) {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		//
		try {
			connDB(); // DB에 연결 하도록 만든 메소드
			String query = "SELECT * FROM " + table;

			if (name != null) {
				// 쿼리에 조건을 더해줌
				query += " where name='" + name + "' and birth='" + birth + "' and p_number='" + num + "'";
			}
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last(); // 커서를 마지막으로 이동
			System.out.println("rs.getRow() : " + rs.getRow());
			// rs.getRow() == 현재 커서가 가리키고 있는 row 번호 리턴

			// 커서 번호가 0인경우 (테이블에 조회할 레코드가 없을경우)
			if (rs.getRow() == 0) {
				// 입력된 아이디 값과 일치하는 데이터가 없을때(로그인실패)
			} else {
				System.out.println(rs.getRow()); // 현재 커서 값 출력
				rs.previous(); // 커서를 이전으로 돌리기
				while (rs.next()) { // 커서를 다음으로 이동, 데이터가 없을시 false
					String sId = rs.getString("id"); // 현재 커서에 있는 아이디값 저장
					String sPw = rs.getString("pw"); // 현재 커서에 있는 비밀번호값 저장
					MemberVo data = new MemberVo(sId, sPw); // MemberVo(값 불러오는 클래스) 타입의
															// 변수생성(쿼리문을 저장하기위함)
					list.add(data);// 리스트에 값 저장
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 리스트에 불러온 값을 차례대로 저장 (리턴타입 : list)
	public ArrayList<MemberVo> list(String id, String table) {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		//
		try {
			connDB(); // DB에 연결 하도록 만든 메소드
			String query = "SELECT * FROM " + table;

			if (id != null) {
				// 쿼리에 조건을 더해줌
				query += " where id like trim('" + id + "')";
			}
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last(); // 커서를 마지막으로 이동
			System.out.println("rs.getRow() : " + rs.getRow());
			// rs.getRow() == 현재 커서가 가리키고 있는 row 번호 리턴

			// 커서 번호가 0인경우 (테이블에 조회할 레코드가 없을경우)
			if (rs.getRow() == 0) {
				// 입력된 아이디 값과 일치하는 데이터가 없을때(로그인실패)
			} else {
				System.out.println(rs.getRow()); // 현재 커서 값 출력
				rs.previous(); // 커서를 이전으로 돌리기
				while (rs.next()) { // 커서를 다음으로 이동, 데이터가 없을시 false
					String sId = rs.getString("id"); // 현재 커서에 있는 아이디값 저장
					String sPw = rs.getString("pw"); // 현재 커서에 있는 비밀번호값 저장
					String sName = rs.getString("name");
					String sGender = rs.getString("gender");
					String sBirth = rs.getString("birth");
					String sNumber = rs.getString("p_number");
					String sEmail = rs.getString("email");
					MemberVo data = new MemberVo(sId, sPw, sName, sGender, sBirth, sNumber, sEmail); // MemberVo(값 불러오는
																										// 클래스) 타입의
					// 변수생성(쿼리문을 저장하기위함)
					list.add(data);// 리스트에 값 저장
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<MemberVo> list1(String id, int i) {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		//
		try {
			connDB(); // DB에 연결 하도록 만든 메소드
			String query = "SELECT * FROM g_user";

			if (id != null) {
				if (i == 0) {

				} else if (i == 1) {
					query += " where id like trim('%" + id + "%')";
				} else if (i == 2) {
					query += " where name like trim('%" + id + "%')";
				} else if (i == 3) {
					query += " where birth like trim('%" + id + "%')";
				} else if (i == 4) {
					query += " where gender like trim('%" + id + "%')";
				} else if (i == 5) {
					query += " where p_number like trim('%" + id + "%')";
				} else if (i == 6) {
					query += " where email like trim('%" + id + "%')";
				} else if (i == 7) {
					query = "DELETE FROM RENTAL\r\n" + "WHERE RENTAL_USER='" + id + "'";
				}
			}
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			if (i != 7) {
				rs.last(); // 커서를 마지막으로 이동
				System.out.println("rs.getRow() : " + rs.getRow());
				// rs.getRow() == 현재 커서가 가리키고 있는 row 번호 리턴

				// 커서 번호가 0인경우 (테이블에 조회할 레코드가 없을경우)
				if (rs.getRow() == 0) {
					// 입력된 아이디 값과 일치하는 데이터가 없을때(로그인실패)
				} else {
					System.out.println(rs.getRow()); // 현재 커서 값 출력
					rs.beforeFirst(); // 커서를 이전으로 돌리기
					while (rs.next()) { // 커서를 다음으로 이동, 데이터가 없을시 false
						String sId = rs.getString("id"); // 현재 커서에 있는 아이디값 저장
						String sPw = rs.getString("pw"); // 현재 커서에 있는 비밀번호값 저장
						String sName = rs.getString("name");
						String sBirth = rs.getString("birth");
						String sGender = rs.getString("gender");
						String sNumber = rs.getString("p_number");
						String sEmail = rs.getString("email");
						MemberVo data = new MemberVo(sId, sPw, sName, sGender, sBirth, sNumber, sEmail); // MemberVo(값
																											// 불러오는
																											// 클래스) 타입의
						// 변수생성(쿼리문을 저장하기위함)
						list.add(data);// 리스트에 값 저장
					}
				}
			} else {
				query = "DELETE FROM g_user\r\n" + "WHERE ID='" + id + "'";
				rs = stmt.executeQuery(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
