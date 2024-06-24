package com.lofri.solution;

import com.lofri.generator.ReactiveGenerators;

import java.io.IOException;

public class Practice2Sol {

    public static void main(String[] args) throws IOException {
        // Use ReactiveGenerators

        // Print all numbers in intNumberFlux()
        ReactiveGenerators.intNumberFlux()
                        .subscribe(System.out::println);

        // Print all numbers in intNumberFlux() which are greater than 5
        ReactiveGenerators.intNumberFlux()
                        .filter(number -> number > 5)
                        .subscribe(System.out::println);


        // Print all numberFormats in numberFormatFlux()
        ReactiveGenerators.numberFormatFlux()
                        .subscribe(System.out::println);

        // Get all numbers in intNumbersFlux() as list
        // Print the list and its size
        ReactiveGenerators.intNumberFlux().collectList()
                .subscribe(list -> {
                    System.out.println(list);
                    System.out.println(list.size());
                });

        // Squaring numberFormat's digit value from numberFormatFlux()
        // Print all numberFormats
        ReactiveGenerators.numberFormatFlux()
                .map(numberFormat -> {
                    numberFormat.setDigit((int) Math.pow(numberFormat.getDigit(), 2));
                    return numberFormat;
                })
                .subscribe(System.out::println);


        // Find prime numbers in intNumberFlux()
        // Squares and print the numberFormat with the same digit value as the previously obtained value
        ReactiveGenerators.intNumberFlux()
                .filter(number -> number > 1)
                .filter(number -> {
                    int tmp = number -1;
                    while (tmp != 1) {
                        if (number % tmp == 0)
                            return false;
                        tmp--;
                    }
                    return true;
                })
                .flatMap(number ->
                        ReactiveGenerators.numberFormatFlux()
                                .filter(numberFormat -> numberFormat.getDigit() == number)
                )
                .map(numberFormat -> {
                    numberFormat.setDigit((int) Math.pow(numberFormat.getDigit(), 2));
                    return numberFormat;
                })
                .doOnComplete(() -> System.out.println("onComplete"))
                .subscribe(System.out::println);

        // Subscribe the intNumberExceptionThrowFlux() using implementation of BaseSubscriber
        ReactiveGenerators.intNumberExceptionThrowFlux()
                .subscribe(
                        System.out::println,
                        (ex) -> System.out.println(ex.getMessage()),
                        () -> System.out.println("onComplete")
                );

        // Subscribe the intNumberExceptionThrowFlux() using the error and completion hooks
        // Stop stream when exception thrown
        ReactiveGenerators.intNumberExceptionThrowFlux()
                .doOnError(ex -> System.out.println(ex.getMessage()))
                .doOnComplete(() -> System.out.println("Done"))
                .subscribe(System.out::println);

        // Subscribe the intNumberExceptionThrowFlux() using the error and completion hooks
        // If an error occurs, continue
        ReactiveGenerators.intNumberExceptionThrowFlux()
                .doOnError(ex -> System.out.println(ex.getMessage()))
                .onErrorContinue((ex, obj) -> System.out.println(ex.getMessage() + " " + obj))
                .doOnComplete(() -> System.out.println("onComplete"))
                .subscribe(System.out::println);


        System.out.println("Type any key to end");
        System.in.read();
    }
}
