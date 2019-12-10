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
import android.widget.EditText;
import android.widget.ListView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

// Thread를 만들기 위해서 Runnable한 ~~ , 외부 스레드는 UI를 제어할수 없기때문에 handler사용
class BookSearchRunnable implements Runnable{

    private Handler handler;
    private String keyword;

    BookSearchRunnable(Handler handler, String keyword){
        this.handler = handler;
        this.keyword = keyword;
    }
    @Override
    public void run() {
        String url = "http://70.12.115.56:8080/bookSearch/search?search_keyword=" + keyword;

        try {
            URL urlObj = new URL(url); // 주소 객체로 만들기
            urlObj.openConnection();
            HttpURLConnection con =  (HttpURLConnection)urlObj.openConnection();
            // con.setRequestMethod("GET"); // default로 get이설정되기떄문에 안써도됨

            BufferedReader br =new BufferedReader(new InputStreamReader(con.getInputStream())); // 연결객체에대해서 연결통로를 하나 얻음
            // BufferedReader : 서버가 보내준 데이터를 라인단위로 끊어 읽어 올수 있음
            String input = "";
            StringBuffer sb = new StringBuffer();
            while ((input = br.readLine()) != null ){ // 서버가 보내준 데이터 계속 읽는부분
                sb.append(input);
            }
            // 서버에대한 스트림은 종료
            br.close();
            // 결과적으로 우리가 얻어낸건... JSON형태의 문자열.
            // JSON 문자열을 원래 객체형태로 복원 => jackson library사용
            ObjectMapper mapper = new ObjectMapper();

            // String 타입이 아니라 BookVo를 가져와서 넣어줘야함
            // String[] resultArr = mapper.readValue(sb.toString(),String[].class);
            BookVO[] resultArr = mapper.readValue(sb.toString(),BookVO[].class); // 원래형태로 복원

            // UI Thread에게 데이터를 전달하기 전에 화면에 표현할 데이터가 완전히 준비되어야해요!!
            for (BookVO vo : resultArr){
                vo.drawableFromURL();
            }


           /* for(BookVO vo : resultArr) {
                Log.i("customListView",vo.getBtitle());
            }*/

            Log.i("customListView",sb.toString());

           // 최종적으로 얻어낸 객체를 UI Thread(Main Thread, Activity Thread) 에게 전달해야 해요!!
            Bundle bundle = new Bundle();
            bundle.putSerializable("BOOKS", resultArr);
            Message msg = new Message();
            msg.setData(bundle);

            handler.sendMessage(msg);

        }catch (Exception e){
            Log.i("customListView", e.toString());
        }
    }
}


public class CustomBookSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_book_search);

        // 필요한 reference 얻어오기
        // Button, EditText, ListView
        Button customBtn = (Button)findViewById(R.id.customBtn);
        final EditText customEt = (EditText)findViewById(R.id.customEt);
        final ListView customLv = (ListView)findViewById(R.id.customLv);

        // final을 사용해서 지역변수가되지 않게 사용
        final Handler handler = new Handler(){

            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                // 외부 Thread에서 message를 받으면 호출
                Bundle bundle = msg.getData();
                BookVO[] list = (BookVO[]) bundle.getSerializable("BOOKS"); // 형변환해줘야함

                // ListView에 adapter를 적용해서 ListView를 그리는 작업
                // ListView : 화면에 리스트형태를 보여주는 widget
                // ListView에 데이터를 가져다가 실제 그려주는 작업 => adapter

                // 새로운 adapter를 만들고 class를 만들어야함
                CustomListViewAdapter adapter = new CustomListViewAdapter();

                customLv.setAdapter(adapter);

                // adapter에 그려야하는 데이터를 세팅
                for (BookVO vo : list){
                    adapter.addItem(vo);
                }
            }
        };

        customBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 검색 버튼이 클릭되면 호춯되요!!
                // 사용자가 입력한 keyword를 이용해서 network 접속을 시도
                // 외부 servlet web program을 호출해서 결과를 받아와야 해요!!!
                // Thread를 이용해서 network연결 처리를 해야 해요!!
                // Thread에 입력 keyword값과 handler객체가 인자로 넘어가야 해요!!!
                BookSearchRunnable runnable = new BookSearchRunnable(handler, customEt.getText().toString());
                Thread t = new Thread(runnable);
                t.start();

            }
        });
    }
}
