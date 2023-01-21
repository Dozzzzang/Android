package com.example.step09gameview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    //사운드 매니저 객체
    SoundManager sManager;
    //사운드의 종류별로 상수 정의하기
    public static final int SOUND_LAZER=1;
    public static final int SOUND_SHOOT=2;
    public static final int SOUND_BIRDDIE=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //GameView 객체를 생성해서
        GameView view=new GameView(this);
        //MainActivity 의 화면을 GameView 로 모두 채운다.
        setContentView(view);
        //SoundManager 객체를 생성해서
        sManager=new SoundManager(this);
        //GameView 의 setter 메소드를 이용해서 참조값을 전달해 준다.
        view.setSoundManager(sManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //효과음 미리 로딩하기
        sManager.addSound(SOUND_LAZER, R.raw.laser1);
        sManager.addSound(SOUND_SHOOT, R.raw.shoot1);
        sManager.addSound(SOUND_BIRDDIE, R.raw.birddie);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //자원 해제
        sManager.release();
    }

    //옵션 메뉴를 만드는 메소드
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //메뉴 전개자 객체를 얻어와서
        MenuInflater inflater=getMenuInflater();
        //res/menu/menu_option.xml 문서를 전개해서 메뉴로 구성한다.
        inflater.inflate(R.menu.menu_option, menu);
        return true;
    }
    //옵션 메뉴를 선택했을때 호출되는 메소드
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.off: //off 를 눌렀을때 해야할 동작
                sManager.setMute(true);
                break;
            case R.id.on: //on 을 눌렀을때 해야할 동작
                sManager.setMute(false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}