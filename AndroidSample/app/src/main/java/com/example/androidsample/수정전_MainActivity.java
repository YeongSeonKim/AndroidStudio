package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// 이벤트 처리객체를 만들어야하기 떄문에 일단 class부터 정의!!
// => 특별한 클래스를 상속 받거나 , 특별한 인터페이스를 구현해야함
// => View.OnClickListener (오류나는 이유는 추상클래스를 가지고 있기 때문 => 오버라이딩 시켜줘야함!!)
class MyClickListener implements View.OnClickListener{

    @Override
    public void onClick(View view) {
        // 버튼이 눌렸을 때 호출되요! 새로운 Activity를 start 시켜요~~!
        // Activity를 호출하기 위해서는 2가지의 방식이 있다.
        // 명시적으로 Activity를 지정해서 실행시키는 방식 => 명시적 intent <-> 묵시적
        Intent i = new Intent();
        ComponentName cname = new ComponentName("com.example.androidsample" ,
                                                "com.example.androidsample.LinearLayoutExampleActivity");
        // 어떤 컴포턴츠,액티비티를 사용할 지 지정해준다.  //ComponentName("패키지명","클레스이름풀패스")

        i.setComponent(cname);
        // startActivity(i); // startActivity : Activity class가 가지고 있는 method , 익명 innerclass를 이용해서 처리
    }
}

public class 수정전_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Delegation Model

        // 
        // btn1 : Event Source
        Button btn1 = (Button)findViewById(R.id.btn1);
        // id를 가지고 view 객체를 찾는다.
        // xml에서 id를 선언하면 자동으로 R이 생성되고 그 R에서 아이디르 찾을 수 있다.
        // (Button) : 원래객체 형태로 변경하기 위해 casting 해줘야한다. 캐스팅한 것을 변수로 생성한다.

        // Listener 객체를 생성 ,
        MyClickListener listener = new MyClickListener();

        // Event Source에 Listener 객체를 부착
        btn1.setOnClickListener(listener);
    }
}
