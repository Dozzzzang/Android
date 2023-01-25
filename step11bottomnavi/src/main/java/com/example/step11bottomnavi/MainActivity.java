package com.example.step11bottomnavi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.step11bottomnavi.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


/*
        - icon 으로 사용할 vector xml 문서 다운 받는곳
        https://fonts.google.com/icons?icon.platform=android
        https://materialdesignicons.com/
        - 직접 선택해서 만들고 싶다면
        res/drawable => 마우스 우클릭 => new Vector Asset  들어가서 원하는 옵션으로 만들수도 있다.
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //화면 레이아웃 구성하기
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //하단 네비바의 참조값 얻어오기
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //하단 메뉴바 설정객체의 참조값 얻어오기
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        //네비게이션 컨트롤러 객체의 참조값 얻어오기
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //네비게이션 컨트롤러와 하단 메뉴바가 동작하기 위한 초기화 작업하기
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}