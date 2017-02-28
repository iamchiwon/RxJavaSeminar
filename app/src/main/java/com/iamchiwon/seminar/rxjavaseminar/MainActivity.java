package com.iamchiwon.seminar.rxjavaseminar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.iamchiwon.seminar.rxjavaseminar.rxcomponent.RxButton;
import com.iamchiwon.seminar.rxjavaseminar.rxcomponent.RxButtonWrapper;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Button button;
    RxButton rxButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        // 1. 일반 버튼
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Log.d("RxSeminar", "Button Clicked");
        });

        //
        // 2. 상속받아 만든 Rx 버튼
        rxButton = (RxButton) findViewById(R.id.rxbutton);
        rxButton.rxClick()
                .subscribe(v -> {
                    Log.d("RxSeminar", "RX Button Clicked");
                });

        //
        // 3. 버튼을 Rx로 Wrapping 한 클래스
        new RxButtonWrapper(this, R.id.rxwrapped_button)
                .rxClick()
                .subscribe(v -> {
                    Log.d("RxSeminar", "RX Wrapped Button Clicked");
                });
    }
}
