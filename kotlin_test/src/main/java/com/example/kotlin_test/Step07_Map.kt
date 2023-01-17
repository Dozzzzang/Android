package com.example.kotlin_test

fun main(){
    //수정 불가능한 Map
    val mem=mapOf<String, Any>("num" to 1, "name" to "김구라", "isMan" to true)

    //Map 에 저장된 데이터 참조하는 방법1
    val num=mem.get("num")
    val name=mem.get("name")
    val addr=mem.get("addr")

    //Map 에 저장된 데이터 참조하는 방법2
    val num2=mem["num"]
    val name2=mem["name"]
    val addr2=mem["addr"]

    //수정 가능한 Map
    val mem2= mutableMapOf<String, Any>()
    // 빈 Map 에 데이터 넣기 방법1
    mem2.put("num", 2)
    mem2.put("name", "해골")
    mem2.put("isMan", false)

    val mem3:MutableMap<String, Any> = mutableMapOf()
    // 빈 Map 에 데이터 넣기 방법2
    mem3["num"]=3
    mem3["name"]="원숭이"
    mem3["isMan"]=true
}