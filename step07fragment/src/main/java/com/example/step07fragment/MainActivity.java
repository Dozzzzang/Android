package com.example.step07fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements MyFragment.MyFragmentListener {
    //필드
    MyFragment mf1, mf2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //전개된 view 에는 MyFragment 객체가 2개 있다. 만일 해당 객체의 참조값이 액티비티에서 필요 하다면?

        //FragmentManager 객체의 참조값을 얻어내서
        FragmentManager fm=getSupportFragmentManager();
        //해당객체의 메소드를 활용해서 프레그먼트의 참조값을 얻어낸다.
        mf1=(MyFragment) fm.findFragmentById(R.id.fragment1);
        mf2=(MyFragment) fm.findFragmentById(R.id.fragment2);
    }

    @Override
    public void sendMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void resetFragment(View v){
        mf1.reset();
        mf2.reset();
    }
}