package com.example;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by iamchiwon on 2017. 2. 28..
 */

public class RxJavaExample {
    public static void main(String[] args) {
        String[] pokemons = new String[]{"피카츄", "라이츄", "파이리", "꼬부"};

        System.out.println("Current Thread : " + Thread.currentThread().getName());

        System.out.println("\n1. 그냥 사용");
        Observable.range(0, 3).subscribe(i -> {
//            System.out.println("Current Thread : " + Thread.currentThread().getName());
            expensiveJob(i);
        });

        System.out.println("\n2. ObserveOn 사용");
        Observable.range(100, 3).observeOn(Schedulers.newThread()).subscribe(i -> {
//            System.out.println("Current Thread : " + Thread.currentThread().getName());
            expensiveJob(i);
        });

//        Observable.range(200, 3).observeOn(Schedulers.newThread()).subscribe(i -> {
//            System.out.println("Current Thread : " + Thread.currentThread().getName());
//            expensiveJob(i);
//        });

        System.out.println("All Done");
        sleep(4000);
    }

    public static void expensiveJob(Integer job) {
        System.out.println("Expensive Job" + job + " - Start");
        sleep(1000);
        System.out.println("Expensive Job" + job + " - End");
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
