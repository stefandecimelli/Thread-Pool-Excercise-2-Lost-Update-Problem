package com.decimelli;

import java.util.HashMap;
import java.util.Map;

public class FaultyTicketReservationSystem implements TicketReservationSystem {

    Map<String, Integer> trainInfo = new HashMap<String, Integer>(); // trainName:ticketCount

    public FaultyTicketReservationSystem() {
        trainInfo.put("a", 100);
        trainInfo.put("b", 100);
    }

    public void reserveTicket(String trainName, int ticketCount) {
        int ticketsAvailable = trainInfo.get(trainName);
        if (ticketCount > ticketsAvailable) {
            System.out.println(Thread.currentThread().getName() + " Not enough tickets left, could not reserve a ticket to the train: " + trainName);
            return;
        }
        mockSleep();
        trainInfo.put(trainName, trainInfo.get(trainName) - ticketCount);
        System.out.println(Thread.currentThread().getName() + " Reserved " + ticketCount + " tickets for train " + trainName);
    }

    private void mockSleep() {
        try { 
            Thread.sleep(10); 
        } catch (InterruptedException e) {  
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return trainInfo.toString();
    }
}