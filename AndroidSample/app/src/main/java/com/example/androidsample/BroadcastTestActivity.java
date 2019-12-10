package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BroadcastTestActivity extends AppCompatActivity {

    // Activity의 field로 BroadcastReceiver 지정
    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_test);

        Button registerBtn = (Button)findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Broadcast Receiver를 생성.
                // 먼저 Broadcast Receiver가 어떤 Broadcast를 청취할 수 있는지를
                // 나타내는 intent filter를 생성.
                IntentFilter filter = new IntentFilter();
                filter.addAction("MY_BROADCAST"); // Broadcast에 해당하는 action을 하나잡는다. 이걸 가지고 receiver를 생성
                // 안드로이드 시스템에서 나오는 여러가지 정해져있는 Broadcast를 catch할 수 있다.!

                // BroadcastReceiver() : 추상메서드이기때문에 inner class 만들어서 오버라이딩하면서 만듬
                // 직접객체를 만들수 없기 때문에 오버라이딩하는거임!!
                // 안드로이드의 3번째 component인 Broadcast receiver를 생성
                receiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        // broadcast를 잡았을때 처리해야 할 코드 작성
                        // Toast.makeText(context,"신호 캐치!!",Toast.LENGTH_SHORT).show();

                        // Notification을 사용해 보아요!!

                        // NotificationManager 객체를 획득
                        NotificationManager nManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

                        // channelID와  channelName, Notification 중요도 설정
                        String channelID = "NY_CHANNEL";
                        String channelName = "MY_CHANEL_NAME";
                        int important = NotificationManager.IMPORTANCE_HIGH; // 중요한 Notification라는 것을 알려줌

                        // Oreo 버전이상에서는 channel 객체를 생성해서 설정해야 된다. if문으로 버전체크!
                        // Build.VERSION.SDK_INT: 버전SDK숫자 , Build.VERSION_CODES.O : 오레오버전의미
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                            NotificationChannel channel = new NotificationChannel(channelID,channelName,important);
                            // 채널 설정 ,NotificationChannel(3개의 인자)
                            nManager.createNotificationChannel(channel); // 채널 생성
                        }

                        // 실제 화면에 보이는 Notification을 생성  , 빌더(builder - Notification을 생성하기위해 필요) 생성
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,channelID);

                        // Intent를 하나 생성 => 나중에 notification을 클릭했을 때
                        // 화면에 Activity를 보여주기 위한 용도
                        Intent nIntent = new Intent(context,BroadcastTestActivity.class);
                        nIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        nIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                        int requestID = 10; // 해당 intent의 아이디를 나중에 받기 위해서

                        // PendingIntent는 intent를 가지고 있는 Intent
                        // Intent의 수행을 지연시키는 역할을 수행!!
                        PendingIntent pIntent = PendingIntent.getActivity(context,requestID,nIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                        // Notification 설정부분
                        builder.setContentTitle("제목부분이에요!!")
                                .setContentText("여기는 내용이 나와요!!")
                                .setAutoCancel(true) // 터치했을때 사라지도록 처리
                                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) // 시스템이 기본으로 가지는 소리 설정
                                .setSmallIcon(android.R.drawable.btn_star) // 별모양의 아이콘 표시
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.cat1)) // 이미지를 비트맵형태로 변경
                                .setContentIntent(pIntent);

                        // Notification 만들고 매니저를 이용해서 실제 화면에 띄우는 부분
                        // NotificationManager를 통해서 실제 Notification 실행
                        nManager.notify(0, builder.build());
                    }
                };

                // receiver를 등록하는 작업 (리시버가 등록되어야지 신호를 잡을수 있다.!!)
                registerReceiver(receiver,filter); // 해당 receiver와 filter가 결합되서 등록

            }
        });

        // 등록을 해지해주는 버튼
        Button unregisterBtn  = (Button)findViewById(R.id.unregisterBtn);

        unregisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼이 클릭되면 receiver의 등록을 해제해 줘요!!
                unregisterReceiver(receiver);

                // 현재 등록이 되어있는지를 확인한 후 등록되어 있는 경우에만 해제.
                if( receiver != null ){
                    unregisterReceiver(receiver);
                }
            }
        });

        Button sendSignalBtn = (Button)findViewById(R.id.sendSignalBtn);
        sendSignalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction("MY_BROADCAST");
                sendBroadcast(i);
            }
        });
    }
}
