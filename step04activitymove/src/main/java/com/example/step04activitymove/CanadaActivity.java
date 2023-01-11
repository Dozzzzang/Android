package com.example.step04activitymove;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CanadaActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canada);
        //버튼의 참조값 얻어와서 바로 리스너 등록하기
        findViewById(R.id.finishBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //이 액티비티를 종료 시키기
        finish();
    }
}