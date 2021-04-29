package com.ponchikchik.webapp;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private int counter;
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("First " + minValue(new int[]{3, 5, 1, 6, 6, 6}));
        System.out.println("Second " + oddOrEven(Arrays.asList(3, 5, 1, 6, 6, 6)));
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }

            private void inc() {
                synchronized (this) {
//                    counter++;
                }
            }

        }).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(mainConcurrency.counter);
    }

    private synchronized void inc() {
        counter++;
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (int acc, int value) -> 10 * acc + value);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        AtomicInteger sum = new AtomicInteger();
        Predicate<Integer> evenPredicate = x -> x % 2 == 0;
        return integers.stream()
                .peek(sum::addAndGet)
                .collect(Collectors.partitioningBy(evenPredicate))
                .get(evenPredicate.negate().test(sum.get()));
    }
}
