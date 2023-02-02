package com.example.kotlin_test

fun main(){
    println("Hello, world!!")
    val  msgs = listOf<String>("eee", "ddd", "ccc", "bbb", "aaa")
    for(i in msgs.size-1 downTo 0){
        var tmp=msgs[i]
        println(tmp)
    }
}
