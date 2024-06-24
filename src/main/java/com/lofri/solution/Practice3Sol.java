package com.lofri.solution;

import com.lofri.generator.ReactiveGenerators;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class Practice3Sol {
    public static void main(String[] args) throws Exception {
        // Use ReactiveGenerators
        // Print a number of intNumberMono()
        ReactiveGenerators.intNumberMono()
                .subscribe(System.out::println);

        // Print a number of noResponseMono
        // Give up after 5 seconds
        ReactiveGenerators.noReturnMono()
                .timeout(Duration.ofSeconds(5))
                .doOnError(TimeoutException.class, e -> {
                    System.out.println("wait 5 seconds");
                })
                .subscribe(System.out::println);

        System.out.println("Type any key to end");
        System.in.read();
    }


}
