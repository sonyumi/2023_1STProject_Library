package login;

import java.sql.SQLException;
import java.util.ArrayList;

public class JoinDAO extends MemberDAO {

	// 리스트에 불러온 값을 차례대로 저장 (리턴타입 : list)
	public ArrayList<MemberVo> list(String id, String pw, String name, String gender, String birth, String number,
			String email) {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		//
		id = id.trim();
		pw = pw.trim();
		name = name.trim();
		birth = birth.trim();
		number = number.trim();
		email = email.trim();
		try {
			connDB(); // DB에 연결 하도록 만든 메소드
			String query = "Insert into g_user (id,pw,name,gender,p_number,birth,email)";

			if (id != null) {
				// 쿼리에 조건을 더해줌
				query += " VALUES (" + "'" + id + "'," + "'" + pw + "'," + "'" + name + "'," + "'" + gender + "'," + "'"
						+ number + "'," + "'" + birth + "'," + "'" + email + "')";
			}
			super.rs = super.stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String userInfoRevise1(String id, String name, String gender, String birth, String number, String email) {
		String value = null;
		id = id.trim();
		name = name.trim();
		birth = birth.trim();
		number = number.trim();
		email = email.trim();
		try {
			connDB(); // DB에 연결 하도록 만든 메소드
			String query = "UPDATE g_user SET name='" + name + "', gender='" + gender + "', birth='" + birth
					+ "', p_number='" + number + "', email='" + email + "'";

			if (id != null) {
				// 쿼리에 조건을 더해줌
				query += " WHERE id='" + id + "'";
				value = "완료";
			}
			super.rs = super.stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}

	public String userInfoRevise(String id, String pw, String gender, String birth, String number, String email) {
		String value = null;
		id = id.trim();
		pw = pw.trim();
		birth = birth.trim();
		number = number.trim();
		email = email.trim();
		try {
			connDB(); // DB에 연결 하도록 만든 메소드
			String query = "UPDATE g_user SET pw='" + pw + "',  gender='" + gender + "', birth='" + birth
					+ "', p_number='" + number + "', email='" + email + "'";

			if (id != null) {
				// 쿼리에 조건을 더해줌
				query += " WHERE id='" + id + "'";
				value = "완료";
			}
			super.rs = super.stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}
}
