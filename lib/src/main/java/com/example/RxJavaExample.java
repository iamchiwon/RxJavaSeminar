package com.example;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by iamchiwon on 2017. 2. 28..
 */

public class RxJavaExample {
    public static void main(String[] args) {
        //
        // ObserveOn 과 SubscribeOn
        Observable.range(0, 5)
                .map(i -> {
                    System.out.println("Map-1     : " + Thread.currentThread().getName());
                    return i;
                })
                .observeOn(Schedulers.newThread())
                .map(i -> {
                    System.out.println("Map-2     : " + Thread.currentThread().getName());
                    expensiveJob(i);
                    return i;
                })
                .observeOn(Schedulers.io())
//                .subscribeOn(Schedulers.computation())
                .subscribe(s -> {
                    System.out.println("Subscribe : " + Thread.currentThread().getName());
                });

        System.out.println("All Done");
        sleep(6000);
    }

    public static void expensiveJob(Integer job) {
        System.out.println("Expensive Job" + job);
        sleep(1000);
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
