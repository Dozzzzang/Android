package com.example.step10viewpager.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {
    //수정 가능한 라이브 데이터
    private MutableLiveData<String> ownerName = new MutableLiveData<>();
    //읽기 전용 라이브 데이터
    private LiveData<String> mText = Transformations.map(ownerName, new Function<String, String>() {
        @Override
        public String apply(String input) {
            // input 을 가공해서 새로운 정보를 얻어낸다.
            String info="이 구역의 주인은 : "+input;
            return info;
        }
    });
    //라이브 데이터를 변경하는 메소드
    public void setOwnerName(String newOwner) {
        //인자로 전달받은 이름을 MutableLiveData 객체의 setValue() 메소드의 인자로 전달해서 변경한다.
        ownerName.setValue(newOwner);
    }

    //읽기 전용 라이브 데이터를 리턴하는 메소드
    public LiveData<String> getText() {
        return mText;
    }
}