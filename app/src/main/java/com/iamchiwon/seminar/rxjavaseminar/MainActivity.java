package com.iamchiwon.seminar.rxjavaseminar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    int pollingCount = 0;
    Subscription pollingSubscription;

    TextView pollingCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pollingCountTextView = (TextView) findViewById(R.id.polling_count);

        RxView.clicks(findViewById(R.id.show_second)).subscribe(v -> {
            startActivity(new Intent(this, SecondActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        polling();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (pollingSubscription != null) {
            pollingSubscription.unsubscribe();
        }
    }

    private void polling() {
        pollingSubscription = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .map(time -> {
                    pollingCount += 1;
                    return "Polling-" + pollingCount;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(text -> pollingCountTextView.setText(text));
    }
}
