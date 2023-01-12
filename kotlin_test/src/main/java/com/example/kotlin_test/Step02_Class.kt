package com.example.kotlin_test

//클래스 정의하기
class MyCar

class YourCar{
    //멤버 함수
    fun drive(){
        println("달려요!")
    }
}
//대표(primary) 생성자는 클래스명 우측에 선언한다.
class AirPlane constructor(){
    //생성자?
    init {
        println("AirPlane 클래스의 init!")
    }
}
//constructor 예약어 생략가능
class AirPlane2(){
    //생성자?
    init {
        println("AirPlane2 클래스의 init!")
    }
}
//인자로 전달 받을게 없으면 () 생략 가능
class AirPlane3{
    //생성자?
    init {
        println("AirPlane2 클래스의 init!")
    }
}

fun main(){
    //클래스를 이용해서 객체생성 in java => new MyCar()
    var c1=MyCar()
    var c2=YourCar()
    c2.drive()

    AirPlane()
}