package com.lofri;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class ExampleMain {
    public static void main(String[] args) {
        Publisher<Integer> pub = new ExamplePublisher();
        Subscriber<Integer> sub = new ExampleSubscriber();
        pub.subscribe(sub);
    }
}
