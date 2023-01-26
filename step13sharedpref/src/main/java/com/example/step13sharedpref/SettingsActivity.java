package com.example.step13sharedpref;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            // settings 라는 아이디를 가지고 있는 레이아웃에 SettingsFragment 띄우기
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        // up button 이 액션바에 보이도록 설정
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //Preference 리스너 등록
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(this);
        pref.registerOnSharedPreferenceChangeListener(this);
    }
    // 설정을 변경하면 호출되는 메소드
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //메소드에 전달되는 키값을 활용해서 변화된 값을 읽어온다.
        if(key.equals("signature")){
            String value = sharedPreferences.getString(key, "");
            Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
        }else if(key.equals("reply")){
            String value = sharedPreferences.getString(key, "");
            Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
        }else if(key.equals("sync")){
            boolean value = sharedPreferences.getBoolean(key, false);
            Toast.makeText(this, "동기화 여부:"+value, Toast.LENGTH_SHORT).show();
        }
    }
    //내부 클래스 SettingsFragment (SharedPreferences 를 자동으로 사용하는 기능을 가진 fragment)
    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            //res/xml/root_preferences.xml 문서를 전개해서 fragment 설정 메뉴 구성하기
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
}