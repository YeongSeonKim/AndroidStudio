package com.example.androidsample;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // btn1 : Event Source
        Button btn1 = (Button)findViewById(R.id.btn1);

        // 새로운 class를 만들지 않고 익명 innerclass를 사용한다.
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.LinearLayoutExampleActivity");
                i.setComponent(cname);
                startActivity(i);

            }
        });

        // btn2 : Event Source
        Button btn2 = (Button)findViewById(R.id.btn2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.ChattingActivity");
                i.setComponent(cname);
                startActivity(i);

            }
        });

        // btn3 : Event Source
        Button btn3 = (Button)findViewById(R.id.btn3);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.ImageActivity");
                i.setComponent(cname);
                startActivity(i);

            }
        });

        // btn4 : Event Source
        Button btn4 = (Button)findViewById(R.id.btn4);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.TouchActivity");
                i.setComponent(cname);
                startActivity(i);

            }
        });

        // btn5 : Event Source
        Button btn5 = (Button)findViewById(R.id.btn5);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.SwipeActivity");
                i.setComponent(cname);
                startActivity(i);

            }
        });

        // btn6 : Event Source
        Button btn6 = (Button)findViewById(R.id.btn6);

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText et = new EditText(MainActivity.this);
                // EditText(context객체,즉 Activity객체(this: inner class , 현재사용되는 레퍼런스)를 넣어줌)
                // 인자로 context 객체를 넣어줘야함, 즉 activity를 넣어야하는데 this만 사용할 수 없으니
                // 클래스 이름이랑 this랑 같이 사용한다.

                // AlertDialog를 생성해 보아요!

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                // view를 만들때는 context객체를 넣어줘야한다!!!
                dialog.setTitle("Activity에 데이터 전달");
                dialog.setMessage("전달할 내용을 입력하세요");
                dialog.setView(et);
                dialog.setPositiveButton("Activity 호출", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 다른 Activity를 호출하는 code

                        Intent intent = new Intent();
                        ComponentName cname = new ComponentName("com.example.androidsample",
                                "com.example.androidsample.MessageViewActivity");
                        intent.setComponent(cname);
                        intent.putExtra("sendMsg", et.getText().toString()); //data type이 안맞아서 toString 추가해줌
                        intent.putExtra("anotherMsg", "다른데이터!!!!");
                        // putExtra() : 추가적인데이터를 전달 , key,value 형식으로
                        // 키값을 다르게해서 여러게 전달가능
                        startActivity(intent);

                    }
                });
                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 취소버튼을 눌렀을때 수행할 코드 작성
                    }
                });
                dialog.show(); // show()를 실행해야 화면에 보인다!!

            }
        });

        // btn8 : Event Source
        Button btn7 = (Button)findViewById(R.id.btn7);

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.DataFromActivity");
                i.setComponent(cname);
                startActivityForResult(i,3000);  // 결과값을 요청하는 작업을 판단하기 위해서
                // startActivityForResult() : 데이터를 받아올 목적으로 사용 (,"reauestCode : 정수값줘야함")


            }
        });

        // btn8 : Event Source
        Button btn8 = (Button)findViewById(R.id.btn8);

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.ANRActivity");
                i.setComponent(cname);
                startActivity(i);

            }
        });

        // btn9 : Event Source
        Button btn9 = (Button)findViewById(R.id.btn9);

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.NoCounterActivity");
                i.setComponent(cname);
                startActivity(i);

            }
        });

        // btn10 : Event Source
        Button btn10 = (Button)findViewById(R.id.btn10);

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.CounterActivity");
                i.setComponent(cname);
                startActivity(i);

            }
        });

        // btn11 : Event Source
        Button btn11 = (Button)findViewById(R.id.btn11);

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.BookSearchActivity");
                i.setComponent(cname);
                startActivity(i);

            }
        });

        // btn12 : Event Source
        Button btn12 = (Button)findViewById(R.id.btn12);

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.MovieSearchActivity");
                i.setComponent(cname);
                startActivity(i);

            }
        });

        // btn13 : Event Source
        Button btn13 = (Button)findViewById(R.id.btn13);

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.CustomBookSearchActivity");
                i.setComponent(cname);
                startActivity(i);

            }
        });

        // btn14 : Event Source
        Button btn14 = (Button)findViewById(R.id.btn14);

        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 명시적 인텐트(Explicit Intent)
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.IntentTestActivity");
                i.setComponent(cname);
                startActivity(i);

            }
        });

        // serviceStartBtn : Event Source
        Button serviceStartBtn = (Button)findViewById(R.id.serviceStartBtn);

        serviceStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // 서비스 객체를 실행!!
                Intent i = new Intent();
                                                        //인자 : 패키지,클래스(패키지포함)
                ComponentName cname = new ComponentName("com.example.androidsample", "com.example.androidsample.LifeCycleService");
                i.setComponent(cname);
                i.putExtra("ActivityToServiceData", "소리없는 아우성!!!");
                // 서비스 클래스를 찾아서 객체화 시키고 실행!!
                startService(i);
            }
        });

        // serviceStopBtn : Event Source
        Button serviceStopBtn = (Button)findViewById(R.id.serviceStopBtn);

        serviceStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 서비스 객체 실행을 종료!!
                Intent i = new Intent();
                //인자 : 패키지,클래스(패키지포함)
                ComponentName cname = new ComponentName("com.example.androidsample", "com.example.androidsample.LifeCycleService");
                i.setComponent(cname);
                // 서비스 클래스를 찾아서 객체화 시키고 실행!!
                stopService(i);
            }
        });

        // btn17 : Event Source
        Button btn17 = (Button)findViewById(R.id.btn17);

        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.KakaoBookSearchActivity");
                i.setComponent(cname);
                startActivity(i);

            }
        });

        // btn18 : Event Source , xml없이 java에서 broadcasting하는거
        Button btn18 = (Button)findViewById(R.id.btn18);

        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Broadcasting
                Intent i = new Intent();
                // action 으로 신호를 발생
                // 일반적으로 시스템에서 발생하는 Broadcasting은 그 종류에 따라 사용하는 Action이 정해져 있다.
                i.setAction("MY_BROADCAST");
                i.putExtra("broadcastMSG", "메세지가 전파되요!!"); //key,value값으로
                // startActivity(i);
                // startService(i);
                sendBroadcast(i);

            }
        });

        // btn20 : Event Source
        Button btn20 = (Button)findViewById(R.id.btn20);

        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 명시적 인텐트(Explicit Intent)
                Intent i = new Intent();
                i.setAction("START_BROADCAST_ACTIVITY");
                startActivity(i);
            }
        });

        // btn21 : Event Source
        Button btn21 = (Button)findViewById(R.id.btn21);

        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 명시적 인텐트(Explicit Intent)
                Intent i = new Intent();
                i.setAction("START_DATABASE_ACTIVITY"); // 묵시적으로 실행 => androidMainfest에서 추가
                startActivity(i);
            }
        });

        // btn22 : Event Source
        Button btn22 = (Button)findViewById(R.id.btn22);

        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 명시적 인텐트(Explicit Intent)
                Intent i = new Intent();
                i.setAction("Contact_ACTIVITY"); // 묵시적으로 실행 => androidMainfest에서 추가
                startActivity(i);
            }
        });


    }

    // callback method , 해당액티비티가 종료되면 자동으로 두번째 액티비티 실행
    @Override                           //   3000         5000             위에 setResult
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3000 && resultCode == 5000) {
            String result = data.getExtras().getString("DATA");
            // Toast 메세지 띄우기
            Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        }
    }

    // Service에서 리턴되는 순간 이 callback method가 실행됨
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String msg =  intent.getExtras().getString("ServiceToActivityData");
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}

