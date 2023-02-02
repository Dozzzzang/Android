package com.example.step17httprequest3

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

object Util {
    //키보드 숨기는 메소드
    fun hideKeyboard(activity: Activity) {
        val iManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (activity.currentFocus == null) return
        iManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
    }

    //메소드의 인자로 전달되는 View 객체의 포커스 해제하는 메소드
    fun releaseFocus(view: View) {
        var parent = view.parent
        var group: ViewGroup? = null
        var child: View? = null
        while (parent != null) {
            if (parent is ViewGroup) {
                group = parent
                for (i in 0 until group!!.childCount) {
                    child = group.getChildAt(i)
                    if (child !== view && child.isFocusable) child.requestFocus()
                }
            }
            parent = parent.parent
        }
    }

    /*
        1. 사용할때 RequestListener 인터페이스 Type 을 전달한다.
        2. 결과는 RequestListener 객체에 전달된다.
        3. Map<String,Object>  에서 응답 코드는
            "code" 라는 키값으로 200, 404, 500, -1 중에 하나가 리턴되고
             -1 이 리턴되면 Exception 발생으로 실패이다. onFail() 호출
     */
    fun sendGetRequest(requestId: Int,
                       requestUrl: String?,
                       params: Map<String?, String?>?,
                       listener: RequestListener?) {
        val task = RequestTask()
        task.setRequestId(requestId)
        task.setRequestUrl(requestUrl)
        task.setListener(listener)
        task.execute(params)
    }

    //POST 방식 REQUEST
    fun sendPostRequest(requestId: Int, requestUrl: String?, params: Map<String?, String?>?, listener: RequestListener?) {
        val task = PostRequestTask()
        task.setRequestId(requestId)
        task.setRequestUrl(requestUrl)
        task.setListener(listener)
        task.execute(params)
    }

    interface RequestListener {
        fun onSuccess(requestId: Int, result: Map<String, Any?>?)
        fun onFail(requestId: Int, result: Map<String, Any?>?)
    }

    private class RequestTask : AsyncTask<Map<String?, String?>, Void?, Map<String, Any?>>() {
        private var requestId = 0
        private var requestUrl: String? = null
        private var listener: RequestListener? = null
        fun setListener(listener: RequestListener?) {
            this.listener = listener
        }

        fun setRequestId(requestId: Int) {
            this.requestId = requestId
        }

        fun setRequestUrl(requestUrl: String?) {
            this.requestUrl = requestUrl
        }

        protected override fun doInBackground(vararg params: Map<String?, String?>?): Map<String, Any?>? {
            var requestUrl = requestUrl
            val param = params[0]
            if (param != null) { //요청 파리미터가 존재 한다면
                //서버에 전송할 데이터를 문자열로 구성하기
                val buffer = StringBuffer()
                val keySet = param.keys
                val it = keySet.iterator()
                var isFirst = true
                //반복문 돌면서 map 에 담긴 모든 요소를 전송할수 있도록 구성한다.
                while (it.hasNext()) {
                    val key = it.next()
                    var arg: String? = null
                    //파라미터가 한글일 경우 깨지지 않도록 하기 위해.
                    var encodedValue: String? = null
                    try {
                        encodedValue = URLEncoder.encode(param[key], "utf-8")
                    } catch (e: UnsupportedEncodingException) {
                    }
                    if (isFirst) {
                        arg = "?$key=$encodedValue"
                        isFirst = false
                    } else {
                        arg = "&$key=$encodedValue"
                    }
                    buffer.append(arg)
                }
                val data = buffer.toString()
                requestUrl += data
            }
            //서버가 http 요청에 대해서 응답하는 문자열을 누적할 객체
            val builder = StringBuilder()
            var conn: HttpURLConnection? = null
            var isr: InputStreamReader? = null
            var br: BufferedReader? = null
            //결과값을 담을 Map Type 객체
            val map: MutableMap<String, Any?> = HashMap()
            try {
                //URL 객체 생성
                val url = URL(requestUrl)
                //HttpURLConnection 객체의 참조값 얻어오기
                conn = url.openConnection() as HttpURLConnection
                if (conn != null) { //연결이 되었다면
                    conn.connectTimeout = 20000 //응답을 기다리는 최대 대기 시간
                    conn.requestMethod = "GET" //Default 설정
                    conn.useCaches = false //케쉬 사용 여부
                    //응답 코드를 읽어온다.
                    val responseCode = conn.responseCode
                    //Map 객체에 응답 코드를 담는다.
                    map["code"] = responseCode
                    if (responseCode == 200) { //정상 응답이라면...
                        //서버가 출력하는 문자열을 읽어오기 위한 객체
                        isr = InputStreamReader(conn.inputStream)
                        br = BufferedReader(isr)
                        //반복문 돌면서 읽어오기
                        while (true) {
                            //한줄씩 읽어들인다.
                            val line = br.readLine() ?: break
                            //더이상 읽어올 문자열이 없으면 반복문 탈출
                            //읽어온 문자열 누적 시키기
                            builder.append(line)
                        }
                        //출력받은 문자열 전체 얻어내기
                        val str = builder.toString()
                        //아래 코드는 수행 불가
                        //console.setText(str);
                        //Map 객체에 결과 문자열을 담는다.
                        map["data"] = str
                    }
                }
            } catch (e: Exception) { //예외가 발생하면
                //에러 정보를 담는다.
                map["code"] = -1
                map["data"] = e.message
            } finally {
                try {
                    isr?.close()
                    br?.close()
                    conn?.disconnect()
                } catch (e: Exception) {
                }
            }
            //결과를 담고 있는 Map 객체를 리턴해준다.
            return map
        }

