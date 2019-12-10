package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class TouchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
    }

    // touchEvent
    // 어떤 activity에서 touch가 일어나면 이 touch event 실행
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(this, R.string.toastMsg, Toast.LENGTH_SHORT).show();
        // Toast.makeText(activity를 지칭하는 this, "",얼마나 토스트메세지가 떠있을건지).show();
        // 반드시 show()를 호출해야함 -> 그래야지 메시지 보임

        return super.onTouchEvent(event);
    }
}


