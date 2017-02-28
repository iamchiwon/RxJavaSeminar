package com.iamchiwon.seminar.rxjavaseminar.rxcomponent;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by iamchiwon on 2017. 2. 27..
 */

public class RxButtonWrapper {

    Button button;
    PublishSubject<View> clickObserver;

    public RxButtonWrapper(Activity activity, int id) {
        button= (Button)activity.findViewById(id);
        rxInit();
    }

    private void rxInit() {
        clickObserver = PublishSubject.create();
        button.setOnClickListener(v -> clickObserver.onNext(v));
    }

    public Observable<View> rxClick() {
        return clickObserver;
    }

}
