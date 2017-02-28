package com.iamchiwon.seminar.rxjavaseminar.rxcomponent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by iamchiwon on 2017. 2. 27..
 */

public class RxButton extends Button {

    PublishSubject<View> clickObserver;

    public RxButton(Context context) {
        super(context);
        rxInit();
    }

    public RxButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        rxInit();
    }

    public RxButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rxInit();
    }

    public RxButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        rxInit();
    }

    private void rxInit() {
        clickObserver = PublishSubject.create();
        setOnClickListener(v -> clickObserver.onNext(v));
    }

    public Observable<View> rxClick() {
        return clickObserver;
    }
}
