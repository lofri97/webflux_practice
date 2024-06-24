package com.lofri;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

public class ExampleSubscriber implements Subscriber<Integer> {
    private static final String PREFIX = "[ExampleSubscriber] ";
    private static final int AVAIL_SIZE = 3;
    private Subscription subscription;
    private List<Integer> buf = new ArrayList<>();

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println(PREFIX + "Retrieve subscription");
        this.subscription = subscription;
        this.subscription.request(AVAIL_SIZE);
    }

    @Override
    public void onNext(Integer integer) {
        buf.add(integer);
        System.out.println(PREFIX + "Retrieve emitted value: " + integer);
        if (buf.size() >= AVAIL_SIZE) {
            this.subscription.request(1);
        }
    }

    @Override
    public void onError(Throwable t) {
        System.out.println(t);
    }

    @Override
    public void onComplete() {
        System.out.println(PREFIX + "onComplete called");
        System.out.println(PREFIX + "buf: " + buf);
    }
}
