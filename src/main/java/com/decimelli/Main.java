package com.decimelli;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exe;
        TicketReservationSystem sys;

        exe = Executors.newFixedThreadPool(4);
        sys = new FaultyTicketReservationSystem(); // Contains a race condition
        System.out.println("___FaultyTicketReservationSystem___");
        try {
            exe.execute(createReservationTask(sys, "a", 51));
            exe.execute(createReservationTask(sys, "b", 51));
            exe.execute(createReservationTask(sys, "b", 51));
            exe.execute(createReservationTask(sys, "a", 51));
            exe.shutdown();
            exe.awaitTermination(10, TimeUnit.SECONDS);
        } finally {
            exe.shutdownNow();
        }

        exe = Executors.newFixedThreadPool(4);
        sys = new GoodTicketReservationSystem(); // No race condition
        System.out.println("___GoodTicketReservationSystem___");
        try {
            exe.execute(createReservationTask(sys, "a", 51));
            exe.execute(createReservationTask(sys, "b", 51));
            exe.execute(createReservationTask(sys, "b", 51));
            exe.execute(createReservationTask(sys, "a", 51));
            exe.shutdown();
            exe.awaitTermination(10, TimeUnit.SECONDS);
        } finally {
            exe.shutdownNow();
        }
    }

    private static Runnable createReservationTask(TicketReservationSystem sys, String trainName, int ticketCount) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + " " + sys.toString());
            sys.reserveTicket(trainName, ticketCount);
            System.out.println(Thread.currentThread().getName() + " " + sys.toString());
        };
    }
}
