package com.iamchiwon.seminar.rxjavaseminar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.iamchiwon.seminar.rxjavaseminar.rxcomponent.RxButtonWrapper;

import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    Subscription eventCapturer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RxButtonWrapper rxButtonWrapper = new RxButtonWrapper(this, R.id.rxwrapped_button);

        //
        // RxButton 이벤트 처리
        final Action1<View> action = v -> Log.d("RxSeminar", "Button Clicked");
        eventCapturer = rxButtonWrapper.rxClick().subscribe(action);

        //
        // Switch 이벤트 처리
        Switch eventSwitch = (Switch) findViewById(R.id.event_switch);
        eventSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    eventCapturer = rxButtonWrapper.rxClick().subscribe(action);

                } else {
                    eventCapturer.unsubscribe();

                }
            }
        });
    }
}
