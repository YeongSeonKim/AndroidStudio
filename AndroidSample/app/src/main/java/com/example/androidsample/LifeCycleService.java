package com.example.androidsample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class LifeCycleService extends Service {
    public LifeCycleService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }
    // 3가지 Callback method를 사용

    // 직접 오버라이딩 해야함!!

    @Override
    public void onCreate() {
        super.onCreate();
        // 서비스에서 사용할 resource 준비작업
        Log.i("ServiceExam", "onCreate() 호출!!");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 서비스가 하는 로직처리
        Log.i("ServiceExam", "onStartCommand() 호출!!");
        // return super.onStartCommand(intent, flags, startId);
        if (intent == null){
            // 강제종료되서 서비스가 재시작된 경우
            Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
            // this가 안되므로 getApplicationContext() 사용 -> context 확보 / MainActivity에 전달
            // addFlags => 3개 입력해줘야함
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            resultIntent.putExtra("ServiceToActivityData", "새로운 Activity가 생성되었어요!!");

            startActivity(resultIntent); // oncreate()가 호출이 안되서 다시 Activity한테 전달

        }else {
            // Activity가 보내준 데이터를 서비스가 받아요!!
            String msg = intent.getExtras().getString("ActivityToServiceData");
            Log.i("ServiceExam", "Activity가 보내준 메세지 : " + msg);
            // 로직처리가 진행!!!! => Activity에게 전달해야하는 최종 결과 데이터
            Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
                        // this가 안되므로 getApplicationContext() 사용 -> context 확보 / MainActivity에 전달
            // addFlags => 3개 입력해줘야함
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            resultIntent.putExtra("ServiceToActivityData", "결과데이터 전달이에요!!");

            startActivity(resultIntent); // oncreate()가 호출이 안되서 다시 Activity한테 전달



        }
        return Service.START_STICKY; // 강제종료되면 자동적으로 재시작
        // return Service.START_NOT_STICKY; // 강제종료되면 자동적으로 재시작 안함
    }

    @Override
    public void onDestroy() {
        // 리소스 해제 작업
        super.onDestroy();
        Log.i("ServiceExam", "onDestroy() 호출!!");

    }
}
