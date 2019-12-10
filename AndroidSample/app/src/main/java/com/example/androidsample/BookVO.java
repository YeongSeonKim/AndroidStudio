package com.example.androidsample;

// VO, DTO라고 불리는 class
// 데이터를 조직화해서 자장하는 용도 , 로직처리는 나오면 안된다.
// 어떤 field를 지정할 것인가가 중요함!! 각각의 필드는 private로 처리할꺼임
// 사용하는 데이터베이스 스키마를 기반으로 작성.
// 사용하는 데이터가 도서 이미지, 도서제목, 도서저자, 도서가격을 필드로 지정할꺼임
// 도서 이미지에 대한 데이터는 데이터베이스에 2가지 종류로 들어가 있어요.
// 이미지 데이터는 URL 형태로 지정되어 있어요.

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

public class BookVO {

    private String bimgurl; // 도서 이미지
    private String btitle;  // 도서 제목
    private String bauthor; // 도서 저자
    private String bprice;  // 도서 가격
    private Drawable drawable;  // 따로 데이터를뽑아서 추리는게아니라 imgurl을가지고 여기다 추가할거란말이죠
    // 도서 이미지에 대한 drawable

    // Alt + Insert => Generate

    public BookVO(){

    }

    public BookVO(String bimgurl, String btitle, String bauthor, String bprice) {
        this.bimgurl = bimgurl;
        this.btitle = btitle;
        this.bauthor = bauthor;
        this.bprice = bprice;
    }

    public String getBimgurl() {
        return bimgurl;
    }

    public String getBtitle() {
        return btitle;
    }

    public String getBauthor() {
        return bauthor;
    }

    public String getBprice() {
        return bprice;
    }

    public Drawable getDrawable() { return drawable; }

    public void setBimgurl(String bimgurl) {
        this.bimgurl = bimgurl;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    public void setBprice(String bprice) {
        this.bprice = bprice;
    }

    public void drawableFromURL(){
        Drawable d = null;
        try {
            InputStream is = (InputStream)new URL(bimgurl).getContent(); // 주소 객체로 만들꺼임
            d = Drawable.createFromStream(is,"NAME"); // 그림객체를만드는데 Stream을 통해서 만듬
            this.drawable = d;
        } catch(Exception e){
            Log.i("Error", e.toString());
        }
    }
}
