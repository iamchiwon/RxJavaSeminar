package com.example;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by iamchiwon on 2017. 2. 28..
 */

public class LambdaExample {
    public static void main(String[] args) {

        //
        // 1. 익명 클래스를 사용하는 방법
        executeRunnable(new Runnable() {
            @Override
            public void run() {
                System.out.println("Annonymous Class");
            }
        });

        //
        // 2. 람다 표현식을 사용
        executeRunnable(() -> {
            System.out.println("Lambda Expression");
        });

        //
        // 3. 람다 표현식의 간결함
        Runnable r = () -> System.out.println("Runnable");
        executeRunnable(r);

        //
        // 4. 람다 표현식에서 파라미터 사용
        executeSupplier(s -> {
            System.out.println(s + " Lambda");
        });
    }

    public static void executeRunnable(Runnable r) {
        r.run();
    }

    public static interface Consumer {
        public void accept(String s);
    }

    public static void executeSupplier(Consumer c) {
        c.accept("Hello");
    }
}
