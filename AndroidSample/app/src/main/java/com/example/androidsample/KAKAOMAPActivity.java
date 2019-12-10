package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class KAKAOMAPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakaomapactivity);

        MapView map = new MapView(this);
        // ViewGroup : 눈에 보이는 component들을 담는 바구니라고 생각하면됨
        ViewGroup group = (ViewGroup)findViewById(R.id.mapll);
                                                                    // 위도,경도 입력
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(37.501480, 127.039672);
        map.setMapCenterPoint(mapPoint,true);
        group.addView(map);
    }
}
