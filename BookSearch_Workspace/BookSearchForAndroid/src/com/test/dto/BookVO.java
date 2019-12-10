package com.test.dto;

// VO, DTO라고 불리는 class
// 데이터를 조직화해서 자장하는 용도 , 로직처리는 나오면 안된다.
// 어떤 field를 지정할 것인가가 중요함!! 각각의 필드는 private로 처리할꺼임
// 사용하는 데이터베이스 스키마를 기반으로 작성.
// 사용하는 데이터가 도서 이미지, 도서제목, 도서저자, 도서가격을 필드로 지정할꺼임
// 도서 이미지에 대한 데이터는 데이터베이스에 2가지 종류로 들어가 있어요.
// 이미지 데이터는 URL 형태로 지정되어 있어요.


public class BookVO {
	
	private String bimgurl; // 도서 이미지
	private String btitle;  // 도서 제목
	private String bauthor; // 도서 저자
	private String bprice;  // 도서 가격
	
	// 기본생성자 , default constructor => class를 확장할 때 문제가 되는것을 막아주기위해 생성함.
	public BookVO() {
	}
	
	// 실제로 사용할  constructor
	public BookVO(String bimgurl, String btitle, String bauthor, String bprice) {
		super();
		this.bimgurl = bimgurl;
		this.btitle = btitle;
		this.bauthor = bauthor;
		this.bprice = bprice;
	}

	// getter와 setter를 생성
	public String getBimgurl() {
		return bimgurl;
	}

	public void setBimgurl(String bimgurl) {
		this.bimgurl = bimgurl;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBauthor() {
		return bauthor;
	}

	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}

	public String getBprice() {
		return bprice;
	}

	public void setBprice(String bprice) {
		this.bprice = bprice;
	}
}
