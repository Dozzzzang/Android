package com.example.step16asynctask;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //전송버튼
        Button sendBtn=findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(v -> {
            /*
                시간이 오래 걸리거나 혹은 실행 시간이 불확실한 직업은
                Main thread (UI thread) 에서 하면 안된다.
             */

            //비동기 task 객체를 생성해서
            SendTask task=new SendTask();
            //execute() 메소드를 호출해서 작업을 시작한다.
            task.execute("hello", "...", "bye!");
        });
    }
    /*
        비동기 작업을 도와줄 클래스 설계하기
        1. AsyncTask 추상 클래스를 상속 받는다.
        2. AsyncTask<파라미터 type, 진행중 type, 결과 type>
           에 맞게끔 Generic 클래스를 잘 정의 한다.
        3. doInBackground() 메소드를 오버라이드한다.
        4. 추가로 필요한 메소드가 있으면 추가로 오버라이드 한다.
     */
    public class SendTask extends AsyncTask<String, Void, Void>{

        //백그라운드에서 작업할 내용을 여기서 해준다( 새로운 스레드에서 할 작업 )
        @Override
        protected Void doInBackground(String... strings) {
            //여기는 UI 스레드가 아니다!! 즉 UI 를 업데이트할수 없는 공간이다.

            //String... 은 String[] 로 간주해서 사용하면 된다.
            Messenger.sendMessage(strings[0]);
            //작업의 결과가 있다면 return 해주면 되고
            return null;
        }
        //doInBackground() 메소드가 리턴하면 자동으로 호출되는 메소드
        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            //여기는 UI 스레드이기 때문에 UI 에 관련된 작업을 마음대로 할수 있다.
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("작업성공")
                    .create()
                    .show();

        }
    }
}