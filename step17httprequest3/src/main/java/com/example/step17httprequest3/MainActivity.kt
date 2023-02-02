package com.example.step17httprequest3

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class MainActivity : AppCompatActivity() , Util.RequestListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //EditText 객체의 참조값 얻어오기
        val editText=findViewById<EditText>(R.id.inputMsg)
        //Button 객체의 참조값 얻어와서 리스너 등록
        val sendBtn=findViewById<Button>(R.id.sendBtn)
        sendBtn.setOnClickListener{
            //EditText 에 입력한 문자열을 읽어와서
            val msg=editText.text.toString()
            Util.sendPostRequest(
                999,
                "http://59.18.221.178:9000/boot07/api/send",
                mapOf("msg" to msg), //"msg" 라는 키값으로 입력한 메세지를 담은 Map
                this
            )

        }
    }

    override fun onSuccess(requestId: Int, result: Map<String, Any?>?) {
        if(requestId == 999){
            //웹서버가 응답한 json 문자열 { }
            val jsonStr = result?.get("data").toString()
            Log.d("#### json 문자열 ####", jsonStr)
            // JSONObject 객체를 생성하면서 생성자의 인자로 json 문자열을 전달한다.
            val obj=JSONObject(jsonStr)
            // key 값을 이용해서 Boolean, String, Int 값을 추출할수 있다.
            val isSuccess=obj.getBoolean("isSuccess")
            val response=obj.getString("response")
            val num=obj.getInt("num")
        }
    }

    override fun onFail(requestId: Int, result: Map<String, Any?>?) {

    }
}