        override fun onPostExecute(map: Map<String, Any?>) {
            super.onPostExecute(map)
            val code = map["code"] as Int
            if (code != -1) { //성공이라면
                listener!!.onSuccess(requestId, map)
            } else { //실패 (예외발생)
                listener!!.onFail(requestId, map)
            }
        }
    }

    class PostRequestTask : AsyncTask<Map<String?, String?>, Void?, Map<String, Any?>>() {
        private var requestId = 0
        private var requestUrl: String? = null
        private var listener: RequestListener? = null
        fun setListener(listener: RequestListener?) {
            this.listener = listener
        }

        fun setRequestId(requestId: Int) {
            this.requestId = requestId
        }

        fun setRequestUrl(requestUrl: String?) {
            this.requestUrl = requestUrl
        }

        protected override fun doInBackground(vararg params: Map<String?, String?>?): Map<String, Any?>? {
            val param = params[0]
            var queryString = ""
            if (param != null) { //요청 파리미터가 존재 한다면
                //서버에 전송할 데이터를 문자열로 구성하기
                val buffer = StringBuffer()
                val keySet = param.keys
                val it = keySet.iterator()
                var isFirst = true
                //반복문 돌면서 map 에 담긴 모든 요소를 전송할수 있도록 구성한다.
                while (it.hasNext()) {
                    val key = it.next()
                    var arg: String? = null
                    if (isFirst) {
                        arg = key + "=" + param[key]
                        isFirst = false
                    } else {
                        arg = "&" + key + "=" + param[key]
                    }
                    buffer.append(arg)
                }
                queryString = buffer.toString()
            }
            //서버가 http 요청에 대해서 응답하는 문자열을 누적할 객체
            val builder = StringBuilder()
            var conn: HttpURLConnection? = null
            var isr: InputStreamReader? = null
            var br: BufferedReader? = null
            var pw: PrintWriter? = null
            //결과값을 담을 Map Type 객체
            val map: MutableMap<String, Any?> = HashMap()
            try {
                //URL 객체 생성
                val url = URL(requestUrl)
                //HttpURLConnection 객체의 참조값 얻어오기
                conn = url.openConnection() as HttpURLConnection
                if (conn != null) { //연결이 되었다면
                    conn.connectTimeout = 20000 //응답을 기다리는 최대 대기 시간
                    conn.doOutput = true
                    conn.requestMethod = "POST"
                    conn.useCaches = false //케쉬 사용 여부
                    //전송하는 데이터에 맞게 값 설정하기
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded") //폼전송과 동일
                    //출력할 스트림 객체 얻어오기
                    val osw = OutputStreamWriter(conn.outputStream)
                    //문자열을 바로 출력하기 위해 osw 객체를 PrintWriter 객체로 감싼다
                    pw = PrintWriter(osw)
                    //서버로 출력하기
                    pw.write(queryString)
                    pw.flush()

                    //응답 코드를 읽어온다.
                    val responseCode = conn.responseCode
                    //Map 객체에 응답 코드를 담는다.
                    map["code"] = responseCode
                    if (responseCode == 200) { //정상 응답이라면...
                        //서버가 출력하는 문자열을 읽어오기 위한 객체
                        isr = InputStreamReader(conn.inputStream)
                        br = BufferedReader(isr)
                        //반복문 돌면서 읽어오기
                        while (true) {
                            //한줄씩 읽어들인다.
                            val line = br.readLine() ?: break
                            //더이상 읽어올 문자열이 없으면 반복문 탈출
                            //읽어온 문자열 누적 시키기
                            builder.append(line)
                        }
                        //출력받은 문자열 전체 얻어내기
                        val str = builder.toString()
                        //아래 코드는 수행 불가
                        //console.setText(str);
                        //Map 객체에 결과 문자열을 담는다.
                        map["data"] = str
                    }
                }
            } catch (e: Exception) { //예외가 발생하면
                //에러 정보를 담는다.
                map["code"] = -1
                map["data"] = e.message
            } finally {
                try {
                    pw?.close()
                    isr?.close()
                    br?.close()
                    conn?.disconnect()
                } catch (e: Exception) {
                }
            }
            //결과를 담고 있는 Map 객체를 리턴해준다.
            return map
        }

        override fun onPostExecute(map: Map<String, Any?>) {
            super.onPostExecute(map)
            val code = map["code"] as Int
            if (code != -1) { //성공이라면
                listener!!.onSuccess(requestId, map)
            } else { //실패 (예외발생)
                listener!!.onFail(requestId, map)
            }
        }
    }
}