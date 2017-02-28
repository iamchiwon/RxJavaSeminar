package com.example;

import rx.Observable;

/**
 * Created by iamchiwon on 2017. 2. 28..
 */

public class RxJavaExample {
    public static void main(String[] args) {
        String[] pokemons = new String[]{"피카츄", "라이츄", "파이리", "꼬부"};

        Observable.from(pokemons)
                .filter(s -> s.endsWith("츄"))
                .map(s -> {
                    String speak = s.substring(0, 2);
                    return speak + speak;
                })
                .subscribe(s -> System.out.println(s));
    }
}
