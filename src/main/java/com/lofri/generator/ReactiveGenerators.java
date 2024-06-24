package com.lofri.generator;

import com.lofri.NumberConstants;
import com.lofri.NumberFormat;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.Random;

public class ReactiveGenerators {

    private ReactiveGenerators() {}

    public static Flux<String> stringNumberFlux() {
        return ReactiveGenerators.stringNumberFlux(11);
    }

    public static Flux<String> stringNumberFlux(int size) {
        if (NumberConstants.NUMBERS.length > size) {
            throw new IllegalArgumentException("Supported max size is " + NumberConstants.NUMBERS.length);
        }

        return Flux.just(Arrays.copyOf(NumberConstants.NUMBERS, size))
                .delayElements(Duration.ofSeconds(1));
    }

    public static Flux<Integer> intNumberFlux() {
        return ReactiveGenerators.intNumberFlux(11);
    }

    public static Flux<Integer> intNumberFlux(int size) {
        return Flux
                .range(0, size)
                .delayElements(Duration.ofSeconds(1));
    }

    public static Flux<Integer> intNumberExceptionThrowFlux() {
        return intNumberFlux()
                .map(i -> {
                    if (i == 5) {
                        throw new RuntimeException("Exception thrown");
                    }
                    return i;
                });
    }

    public static Flux<NumberFormat> numberFormatFlux() {
        return ReactiveGenerators.numberFormatFlux(11);
    }

    public static Flux<NumberFormat> numberFormatFlux(int size) {
        if (NumberConstants.NUMBERS.length < size) {
            throw new IllegalArgumentException("Supported max size is " + NumberConstants.NUMBERS.length);
        }

        NumberFormat[] numberFormats = new NumberFormat[size];
        for (int i = 0; i < size; i++) {
            numberFormats[i] = new NumberFormat(i, NumberConstants.NUMBERS[i] + "Name");
        }
        return Flux.just(numberFormats)
                .delayElements(Duration.ofSeconds(1));
    }

    public static Mono<Integer> intNumberMono() {
        Random random = new Random();
        return Mono.just(random.nextInt(100) + 1);
    }

    public static Mono<Integer> noReturnMono() {
        return Mono.never();
    }
}
