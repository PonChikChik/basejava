package com.ponchikchik.webapp;

public class DeadLock {
    public static void main(String[] args) {
        final String lock1 = "first";
        final String lock2 = "second";

        deadLock(lock1, lock2);
        deadLock(lock2, lock1);
    }

    private static void deadLock(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println("wait " + lock1 + ". Thread name " + getThreadName());
            synchronized (lock1) {
                System.out.println("hold " + lock1 + ". Thread name " + getThreadName());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait " + lock2 + ". Thread name " + getThreadName());
                synchronized (lock2) {
                    System.out.println("hold " + lock2 + ". Thread name " + getThreadName());
                }
            }
        }).start();
    }

    private static String getThreadName() {
        return Thread.currentThread().getName();
    }
}
