package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MessageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_viewactivity);

        // dialog에서 입력한데이터 읽어오기
        Intent i = getIntent();
        // key, value 값으로 적은 값들 가져오기
        String msg = (String)i.getExtras().get("sendMsg"); // 여러개니까 복수로, 원래의 형태로 downcasting 해줘야함
        // String msg = i.getExtras().get("sendMsg");

        TextView tv = (TextView)findViewById(R.id.messageTv);
        tv.setText(msg);

        // Activity 종료 버튼
        Button closeBtn = (Button)findViewById(R.id.closeBtn);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Activity가 가지는 메서드
            }
        });

    }
}
