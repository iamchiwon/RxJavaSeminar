package com.example;

import java.util.Iterator;

import rx.Observable;
import rx.Observer;
import rx.subjects.Subject;

/**
 * Created by iamchiwon on 2017. 2. 28..
 */

public class RxJavaExample {
    public static void main(String[] args) {
        String[] pokemons = new String[]{"피카츄", "라이츄", "파이리", "꼬부"};

        //
        // 기존 방법
        Iterator<String> iter = getIterator(pokemons);
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
        System.out.println("Complete-Iterator");

        //
        // Rx 방법
        Observable.from(pokemons).subscribe(new Observer<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onCompleted() {
                System.out.println("Complete-Rx");
            }

            @Override
            public void onError(Throwable e) {

            }
        });

        //
        // Rx 방법 (짧게)
        Observable.from(pokemons)
                .subscribe(s -> System.out.println(s), e -> {},
                          () -> System.out.println("Complete-Rx2"));
    }

    public static Iterator<String> getIterator(String[] arr) {
        Iterator<String> i = new Iterator<String>() {
            String[] data = arr.clone();
            int current = 0;

            @Override
            public boolean hasNext() {
                return current < arr.length;
            }

            @Override
            public String next() {
                String s = arr[current];
                current += 1;
                return s;
            }
        };
        return i;
    }
}
