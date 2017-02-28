package com.example;

import rx.subjects.PublishSubject;

/**
 * Created by iamchiwon on 2017. 2. 28..
 */

public class RxJavaExample {
    public static void main(String[] args) {
        //
        // Subject 는 Observable 이면서 Observer
        PublishSubject<String> subject = PublishSubject.create();

        //
        // Subject 는 Observable 이니까 subscribe 가능
        subject.subscribe(s -> System.out.println(s));

        //
        // 두개의 Thread에서 각각 별도로 이벤트 발생
        new Thread(() -> {
            sleep(1000);
            subject.onNext("1 second later");
        }).start();

        new Thread(() -> {
            sleep(2000);
            subject.onNext("2 second later");
        }).start();

        System.out.println("All Done");
        sleep(3000);
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
