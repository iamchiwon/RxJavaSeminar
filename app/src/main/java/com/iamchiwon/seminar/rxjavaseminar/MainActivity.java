package com.iamchiwon.seminar.rxjavaseminar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView resultText = (TextView) findViewById(R.id.valid_result);
        EditText editText = (EditText) findViewById(R.id.input_field);

        RxTextView.textChanges(editText)
                .debounce(500, TimeUnit.MILLISECONDS)
                .map(charSequence -> charSequence.toString())
                .distinct()
                .map(text -> checkValid(text.toString()))  //expensive
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(valid -> {
                    if (valid) {
                        resultText.setTextColor(Color.GREEN);
                        resultText.setText("Valid");
                    } else {
                        resultText.setTextColor(Color.RED);
                        resultText.setText("Invalid");
                    }
                }, error -> error.printStackTrace());

    }

    private boolean checkValid(String text) {
        try {

            //expensive job
            Thread.sleep(1000);
            Log.d("RxSeminar", "checkValid on " + Thread.currentThread().getName());

            int num = Integer.parseInt(text);
            return (num >= 1 && num <= 100);

        } catch (Exception e) {
        }
        return false;
    }
}
