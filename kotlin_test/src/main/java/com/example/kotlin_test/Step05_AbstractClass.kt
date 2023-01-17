package com.example.kotlin_test

// 추상 클래스
abstract class Weapon{
    fun move():Weapon{
        println("이동 합니다.")
        return this
    }
    abstract fun attack()
}

class MyWeapon : Weapon(){
    override fun attack() {
        println("무언가를 공격해요")
    }
}

fun main(){
    val w1=MyWeapon()
    w1.move()
    w1.attack()

    println("--------------------")

    /*
        with( 참조값 ){
        
        }
        참조값을 가지고(참조값과 함께) 여러가지 작업을 { } 안에서 한다
     */
    with(w1){
        move()
        attack()
    }

    //익명 클래스를 이용해서 추상클래스(Weapon) type 의 참조값 얻어내기
    val w2 = object : Weapon(){//클래스 상속은 () 가 있어야 된다.
        override fun attack() {
            println("공중 공격을 해요")
        }
    }
    w2.move()
    w2.attack()

    // 다형성 확인
    val a:MyWeapon = w1
    val b:Weapon = w1
    val c:Any = w1 // java 에서 Object type 에 해당되는 type 이다.
}