package com.example.step16asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
/*
    UI 에 관련된 작업이 가능한 스레드는 오직 MainThread(UI Thread) 에서만 가능하다
 */
public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //전송버튼
        Button sendBtn=findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(v -> {
            /*
                시간이 오래 걸리거나 혹은 실행 시간이 불확실한 작업은
                Main thread (UI thread) 에서 하면 안된다.
            */

            //비동기 task 객체를 생성해서
            SendTask task=new SendTask();
            //execute() 메소드를 호출해서 작업을 시작한다.
            task.execute("hello", "...", "bye!");

        });
        //EditText 의 참조값을 필드에 저장
        editText=findViewById(R.id.editText);
        Button testBtn=findViewById(R.id.testBtn);
        testBtn.setOnClickListener(v->{
            //새로운 스레드에서 어떤 작업을하고 작업이 끝나면 그 스레드 안에서 EditText 에 문자열을 출력하려고 한다. 가능할까?
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //3초가 소요되는 어떤 작업이라고 가정
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //EditText 에 문자열을 출력해 보자
                    //editText.setText("작업이 종료 됬습니다"); // ui 스레드에서 ui 를 업데이트 할수 없으니

                    // Handler 객체에 메세지를 보내서 ui 를 업데이트 하도록 한다.
                    handler.sendEmptyMessage(0);
                }
            }).start();
        });
        //Textview 의 참조값을 필드에 저장하기
        textView=findViewById(R.id.textView);
        Button startBtn=findViewById(R.id.startBtn);
        startBtn.setOnClickListener(v->{
            //비동기 Task 시작하기
            new CountTask().execute("김구라","해골","원숭이");
        });
    }
    class CountTask extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            // strings 는 String[] 이다. 전달된 파라미터는 배열에 순서대로 저장되어 있다.
            String p1=strings[0]; //김구라
            String p2=strings[1]; //해골
            String p3=strings[2]; //원숭이

            int count=0;
            //반복문을 10번 돌면서 카운트를 센다
            for(int i=0; i<10; i++){
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                count++; //카운트를 증가 시키고
                publishProgress(count); //카운트 값을 발행한다.
            }
            //작업의 결과라고 가정
            String result="Success!";
            //리턴 해주면 onPostExecute() 메소드가 호출되면서 리턴된 값이 전달된다.
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // values는 Integer[] 이다. 0 번방에 카운트값이 들어 있다.
            // 여기는 UI 스레드이기 때문에 UI 업데이트 가능
            //textView.setText(values[0].toString());
            textView.setText(Integer.toString(values[0])); //이렇게도 가능하다
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // 여기는 UI 스레드이기 때문에 UI 업데이트 가능
            textView.setText(s);
        }
    }

    //필드에 Handler 객체를 생성해서 참조값을 넣어둔다. 단) handleMessage() 메소드를 재정의 해서
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //여기는 UI Thread 이기 때문에 UI 관련 작업을 할수가 있다!
            editText.setText("작업이 종료 됬습니다");
        }
    };



    /*
     비동기 작업을 도와줄 클래스 설계하기
     1. AsyncTask 추상 클래스를 상속 받는다.
     2. AsyncTask<파라미터 type, 진행중 type, 결과 type>
        에 맞게끔 Generic 클래스를 잘 정의 한다.
     3. doInBackground() 메소드를 오버라이드한다.
     4. 추가로 필요한 메소드가 있으면 추가로 오버라이드 한다.
    */
    public class SendTask extends AsyncTask<String, Void, Void>{

        //백그라운드에서 작업할 내용을 여기서 해준다( 새로운 스레드에서 할 작업)
        @Override
        protected Void doInBackground(String... strings) {
            //여기는 UI 스레드가 아니다!!!  즉  UI 를 업데이트할수 없는 공간이다.

            //String... 은  String[] 로 간주해서 사용하면 된다.
            Messenger.sendMessage(strings[0]);
            //작업의 결과가 있다면 return 해주면 되고
            return null;
        }

        // doInBackground() 메소드 안에서 publishProgress() 하면 자동으로 호출되는 메소드
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            //여기도 역시 UI 스레드 이다.

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
