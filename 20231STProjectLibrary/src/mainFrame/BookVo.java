package mainFrame;

public class BookVo {
	private String code, name, writer, publisher, position, image, id, rental, days, ret;

	// 생성자
	public BookVo() {
	}

	public BookVo(String code) {
		this.code = code; // 입력받은 값을 대입
	}

	// 책 코드, 책 이름 생성자
	public BookVo(String code, String img) {
		this.code = code; // 입력받은 값을 대입
		this.image = img;
	}

	// 책 코드, 책 이름, 이미지 경로 생성자
	public BookVo(String code, String name, String image) {
		this.code = code;
		this.name = name;
		this.image = image;
	}

	//
	public BookVo(String code, String name, String writer, String publisher, String img) {
		this.code = code; // 입력받은 값을 대입
		this.name = name;
		this.writer = writer;
		this.publisher = publisher;
		this.image = img;
	}

	public BookVo(String code, String name, String writer, String publisher, String position, String image) {
		this.code = code; // 입력받은 값을 대입
		this.name = name;
		this.writer = writer;
		this.publisher = publisher;
		this.position = position;
		this.image = image;
	}

	public BookVo(String id, String code, String name, String writer, String rental, String days, String ret) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.writer = writer;
		this.rental = rental;
		this.days = days;
		this.ret = ret;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getWriter() {
		return writer;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getPosition() {
		return position;
	}

	public String getImgLink() {
		return image;
	}

	public String getRental() {
		return rental;
	}

	public String getDays() {
		return days;
	}

	public String getReturn() {
		return ret;
	}
}