package com.example.step04activitymove;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class GermanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_german);
        /*
        //익명 클래스를 이용해서 View.OnClickListener type 의 참조값 얻어내기
        View.OnClickListener listener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };
        //리스너 등록하기
        findViewById(R.id.finishBtn).setOnClickListener(listener);
        */
        // 위의 코드를 좀 더 줄이면
        findViewById(R.id.finishBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}