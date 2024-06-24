package com.lofri.solution;

import com.lofri.NumberFormat;
import com.lofri.generator.StreamGenerators;

public class Practice1Sol {

    public static void main(String[] args) {
        // Use StreamGenerators

        // Print all numbers in intNumberStream()
        StreamGenerators.intNumbersStream()
                .forEach(number -> System.out.println(number));
        StreamGenerators.intNumbersStream()
                .forEach(System.out::println);

        // Print first number from intNumberStream()
        StreamGenerators.intNumbersStream().findFirst().ifPresent(System.out::println);


        // Print numbers from intNumberStream() that are less than 5
        StreamGenerators.intNumbersStream()
                .filter(number -> number < 5)
                .forEach(System.out::println);

        // Print the second and third numbers in intNumbersStream() that's greater than 5
        StreamGenerators.intNumbersStream()
                .filter(i -> i > 5)
                .skip(1)
                .limit(2)
                .forEach(System.out::println);

        // Print first numbers from intNumbersStream() that are greater than 10
        // If no elements, print -1
        Integer result = StreamGenerators.intNumbersStream()
                .filter(i -> i > 10)
                .findFirst()
                .orElse(-1);
        System.out.println(result);

        // Print all numberFormat's digit from usersStream()
        StreamGenerators.numberFormatsStream()
                .forEach(numberFormat -> System.out.println(numberFormat.getDigit()));

        StreamGenerators.numberFormatsStream()
                .mapToInt(NumberFormat::getDigit)
                .forEach(System.out::println);

        // Print numberFormat's word from usersStream() which numberFormat's digit is match to intNumbersStream().filter(i -> i > 5)
        StreamGenerators.numberFormatsStream()
                        .filter(user ->
                                StreamGenerators.intNumbersStream()
                                        .filter(i -> i > 5)
                                        .anyMatch(number -> number == user.getDigit())
                                )
                        .forEach(numberFormat -> System.out.println(numberFormat.getWord()));

        StreamGenerators.intNumbersStream()
                .filter(i -> i > 5)
                .flatMap(id -> StreamGenerators.numberFormatsStream().filter(numberFormat -> numberFormat.getDigit() == id))
                .forEach(numberFormat -> System.out.println(numberFormat.getWord()));
    }
}
