package com.example.step17httprequest2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements Util.RequestListener{
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);

        EditText inputUrl=findViewById(R.id.inputUrl);
        //요청 버튼을 클릭했을때 동작할 준비
        Button requestBtn=findViewById(R.id.requestBtn);
        requestBtn.setOnClickListener(v->{
            //입력한 요청 url 을 읽어온다.
            String url=inputUrl.getText().toString();
            //Util 클래스의 static 메소드를 활용해서 GET 방식 요청하고 결과 받아오기
            Util.sendGetRequest(999, url, null, this);
        });
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        if(requestId == 999){
            // Map 에 data 라는 키값으로 담긴 String type 을 읽어온다.
            String data=(String)result.get("data");
            editText.setText(data);
        }
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {

    }
}