package login;

public class MemberVo {
	private String user_id,user_pw,name,gender,birth,number,email;
	
	
	// 생성자
	public MemberVo() { 
	}
	public MemberVo(String id,String pw) {
		this.user_id=id; // 입력받은 값을 대입
		this.user_pw=pw;
	}
	//아이디, 비밀번호를 입력받는 생성자
	public MemberVo(String id,String pw,String name,String gender,String birth,String number, String email) { 
		this.user_id=id; // 입력받은 값을 대입
		this.user_pw=pw; 
		this.name=name;
		this.gender=gender;
		this.birth=birth;
		this.number=number;
		this.email=email;
	}
	public MemberVo(String id,String pw,String name,String gender,String birth,String number) { 
		this.user_id=id; // 입력받은 값을 대입
		this.user_pw=pw; 
		this.name=name;
		this.gender=gender;
		this.birth=birth;
		this.number=number;
	}
	
	//유저 아이디를 내보내기 위한 메소드
	public String getId() {
		return user_id;
	}
	
	//유저 비밀번호를 내보내기 위한 메소드
	public String getPw() {
		return user_pw;
	}
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public String getBirth() {
		return birth;
	}
	public String getNumber() {
		return number;
	}
	public String getEmail() {
		return email;
	}

}
