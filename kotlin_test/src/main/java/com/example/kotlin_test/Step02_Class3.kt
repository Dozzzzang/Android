package com.example.kotlin_test

class Cat constructor(){
    // init 블럭은 primary 생성자의 일부이다.
    init {
        println("야옹이가 생겼어요")
    }

    //필드
    //null 값을 허용하고 싶으면 type 뒤에 ? 를 붙인다
    var name:String?=null

    //primary 생성자 외에 추가로 생성자 정의하기
    // :this() 는 대표 생성자를 호출하는 표현식이다.
    // 다른 생성자에서 반드시 대표 생성자를 호출해야 한다.
    constructor(name:String):this(){
        println("야옹이의 이름은:${name}")
        this.name=name
    }
}

fun main() {
    Cat()
    Cat("Tomcat")
    // Kotlin 에서는 모든 데이터가 참조 데이터 type 이다.
    var num:Int?=null //null 값을 허용하는 Int type
    num=1

    var isRun:Boolean?=null //null 값을 허용하는 Boolean type
    isRun=true
}