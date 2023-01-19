package com.example.kotlin_test

class StarBucks{

    var location:String?=null
        set(value) {
            println("location 의 setter 메소드 호출됨")
            field=value+" 지점"
        }
        get() {
            if (field == null){
                return "지점명 없음"
            }else{
                return field
            }
        }
    
}

fun main(){
    val s1=StarBucks()
    s1.location="강남" // 값을 넣어줄때는 setter 가 호출
    
    val s2=StarBucks()
    s2.location="역삼"

    val s3=StarBucks()

    println(s1.location) // 값을 참조 할때는 getter 가 호출
    println(s2.location)
    println(s3.location)
}