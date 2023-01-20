package com.example.kotlin_test

/*
    -  비교 연산자
    == 와 === 의 구분
    == 는 값이 같은지 비교
    === 는 참조값이 같은지 비교
    != 는 값이 다른지 비교
    !== 는 참조값이 다른지 비교
    - 삼항 연산자가 없다
      대신 대체할수 있는 문법이 있다.
    - Elvis 연산자
      ?:
 */

fun main(){
    /*
        names 와 names2 는 참조값은 다르지만, 안에 저장된 값은 같다
     */
    val names= listOf("kim","lee","park")
    val names2= listOf("kim","lee","park")

    println(" names === names2 : ${names === names2}")
    println(" names == names2 : ${names == names2}")

    val a="kim"
    val b="kim"

    println("a === b : ${a === b}")
    println("a == b : ${a == b}")

    var isRun=true
    //var result = isRun ? "달려요" : "달리지 않아요"
    //위의 코드는 삼항 연산자가 없기 때문에 동작하지 않는다.
    var result = if(isRun) "달려요" else "달리지 않아요"

    var result2 = if(isRun){
        println("어쩌구...")
        println("저쩌구...")
        "이걸 남기자... 달려요"
    }else{
        "달리지 않아요"
    }

    // null 이 가능한 변수가 있다고 가정하자!
    var myName:String?=null

    if(myName === null){
        myName = "기본이름"
        println("이름:"+myName)
    }else{
        println("이름:"+myName)
    }
    //   ?:  연산자의 좌측에 있는 값이 null 이면  ?: 연산자의 우측에 있는 값이 남는다.
    //  이 값을 알수가 없으면(null 이면)  ?:  이값을 써라
    var result3 = myName ?: "기본이름"

    println("이름: ${myName ?: "기본이름"}")
}