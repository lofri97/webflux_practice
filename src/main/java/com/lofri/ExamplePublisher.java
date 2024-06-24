package com.lofri;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExamplePublisher implements Publisher<Integer> {
    private static final String PREFIX = "[ExamplePublisher] ";
    Iterable<Integer> items = Arrays.asList(0,1,2,3,4,5);

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Iterator<Integer> iterator = items.iterator();

        System.out.println(PREFIX + "Create Subscription");
        subscriber.onSubscribe(new Subscription() {
            private static final String PREFIX = "[Subscription] ";
            Future<?> f;
            @Override
            public void request(long n) {
                System.out.println(PREFIX + n + " items requested");
                this.f = executorService.submit(() -> {
                    long left = n;
                    try {
                        while (left > 0) {
                            if (!iterator.hasNext()) {
                                System.out.println(PREFIX + "No items, call Subscriber.onComplete()");
                                subscriber.onComplete();
                                executorService.shutdown();
                                break;
                            }
                            subscriber.onNext(iterator.next());
                            left--;
                        }
                    } catch (Exception e) {
                        subscriber.onError(e);
                    }
                });
            }

            @Override
            public void cancel() {
                f.cancel(true);
            }
        });
    }
}
