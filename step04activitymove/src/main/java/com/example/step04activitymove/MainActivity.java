package com.example.step04activitymove;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //필드
    Button toCanada, toGerman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //버튼의 참조값 얻어와서
        toCanada=findViewById(R.id.toCanada);
        toGerman=findViewById(R.id.toGerman);
        //리스너 등록
        toCanada.setOnClickListener(this);
        toGerman.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {//View view 에는 눌러진 Button 객체의 참조값이 들어 있다
        /*
        // 만일 해당 참조값이 toCanada 필드에 있는 값과 같다면 Canada 로 가기 버튼을 누른 것이다.
        if(view == toCanada){
            //CanadaActivity 를 활성화 시킬수 있는 의도를 가진 객체 생성
            Intent intent=new Intent(this, CanadaActivity.class);
            startActivity(intent);
        }else if(view == toGerman){// 만일 해당 참조값이 toGerman 필드에 있는 값과 같다면 German 으로 가기 버튼을 누른 것이다.
            //GermanActivity 를 활성화 시킬수 있는 의도를 가진 객체 생성
            Intent intent=new Intent(this, GermanActivity.class);
            startActivity(intent);
        }
        */
        // 눌러진 버튼의 아이디값(자동 부여된 정수값)을 읽어와서 R 클래스에 등록된 아이디값과 비교해서 분기하기
        switch (view.getId()){
            case R.id.toCanada:
                //CanadaActivity 를 활성화 시킬수 있는 의도를 가진 객체 생성
                Intent intent=new Intent(this, CanadaActivity.class);
                startActivity(intent);
                break;
            case R.id.toGerman:
                //GermanActivity 를 활성화 시킬수 있는 의도를 가진 객체 생성
                Intent intent2=new Intent(this, GermanActivity.class);
                startActivity(intent2);
                break;
        }
    }
}