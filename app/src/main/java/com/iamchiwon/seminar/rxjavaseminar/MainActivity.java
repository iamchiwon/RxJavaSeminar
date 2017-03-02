package com.iamchiwon.seminar.rxjavaseminar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import rx.Observable;

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

        Observable<String> passwordObservable =
                RxTextView.textChanges(password).map(chseq -> chseq.toString());

        Observable<String> reinputObservable =
                RxTextView.textChanges(reinput).map(chseq -> chseq.toString());

        Observable
                .combineLatest(passwordObservable
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
                .subscribe(RxView.enabled(submit));
    }
}
