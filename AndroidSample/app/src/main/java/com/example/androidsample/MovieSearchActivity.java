package com.example.androidsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

class SearchMovieDateRunnable implements Runnable{

    private String keyword;
    private Handler handler;

    SearchMovieDateRunnable(Handler handler, String keyword){
        this.handler = handler;
        this.keyword = keyword;
    }

    @Override
    public void run() {
        String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?" +
                "key=c20a460471ff1ffeb64baa5f1e11fec1&targetDt=" + keyword;

        try {
            URL urlObj = new URL(url); // 주소를 자바 객체로 만들어줄꺼임 -> open connection 사용가능
            HttpURLConnection con = (HttpURLConnection)urlObj.openConnection();
            //연결객체를 만든 다음에 데이터를 읽어드릴수  있는 Stream(통로)을 뽑아낼수 있다.
            // network연결이 성공 한 후 데이터를 읽어들이기 위한 데이터 연결 통로 Stream을 생성한다.
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream())); // Stream을 더좋은걸로 바꿔주는거

            // 데이터 저장 용도로 만듬
            String input = "";
            StringBuffer sb = new StringBuffer();

            // 서버가 보내준 데이터를 읽어서 input에 넣는데 null이 아닐때까지 모두 읽으라는 의미
            while ((input = br.readLine()) != null){
                sb.append(input);
            }
            // Log창에 찍어보기
            // Log.i("DATA",sb.toString());

            // 얻어온 결과 JSON 문자열을 Jackson Library를 이용해서
            // Java 객체 형태(String[])로 변형해서 List에 입력할수 있게끔
            ObjectMapper mapper = new ObjectMapper();
            // Jackson Library를 이용하여 JSON문자열을 String[] 형태로 변환
            String[] resultArr = mapper.readValue(sb.toString(),String[].class); // 배열로 변환시켜주기.
            // 원래형태 <-> JSON

            // 데이터를 합치기 위해서 , 바구니라고 생각하면됨
            Bundle bundle = new Bundle();
            bundle.putStringArray("MOVIE_ARRAY", resultArr); // key, value 쌍으로

            // 번들을 보내기 위해서는 메세지가 필요함
            Message msg = new Message();
            msg.setData(bundle);

            // 위에서 만든 메세지 객체를 보냄 , Activtiy에 있는 handler가 받음

            handler.sendMessage(msg);

        }catch (Exception e){
            Log.i("DATAerror",e.toString());
        }
    }
}

public class MovieSearchActivity extends AppCompatActivity {
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);

        Button searchMovieBtn = (Button)findViewById(R.id.searchMovieBtn);

        final DatePicker datePicker = findViewById(R.id.datePicker);
        final TextView txtdate = (TextView)findViewById(R.id.txtdate);
        final ListView movie_list = (ListView)findViewById(R.id.movie_list);


        // dataPicker 날짜 가져오는거

        datePicker.init(2019, 8, 01, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date = year + "/" + monthOfYear + "/" + dayOfMonth;
                Toast.makeText(MovieSearchActivity.this, date, Toast.LENGTH_SHORT).show();
            }
        });

        // handler만들면서 Override하면서 만들꺼임 , 이벤트에서 사용할 꺼니까 final 잡아야함
        final Handler handler = new Handler() {

            // handler에게 message가 전달되면 아래의 method가 callback되요!!
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                // 데이더 받음
                Bundle bundle = msg.getData();
                String[] result = bundle.getStringArray("BOOK_ARRAY");

                // adapter라는 객체는 데이터를 가져다가 view에 그리는 역할을 담당 ,
                // 배열에 있는데이터를 가져와서 그림
                ArrayAdapter adapter = new ArrayAdapter(MovieSearchActivity.this,
                        android.R.layout.simple_list_item_1, result);
                // android.R.layout.simple_list_item_1 : 화면에 보여주는 형태가 정해져있다.

                // List 화면에 띄우기
                movie_list.setAdapter(adapter);
            }
        };



        searchMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String result = String.format("%d년 %d월 %d일",datePicker.getYear(), datePicker.getMonth()+1,datePicker.getDayOfMonth());
//                Toast.makeText(MovieSearchActivity.this,result,Toast.LENGTH_SHORT).show();

                // 사용자가 입력한 keyword를 가지고 Thread를 파생.                   인터페이스로 떨어짐
                SearchMovieDateRunnable runnable = new SearchMovieDateRunnable(handler, txtdate.getText().toString());
                // Thread를 만들려면 new Thread가 반드시 나와야함          외부 Thread에서 정의 해줘야함
                Thread t = new Thread(runnable);

                t.start();
            }
        });
    }
}
