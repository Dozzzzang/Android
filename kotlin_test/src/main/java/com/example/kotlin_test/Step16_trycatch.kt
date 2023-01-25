package com.example.kotlin_test

fun main() {
    var str="1000"
    var str2="천"
    //kotlin 에서의 예외처리
    try {
        var result = Integer.parseInt(str)
        println("result:${result}")

        var result2 = Integer.parseInt(str2)
        println("result2:${result2}")
    }catch (nfe : java.lang.NumberFormatException){
        nfe.printStackTrace()
    }
    println("main 함수가 정상 종료 됩니다.")
}