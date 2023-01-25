package com.example.step11bottomnavi.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }
    //라이브 데이터를 수정하는 메소드
    public void setText(String text){
        mText.setValue(text);
    }

    public LiveData<String> getText() {
        return mText;
    }
}