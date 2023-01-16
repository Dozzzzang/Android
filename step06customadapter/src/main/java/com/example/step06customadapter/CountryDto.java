package com.example.step06customadapter;

import java.io.Serializable;

public class CountryDto implements Serializable {
    //필드
    private int resId; // 출력할 이미지 리소스 아이디 R.id.austria 등등의 값
    private String name; // 나라의 이름
    private String content; // 나라에 대한 자세한 설명
    //디폴트 생성자
    public CountryDto(){}

    public CountryDto(int resId, String name, String content) {
        this.resId = resId;
        this.name = name;
        this.content = content;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
