package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChattingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        final TextView tv = (TextView)findViewById(R.id.textView);
        final TextView uId = (TextView)findViewById(R.id.userid);
        final EditText et = (EditText)findViewById(R.id.editText);
        Button sendBtn = (Button)findViewById(R.id.sendBtn);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance()); // scroll 기능을 가능하게 해준다.

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.append(uId.getText() + " >> " + et.getText() + "\n");
                // 스크롤을 해야하는지 판단해서 , scrollTo(x:,y:) 절대좌표

                int lineTop =  tv.getLayout().getLineTop(tv.getLineCount());
                int scrollY = lineTop - tv.getHeight();
                if (scrollY > 0) {
                    tv.scrollTo(0, scrollY);
                } else {
                    tv.scrollTo(0,0);

                }


            }
        });

    }
}
