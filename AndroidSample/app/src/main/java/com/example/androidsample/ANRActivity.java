package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//thread 객체를 만들기 위한 class
// 직접 thread 클래스 상속받는 경우 => 잘안씀
/*class MySum extends Thread{

}*/
// 특정 인터페이스를 구현해서 class 생성
class MySum implements Runnable {

    private TextView tv;

    MySum(TextView tv){ // 인젝션 : 클래스간의 연결구조와 상관없이 유연하게 사용가능
                        // 한 객체를 다른 객체에서 넣어서 사용할수 있는
        this.tv = tv;
    }

    // run이라는 추상메서드를 가지고 있기때문에 오버라이딩 해줘야함

    @Override
    public void run() {
        // Thread가 실행이 되면 수행되는 코드를 여기에 작성
        long sum = 0;
        for(long i =0; i<1000000000L; i++){
            sum += i;
        }
        tv.setText("총합은 : " + sum);

    }
}

// ★ 안드로이드에서 UI 에 대한 제어는 Activity 내에서만 즉 mainThread에서만 사용이 가능하다.
//    외부에서 하면 안됨!!

// MainThread
public class ANRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anr);

        final TextView tv = (TextView)findViewById(R.id.countTv);
        Button countBtn = (Button)findViewById(R.id.countBtn);
        Button toastBtn = (Button)findViewById(R.id.toastBtn);

        countBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Thread를 파생시켜야해요!! => 외부클래스를 만들어서 (위에 만듬)
                MySum mySum = new MySum(tv); // Runnable interface를 구현한 객체일뿐 , MySum(객체를 넣어줘야함)

                // Thread를 생성
                Thread t = new Thread();

                t.start(); // non-blocking method : 멈춰있는 메서드가 아니라 수행과 상관없이 계속 진행됨
                           // 새로운 실행흐름을 만들어 낼 수 있어요!!!

                //=> 이렇게 처리는 했는데 UI는 처리가 안된다.
                // tv.setText("총합은 : " + sum);
            }
        });

        toastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ANRActivity.this,"Toast가 실행되요!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
