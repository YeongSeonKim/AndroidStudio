package com.example.androidsample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DataFromActivity extends AppCompatActivity {

    private String selectedItem = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_from);

        final ArrayList<String> list = new ArrayList<String>();
        list.add("수박");
        list.add("바나나");
        list.add("딸기");
        list.add("멜론");

        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        // adapter가(객체) 필요!! spinner와 ArrayList를 결합시키기위해서
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, list);
        // ArrayAdapter("어플리케이션 컨텍스트(this써도됨)","레이아웃어떤형태쓸지","list이용해서")
        //                   getApplicationContext()            스타일             list

        spinner.setAdapter(adapter);

        // spinner에서 item을 선택하는 이벤트 처리가 필요!!
        // setOnItemSelectedListener : 여러개의 아이템중에 하나를 선택했을때 이벤트 처리
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedItem = (String)list.get(i);
                    Log.i("selectedTest", "선택된 과일 : " + selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // 버튼 클릭 이벤트 처리
        Button sendMsgBtn = (Button)findViewById(R.id.sendMsgBtn);

        sendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 기존의 코드와 틀려짐
                // => 클릭되면 현재 선택한 과일이름을 이전 Activity로 전달하고 현재 Activity는 종료
                Intent resultIntent = new Intent();
                resultIntent.putExtra("DATA", selectedItem); // key: data, value:과일이름

                setResult(5000, resultIntent);
                // (액티비티의 결과값 설정 , 결과값으로 돌려줄것)
                // 현재 액티비티가 종료되면 자동으로 전달됨
                DataFromActivity.this.finish();  // finish(); 와 같은 의미
                // this만쓰면 리스너를 의미할수 있어서 앞에 Activity를 명시해준다.


            }
        });

    }

}
