package com.example.kotlin_test


fun main() {
    /*
        in Java => public void a(){ }
        in kotlin => fun a():Unit{ } or fun a(){ }
        코틀린에서 Unit 은 원시 type 이라고 지칭하고 java 의 void 와 비슷한 역할을 한다.
     */
    fun a():Unit{
        println("a 함수 호출됨")
    }
    a()

    val isRun:Boolean=true
    var myName:String?=null
    myName="김구라"


    //함수를 만들어서 변수에 담고 싶다면...
    //type 추론이 가능하지만, 명시적으로 type 을 표시 하자고 한다면 어떻게 해야할까?

    /*
        () -> Unit 은
        1. 함수에 전달되는 인자는 없으며
        2. 아무값도 리턴하지 않는
        3. 함수 type 을 의미한다.
     */
    val b:() -> Unit=fun(){
        println("b 함수 호출됨")
    }
    b()

    // fun() 생략 가능
    val c:() -> Unit = {
        println("c 함수 호출됨")
    }

    /*
        (String)->String
        1. String type 인자를 하나 받아서
        2. String type 을 리턴해주는
        3. 함수 type
     */
    val d:(String)->String = fun(name:String):String{
        return "내 이름은 ${name}"
    }

    // 위를 아래와 같이 줄일수 있다.
    val e:(String)->String = { name -> "내 이름은 ${name}" }

    println(e("김구라"))
}