package com.example.androidsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class MyCounter1 implements Runnable{

    // thread에서는 handler를 받아 인젝션시키고 필드에 설정을 잡아줘야함
    private Handler handler;

    MyCounter1(Handler handler){
        this.handler = handler;
    }

    @Override
    public void run() {
        // 1초마다 카운트를 증가시켜서 TextView에 출력
        for (int i = 0; i<10; i++){
            try {
                Thread.sleep(1000); // 1초 머무름 , sleep에러 => try-catch사용
                // Bundle : 데이터를 묶을 수 있는 단위
                Bundle bundle = new Bundle();
                bundle.putString("COUNT", i + ""); // key와 value 쌍으로 받음, ""문자열변환

                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);

                // UI Thread에 message를 전달!! => activity counter가 받음

            } catch (Exception e){

            }
        }
    }
}

public class CounterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        final TextView tv1 = (TextView)findViewById(R.id.counterTv1);
        Button startBtn1 = (Button)findViewById(R.id.startBtn1);

        // Handler : 중간 매개체, 핸들러를 이용해서 메세지 전달.
        final Handler handler = new Handler() {
            // message를 받는 순간 아래 method가 호출 된다.

            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Bundle b = msg.getData();
                tv1.setText(b.getString("COUNT")); // key값을 받아와 text로로
           }
        };

        startBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Thread 하나 생성해서 1초마다 TextView에 카운트를 출력
                MyCounter1 counter = new MyCounter1(handler); // thread에서는 handler를 받아 인젝션시키고 필드에 설정을 잡아줘야함
                Thread t =  new Thread(counter);
                t.start(); // thread 시작
            }
        });
    }
}
