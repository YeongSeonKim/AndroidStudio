package com.example.androidsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// ListView안의 내용을 그리는 역할을 하는 adapter
// BaseAdapter(추상클래스)를 반드시 상속받아서 사용해야함 => 반드시 오버라이딩 해줘야함
public class CustomListViewAdapter extends BaseAdapter {

    private List<BookVO> list = new ArrayList<BookVO>();

    // 반드시 overriding을 해야하는 method가 존재.
    // Ctrl + O => Override Method

    public void addItem(BookVO vo){
        list.add(vo);
    }

    @Override
    public int getCount() {
        // 총 몇개의 component를 그려야 하는지를 return
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        // 몇번째 데이터를 화면에 출력할지를 결정
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        // 순번자체를 리턴해줌
        return i;
    }

    @Override      // 몇번째 그림을 그릴지
    public View getView(int i, View view, ViewGroup viewGroup) {
        // data를 가져다가 그림을 그림

        final Context context = viewGroup.getContext();

        // 출력할 view 객체를 생성.
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            // 생성된 view 객체에 XML Layout을 설정.
            view = inflater.inflate(R.layout.listview_item, viewGroup, false);
        }

        // 출력할 view Component의 reference 를 획득.
        ImageView iv = (ImageView)view.findViewById(R.id.customIv);
        TextView tv1 = (TextView)view.findViewById(R.id.customTv1);
        TextView tv2 = (TextView)view.findViewById(R.id.customTv2);

        BookVO vo = list.get(i);  // 화면에 출력할 데이터를 가져와요!!

        iv.setImageDrawable(vo.getDrawable());
        tv1.setText(vo.getBtitle());
        tv2.setText(vo.getBauthor());

        return view;
    }
}
