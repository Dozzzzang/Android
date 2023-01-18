package com.example.step07fragment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class BlankFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    //생성자
    public BlankFragment() {
        
    }
    //자신의 객체(Fragment) 를 생성해서 리턴해주는 static 메소드
    public static BlankFragment newInstance(String param1, String param2) {
        // 객체 생성
        BlankFragment fragment = new BlankFragment();
        // 생성된 객체에 인자를 전달할 꾸러미(Bundle) 객체 생성
        Bundle args = new Bundle();
        // 꾸러미 객체에 전달할 값을 담고
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        // 꾸러미 객체를 Fragment 객체에 인자로 전달한 다음
        fragment.setArguments(args);
        // Fragment 객체를 리턴해 준다.
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }
}