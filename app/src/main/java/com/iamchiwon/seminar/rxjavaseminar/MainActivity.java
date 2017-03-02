package com.iamchiwon.seminar.rxjavaseminar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    enum PasswordCheckResult {
        Empty,
        Short,
        NotEqual,
        Equal,
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView resultText = (TextView) findViewById(R.id.valid_result);
        EditText password = (EditText) findViewById(R.id.password_field);
        EditText reinput = (EditText) findViewById(R.id.reinput_field);
        Button submit = (Button) findViewById(R.id.submit_button);


        Flowable<String> passwordObservable =
                RxJavaInterop.toV2Flowable(
                        RxTextView.textChanges(password).map(chseq -> chseq.toString())
                );

        Flowable<String> reinputObservable =
                RxJavaInterop.toV2Flowable(
                        RxTextView.textChanges(reinput).map(chseq -> chseq.toString())
                );

        Flowable.combineLatest(passwordObservable
                , reinputObservable
                , (pass, repass) -> {
                    if (TextUtils.isEmpty(pass)) return PasswordCheckResult.Empty;
                    if (pass.length() < 6) return PasswordCheckResult.Short;

                    if (TextUtils.isEmpty(repass)) return PasswordCheckResult.Empty;

                    if (pass.equals(repass)) return PasswordCheckResult.Equal;
                    return PasswordCheckResult.NotEqual;
                })
                .doOnNext(check -> {
                    if (check == PasswordCheckResult.Equal) {
                        resultText.setText("Valid");
                        resultText.setTextColor(Color.GREEN);
                    } else if (check == PasswordCheckResult.NotEqual) {
                        resultText.setText("Not Same");
                        resultText.setTextColor(Color.RED);
                    } else if (check == PasswordCheckResult.Short) {
                        resultText.setText("Need more inputs");
                        resultText.setTextColor(Color.BLUE);
                    } else if (check == PasswordCheckResult.Empty) {
                        resultText.setText("No Input");
                        resultText.setTextColor(Color.DKGRAY);
                    } else {
                        resultText.setText("");
                    }
                })
                .map(check -> check == PasswordCheckResult.Equal)
                .subscribe(b ->
                        RxView.enabled(submit).call(b)
                );


        RxJavaInterop.toV2Flowable(
                RxView.clicks(submit).map(v -> submit)
        ).subscribe(v -> doLogin());
    }

    private void doLogin() {
        EditText password = (EditText) findViewById(R.id.password_field);
        TextView resultText = (TextView) findViewById(R.id.valid_result);
        Button submit = (Button) findViewById(R.id.submit_button);
        ProgressBar progress = (ProgressBar) findViewById(R.id.progress);

        String pass = password.getText().toString();

        Single.just(pass)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(pw -> {
                    submit.setEnabled(false);
                    progress.setVisibility(View.VISIBLE);
                })
                .observeOn(Schedulers.io())
                .map(pw -> networkJob(pw))
                .timeout(1, TimeUnit.SECONDS)
                .retry(3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result) {
                        resultText.setTextColor(Color.BLACK);
                        resultText.setText("Logged in");
                    } else {
                        resultText.setTextColor(Color.BLACK);
                        resultText.setText("Login fail");
                    }
                    submit.setEnabled(true);
                    progress.setVisibility(View.GONE);
                }, e -> {
                    resultText.setTextColor(Color.BLACK);
                    resultText.setText("Time out");
                    submit.setEnabled(true);
                    progress.setVisibility(View.GONE);
                });
    }

    private boolean networkJob(String pass) {
        try {
            int r = new Random().nextInt(5);
            Thread.sleep(r * 1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (pass.equals("1234567")) return true;
        return false;
    }
}
