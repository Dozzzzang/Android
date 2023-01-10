package com.example.step01activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity", "onCreate() 호출됨");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity", "onStart() 호출됨");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("MainActivity", "onRestart() 호출됨");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity", "onResume() 호출됨");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MainActivity", "onPause() 호출됨");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("MainActivity", "onStop() 호출됨");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("MainActivity", "onDestroy() 호출됨");
    }
}