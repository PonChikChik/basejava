package com.ponchikchik.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        System.out.println("First " + minValue(new int[]{3, 5, 1, 6, 6, 6}));
        System.out.println("Second " + oddOrEven(Arrays.asList(3, 5, 1, 6, 6, 6)));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (acc, value) -> 10 * acc + value);
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
