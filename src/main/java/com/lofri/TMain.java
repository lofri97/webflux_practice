package com.lofri;

import reactor.core.publisher.Flux;

import java.util.List;

public class TMain {

    public static void main(String[] args) {
        Flux.fromIterable(List.of(0,1,2,3,4,5))
                .doOnSubscribe(v -> System.out.println("Retrieve subscription"))
                .doOnNext(v -> System.out.println("Retrieve emitted value: " + v))
                .doOnRequest(v -> System.out.println(v + " items requested"))
                .doOnComplete(() -> System.out.println("onComplete called"))
                .collectList()
                .subscribe(System.out::println);
    }
}
