package com.example.kotlin_test
import com.example.kotlin_test.java.Member
import com.example.kotlin_test.java.MemberDto

fun main(){
    // kotlin 에서 java 클래스도 자유롭게 import 해서 사용할수 있다.
    val mem1=Member()
    mem1.num=1
    mem1.name="김구라"
    mem1.addr="노량진"
    mem1.showInfo()

    val mem2=MemberDto()
    // 내부적으로 java 의 setter 메소드가 호출된다
    mem2.num=2;
    mem2.name="해골"
    mem2.addr="행신동"

    // 내부적으로 java 의 getter 메소드가 호출된다.
    val a = mem2.num;
    val b = mem2.name;
    val c = mem2.addr;
}