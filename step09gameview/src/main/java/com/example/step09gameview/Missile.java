package com.example.step09gameview;

public class Missile {
    public int x;
    public int y;
    public boolean isDead=false; //화면에서 제거 할지 여부
    //생성자
    public Missile(int x, int y){
        this.x=x;
        this.y=y;
    }
}
