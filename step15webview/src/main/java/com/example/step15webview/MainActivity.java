package com.example.step15webview;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //WebView 객체의 참조값 얻어오기
        WebView webView=findViewById(R.id.webView);
        //WebView 의 설정 객체 얻어오기
        WebSettings ws=webView.getSettings();
        //javascript 해석 가능하게 설정
        ws.setJavaScriptEnabled(true);
        //WebView 클라이언트 객체 넣어주기
        webView.setWebViewClient(new WebViewClient());
        //webView.setWebChromeClient(new WebChromeClient());
        /*
            원하는 url 로딩시키기
            인터냇 자원을 사용해야 한다 => 비용이 발생할 가능성이 있다 => 사용자의 허가를 얻어내야 한다.
            허가(permission)
            인터냇을 사용하겠다는 permission 이  AndroidManifest.xml 문서에 있어야 한다.
         */
        webView.loadUrl("https://naver.com");
    }
}