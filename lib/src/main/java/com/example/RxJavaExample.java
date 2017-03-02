package com.example;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by iamchiwon on 2017. 2. 28..
 */

public class RxJavaExample {
    public static void main(String[] args) {
        String[] arr = new String[]{"Hello", "World", "Bye"};

        System.out.println("\nFlowable");
        Flowable.fromArray(arr).subscribe(s -> System.out.println(s));

        System.out.println("\nObservable");
        Observable.fromArray(arr).subscribe(s -> System.out.println(s));

        System.out.println("\nSingle");
        Single.just(arr).subscribe(s -> System.out.println(s));

        System.out.println("\nCompletable");
        Completable.complete().subscribe(() -> {
            System.out.println("complete");
        }, error -> {
            System.out.println("error");
        });

        System.out.println("\nMaybe");
        Maybe.just(arr).subscribe(s -> System.out.println(s));


        System.out.println("\nDisposable");
        Disposable disposable = Flowable
                .fromArray(arr)
                .observeOn(Schedulers.io())
                .doOnNext(s -> {
                    sleep(500);
                })
                .subscribe(s -> System.out.println(s));

        sleep(1100);
        disposable.dispose();
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
