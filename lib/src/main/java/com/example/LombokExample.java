package com.example;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;

/**
 * Most Used Lombok Annotations
 *
 * Field:
 * @Getter
 * @Setter
 * @NonNull
 *
 * Variable:
 * @Cleanup
 *
 * Class:
 * @AllArgsConstructor
 * @NoArgsConstructor
 * @ToString
 * @EqualsAndHashCode
 * @Data
 */

public class LombokExample {
    public static void main(String[] args) {
        Pokemon monster1 = new Pokemon("피카츄", 9);
        System.out.println(monster1.toString());

        Pokemon monster2 = new Pokemon("라이츄", 9);

        if(monster1.equals(monster2)) {
            System.out.println("친구야!");
        } else {
            System.out.println("형?");
        }
    }

    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode(exclude = "name")
    public static class Pokemon {
        private String name;
        private int age;
    }
}
