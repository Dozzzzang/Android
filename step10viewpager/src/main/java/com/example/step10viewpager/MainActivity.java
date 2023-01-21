package com.example.step10viewpager;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.step10viewpager.databinding.ActivityMainBinding;
import com.example.step10viewpager.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // view binding 을 이용해서 화면 구성하기
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ViewPager 에 연결할 Adapter 객체 생성
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        // ViewPager 의 참조값을 얻어와서
        ViewPager viewPager = binding.viewPager;
        // Adapter 연결하기
        viewPager.setAdapter(sectionsPagerAdapter);

        //상단텝의 참조값 얻어와서
        TabLayout tabs = binding.tabs;
        //ViewPage 와 연동 되도록 연결하기
        tabs.setupWithViewPager(viewPager);
        //떠있는 Action 버튼의 참조값 얻어와서
        FloatingActionButton fab = binding.fab;
        //해당 버튼을 눌렀는지 감시할 리스너 등록
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //하단에서 잠시 올라왔다가 사라지는 Sankebar 띄우기
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}