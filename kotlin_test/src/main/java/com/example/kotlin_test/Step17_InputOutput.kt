package com.example.kotlin_test

import java.io.BufferedReader
import java.io.FileReader
import java.io.InputStream

/*
    Kotlin 에서 입출력은 java 의 클래스를 import 에서 사용해야 한다.
 */
fun main() {
    //키보드와 연결된 InputStream
    var  kbd:InputStream =System.`in`
    // c:\acorn202210\myFolder\memo.txt 파일에서 문자열을 읽어들일려면?
    // in java = >      FileReader fr = new FileReader( File )

    //FileReader
    val fr=FileReader("c:/acorn202210/myFolder/memo.txt")
    //BufferedReader
    val br=BufferedReader(fr)
    //반복문 돌면서
    while (true){
        //한줄씩 읽어들이고
        val line=br.readLine()
        //만일 더이상 읽을 문자열이 없다면 반복문 탈출
        if(line==null)break
        //읽은 문자열 출력하기
        println(line)
    }
}