package com.example.step08viewbinding;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.step08viewbinding.databinding.ActivityMainBinding;

/*
    view binding 사용하는 방법
    
    1. build.gradle 파일에 설정

    buildFeatures {
        viewBinding = true
    }
    
    2. build.gradle 파일의 우상단에 sync now 버튼을 눌러서 실제 반영되도록 한다.

    3. layout xml 문서의 이름을 확인해서 자동으로 만들어진 클래스명을 예측한다.
    
    예를들어 activity_main.xml 문서이면 ActivityMainBinding 클래스
    예를들어 activity_sub.xml 문서이면 ActivitySubBinding 클래스
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //ActivityMainBinding 객체의 참조값을 담을 필드
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); // 바인딩을 사용할때는 사용하지 않는다
        // activity_main.xml 문서에 전개된 EditText, Button, TextView 의 참조값 얻어오는 방법1
        /*
        EditText a=findViewById(R.id.editText);
        Button button=findViewById(R.id.button);
        TextView textView=findViewById(R.id.textView);
         */
        // activity_main.xml 문서에 전개된 EditText, Button, TextView 의 참조값 얻어오는 방법2
        /*
        ActivityMainBinding binding=ActivityMainBinding.inflate(getLayoutInflater());
        EditText editText=binding.editText;
        Button button=binding.button;
        TextView textView=binding.textView;
         */
        /*
            예를 들어 EditText 에 문자열을 입력 하고 버튼을 누르면 입력한 문자열이
            TextView 로 이동하도록 프로그래밍 한다면 아래와 같은 코딩이 된다.
         */
        //바인딩 객체의 참조값을 얻어와서 필드에 저장
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        //바인딩 객체가 리턴해주는 View 를 이용해서 화면 구성하기
        setContentView(binding.getRoot());
        //버튼에 리스너 등록하기
        binding.button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //EditText 객체에 입력한 문자열 읽어오기
        String msg=binding.editText.getText().toString();
        //TextView 에 출력하기
        binding.textView.setText(msg);
    }
}