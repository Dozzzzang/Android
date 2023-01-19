package com.example.kotlin_test

val nums = listOf<Int>(1,2,3,4,5,6,7,8,9,10)

fun main(){
    nums.forEach(fun(item){
        println(item)
    })
    println("--------------")
    nums.forEach({
        println(it)
    })
    println("--------------")
    nums.forEach{
        println(it)
    }

    //목록에 저장된 모든 아이템을 순회 하면서 조건에 맞는 아이템만 남긴 새로운 목록 얻어내기
    val result=nums.filter {
        it > 5 // 이 조건이 true 인 아이템만 남긴 새로운 목록이 만들어 진다.
    }

    println(result)

    //목록에 저장된 모든 아이템을 순회 하면서 어떤 조작을 가한 새로운 목록 얻어내기
    val result2=nums.map {
        it * 2
    }

    println(result2)
}